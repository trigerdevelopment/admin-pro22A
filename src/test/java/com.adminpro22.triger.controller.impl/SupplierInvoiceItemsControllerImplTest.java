package com.adminpro22.triger.controller.impl;

import com.adminpro22.triger.controller.impl.CustomUtils;
import com.adminpro22.triger.controller.impl.SupplierInvoiceItemsControllerImpl;
import com.adminpro22.triger.dto.SupplierInvoiceItemsDTO;
import com.adminpro22.triger.mapper.SupplierInvoiceItemsMapper;
import com.adminpro22.triger.models.SupplierInvoiceItems;
import com.adminpro22.triger.service.SupplierInvoiceItemsService;
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
public class SupplierInvoiceItemsControllerImplTest {
    //TODO: create the data Test generator class SupplierInvoiceItemsBuilder
    private static final String ENDPOINT_URL = "/supplier-invoice-itemss";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private SupplierInvoiceItemsControllerImpl supplierinvoiceitemsController;
    @MockBean
    private SupplierInvoiceItemsService supplierinvoiceitemsService;
    @MockBean
    private SupplierInvoiceItemsMapper supplierinvoiceitemsMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.supplierinvoiceitemsController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(supplierinvoiceitemsMapper.asDTOList(ArgumentMatchers.any())).thenReturn(SupplierInvoiceItemsBuilder.getListDTO());

        Mockito.when(supplierinvoiceitemsService.findAll()).thenReturn(SupplierInvoiceItemsBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(supplierinvoiceitemsMapper.asDTO(ArgumentMatchers.any())).thenReturn(SupplierInvoiceItemsBuilder.getDTO());

        Mockito.when(supplierinvoiceitemsService.findById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.of(SupplierInvoiceItemsBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(supplierinvoiceitemsService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(supplierinvoiceitemsService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(supplierinvoiceitemsMapper.asEntity(ArgumentMatchers.any())).thenReturn(SupplierInvoiceItemsBuilder.getEntity());
        Mockito.when(supplierinvoiceitemsService.save(ArgumentMatchers.any(SupplierInvoiceItems.class))).thenReturn(SupplierInvoiceItemsBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(SupplierInvoiceItemsBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(supplierinvoiceitemsService, Mockito.times(1)).save(ArgumentMatchers.any(SupplierInvoiceItems.class));
        Mockito.verifyNoMoreInteractions(supplierinvoiceitemsService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(supplierinvoiceitemsMapper.asEntity(ArgumentMatchers.any())).thenReturn(SupplierInvoiceItemsBuilder.getEntity());
        Mockito.when(supplierinvoiceitemsService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(SupplierInvoiceItemsBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(SupplierInvoiceItemsBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(supplierinvoiceitemsService, Mockito.times(1)).update(ArgumentMatchers.any(SupplierInvoiceItems.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(supplierinvoiceitemsService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(supplierinvoiceitemsService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(supplierinvoiceitemsService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(supplierinvoiceitemsService);
    }