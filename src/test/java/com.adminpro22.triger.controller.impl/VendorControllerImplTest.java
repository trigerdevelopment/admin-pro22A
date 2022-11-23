package com.adminpro22.triger.controller.impl;

import com.adminpro22.triger.mapper.VendorMapper;
import com.adminpro22.triger.models.Vendor;
import com.adminpro22.triger.service.VendorService;
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
public class VendorControllerImplTest {
    //TODO: create the data Test generator class VendorBuilder
    private static final String ENDPOINT_URL = "/vendors";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private VendorControllerImpl vendorController;
    @MockBean
    private VendorService vendorService;
    @MockBean
    private VendorMapper vendorMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.vendorController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(vendorMapper.asDTOList(ArgumentMatchers.any())).thenReturn(VendorBuilder.getListDTO());

        Mockito.when(vendorService.findAll()).thenReturn(VendorBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(vendorMapper.asDTO(ArgumentMatchers.any())).thenReturn(VendorBuilder.getDTO());

        Mockito.when(vendorService.findById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.of(VendorBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(vendorService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(vendorService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(vendorMapper.asEntity(ArgumentMatchers.any())).thenReturn(VendorBuilder.getEntity());
        Mockito.when(vendorService.save(ArgumentMatchers.any(Vendor.class))).thenReturn(VendorBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(VendorBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(vendorService, Mockito.times(1)).save(ArgumentMatchers.any(Vendor.class));
        Mockito.verifyNoMoreInteractions(vendorService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(vendorMapper.asEntity(ArgumentMatchers.any())).thenReturn(VendorBuilder.getEntity());
        Mockito.when(vendorService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(VendorBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(VendorBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(vendorService, Mockito.times(1)).update(ArgumentMatchers.any(Vendor.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(vendorService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(vendorService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(vendorService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(vendorService);
    }