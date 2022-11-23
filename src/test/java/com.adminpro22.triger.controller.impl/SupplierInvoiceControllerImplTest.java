package com.adminpro22.triger.controller.impl;

import com.adminpro22.triger.controller.impl.CustomUtils;
import com.adminpro22.triger.controller.impl.SupplierInvoiceControllerImpl;
import com.adminpro22.triger.dto.SupplierInvoiceDTO;
import com.adminpro22.triger.mapper.SupplierInvoiceMapper;
import com.adminpro22.triger.models.SupplierInvoice;
import com.adminpro22.triger.service.SupplierInvoiceService;
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
public class SupplierInvoiceControllerImplTest {
    //TODO: create the data Test generator class SupplierInvoiceBuilder
    private static final String ENDPOINT_URL = "/supplier-invoices";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private SupplierInvoiceControllerImpl supplierinvoiceController;
    @MockBean
    private SupplierInvoiceService supplierinvoiceService;
    @MockBean
    private SupplierInvoiceMapper supplierinvoiceMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.supplierinvoiceController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(supplierinvoiceMapper.asDTOList(ArgumentMatchers.any())).thenReturn(SupplierInvoiceBuilder.getListDTO());

        Mockito.when(supplierinvoiceService.findAll()).thenReturn(SupplierInvoiceBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(supplierinvoiceMapper.asDTO(ArgumentMatchers.any())).thenReturn(SupplierInvoiceBuilder.getDTO());

        Mockito.when(supplierinvoiceService.findById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.of(SupplierInvoiceBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(supplierinvoiceService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(supplierinvoiceService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(supplierinvoiceMapper.asEntity(ArgumentMatchers.any())).thenReturn(SupplierInvoiceBuilder.getEntity());
        Mockito.when(supplierinvoiceService.save(ArgumentMatchers.any(SupplierInvoice.class))).thenReturn(SupplierInvoiceBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(SupplierInvoiceBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(supplierinvoiceService, Mockito.times(1)).save(ArgumentMatchers.any(SupplierInvoice.class));
        Mockito.verifyNoMoreInteractions(supplierinvoiceService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(supplierinvoiceMapper.asEntity(ArgumentMatchers.any())).thenReturn(SupplierInvoiceBuilder.getEntity());
        Mockito.when(supplierinvoiceService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(SupplierInvoiceBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(SupplierInvoiceBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(supplierinvoiceService, Mockito.times(1)).update(ArgumentMatchers.any(SupplierInvoice.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(supplierinvoiceService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(supplierinvoiceService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(supplierinvoiceService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(supplierinvoiceService);
    }