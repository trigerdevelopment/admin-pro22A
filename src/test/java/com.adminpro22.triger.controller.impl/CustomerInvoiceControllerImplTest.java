package com.adminpro22.triger.controller.impl;

import com.adminpro22.triger.mapper.CustomerInvoiceMapper;
import com.adminpro22.triger.models.CustomerInvoice;
import com.adminpro22.triger.service.CustomerInvoiceService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class CustomerInvoiceControllerImplTest {
    //TODO: create the data Test generator class CustomerInvoiceBuilder
    private static final String ENDPOINT_URL = "/customer-invoices";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private CustomerInvoiceControllerImpl customerinvoiceController;
    @MockBean
    private CustomerInvoiceService customerinvoiceService;
    @MockBean
    private CustomerInvoiceMapper customerinvoiceMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.customerinvoiceController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(customerinvoiceMapper.asDTOList(ArgumentMatchers.any())).thenReturn(CustomerInvoiceBuilder.getListDTO());

        Mockito.when(customerinvoiceService.findAll()).thenReturn(CustomerInvoiceBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(customerinvoiceMapper.asDTO(ArgumentMatchers.any())).thenReturn(CustomerInvoiceBuilder.getDTO());

        Mockito.when(customerinvoiceService.findById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.of(CustomerInvoiceBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(customerinvoiceService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(customerinvoiceService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(customerinvoiceMapper.asEntity(ArgumentMatchers.any())).thenReturn(CustomerInvoiceBuilder.getEntity());
        Mockito.when(customerinvoiceService.save(ArgumentMatchers.any(CustomerInvoice.class))).thenReturn(CustomerInvoiceBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(CustomerInvoiceBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(customerinvoiceService, Mockito.times(1)).save(ArgumentMatchers.any(CustomerInvoice.class));
        Mockito.verifyNoMoreInteractions(customerinvoiceService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(customerinvoiceMapper.asEntity(ArgumentMatchers.any())).thenReturn(CustomerInvoiceBuilder.getEntity());
        Mockito.when(customerinvoiceService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(CustomerInvoiceBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(CustomerInvoiceBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(customerinvoiceService, Mockito.times(1)).update(ArgumentMatchers.any(CustomerInvoice.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(customerinvoiceService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(customerinvoiceService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(customerinvoiceService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(customerinvoiceService);
    }