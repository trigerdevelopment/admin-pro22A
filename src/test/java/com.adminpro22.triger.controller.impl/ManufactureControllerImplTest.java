package com.adminpro22.triger.controller.impl;

import com.adminpro22.triger.mapper.ManufactureMapper;
import com.adminpro22.triger.models.Manufacture;
import com.adminpro22.triger.service.ManufactureService;
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
public class ManufactureControllerImplTest {
    //TODO: create the data Test generator class ManufactureBuilder
    private static final String ENDPOINT_URL = "/manufactures";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private ManufactureControllerImpl manufactureController;
    @MockBean
    private ManufactureService manufactureService;
    @MockBean
    private ManufactureMapper manufactureMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.manufactureController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(manufactureMapper.asDTOList(ArgumentMatchers.any())).thenReturn(ManufactureBuilder.getListDTO());

        Mockito.when(manufactureService.findAll()).thenReturn(ManufactureBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(manufactureMapper.asDTO(ArgumentMatchers.any())).thenReturn(ManufactureBuilder.getDTO());

        Mockito.when(manufactureService.findById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.of(ManufactureBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(manufactureService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(manufactureService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(manufactureMapper.asEntity(ArgumentMatchers.any())).thenReturn(ManufactureBuilder.getEntity());
        Mockito.when(manufactureService.save(ArgumentMatchers.any(Manufacture.class))).thenReturn(ManufactureBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(ManufactureBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(manufactureService, Mockito.times(1)).save(ArgumentMatchers.any(Manufacture.class));
        Mockito.verifyNoMoreInteractions(manufactureService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(manufactureMapper.asEntity(ArgumentMatchers.any())).thenReturn(ManufactureBuilder.getEntity());
        Mockito.when(manufactureService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(ManufactureBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(ManufactureBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(manufactureService, Mockito.times(1)).update(ArgumentMatchers.any(Manufacture.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(manufactureService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(manufactureService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(manufactureService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(manufactureService);
    }