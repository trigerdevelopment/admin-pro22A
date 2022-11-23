package com.adminpro22.triger.controller.impl;

import com.adminpro22.triger.controller.impl.CustomUtils;
import com.adminpro22.triger.controller.impl.InvoiceItemsControllerImpl;
import com.adminpro22.triger.dto.InvoiceItemsDTO;
import com.adminpro22.triger.mapper.InvoiceItemsMapper;
import com.adminpro22.triger.models.InvoiceItems;
import com.adminpro22.triger.service.InvoiceItemsService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class InvoiceItemsControllerImplTest {
    //TODO: create the data Test generator class InvoiceItemsBuilder
    private static final String ENDPOINT_URL = "/invoice-itemss";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private InvoiceItemsControllerImpl invoiceitemsController;
    @MockBean
    private InvoiceItemsService invoiceitemsService;
    @MockBean
    private InvoiceItemsMapper invoiceitemsMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.invoiceitemsController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(invoiceitemsMapper.asDTOList(ArgumentMatchers.any())).thenReturn(InvoiceItemsBuilder.getListDTO());

        Mockito.when(invoiceitemsService.findAll()).thenReturn(InvoiceItemsBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(invoiceitemsMapper.asDTO(ArgumentMatchers.any())).thenReturn(InvoiceItemsBuilder.getDTO());

        Mockito.when(invoiceitemsService.findById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.of(InvoiceItemsBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(invoiceitemsService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(invoiceitemsService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(invoiceitemsMapper.asEntity(ArgumentMatchers.any())).thenReturn(InvoiceItemsBuilder.getEntity());
        Mockito.when(invoiceitemsService.save(ArgumentMatchers.any(InvoiceItems.class))).thenReturn(InvoiceItemsBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(InvoiceItemsBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(invoiceitemsService, Mockito.times(1)).save(ArgumentMatchers.any(InvoiceItems.class));
        Mockito.verifyNoMoreInteractions(invoiceitemsService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(invoiceitemsMapper.asEntity(ArgumentMatchers.any())).thenReturn(InvoiceItemsBuilder.getEntity());
        Mockito.when(invoiceitemsService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(InvoiceItemsBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(InvoiceItemsBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(invoiceitemsService, Mockito.times(1)).update(ArgumentMatchers.any(InvoiceItems.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(invoiceitemsService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(invoiceitemsService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(invoiceitemsService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(invoiceitemsService);
    }