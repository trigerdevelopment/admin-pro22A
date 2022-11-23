package com.adminpro22.triger.controller.impl;

import com.adminpro22.triger.controller.impl.CustomUtils;
import com.adminpro22.triger.controller.impl.RawMaterialControllerImpl;
import com.adminpro22.triger.dto.RawMaterialDTO;
import com.adminpro22.triger.mapper.RawMaterialMapper;
import com.adminpro22.triger.models.RawMaterial;
import com.adminpro22.triger.service.RawMaterialService;
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
public class RawMaterialControllerImplTest {
    //TODO: create the data Test generator class RawMaterialBuilder
    private static final String ENDPOINT_URL = "/raw-materials";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private RawMaterialControllerImpl rawmaterialController;
    @MockBean
    private RawMaterialService rawmaterialService;
    @MockBean
    private RawMaterialMapper rawmaterialMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.rawmaterialController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(rawmaterialMapper.asDTOList(ArgumentMatchers.any())).thenReturn(RawMaterialBuilder.getListDTO());

        Mockito.when(rawmaterialService.findAll()).thenReturn(RawMaterialBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(rawmaterialMapper.asDTO(ArgumentMatchers.any())).thenReturn(RawMaterialBuilder.getDTO());

        Mockito.when(rawmaterialService.findById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.of(RawMaterialBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(rawmaterialService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(rawmaterialService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(rawmaterialMapper.asEntity(ArgumentMatchers.any())).thenReturn(RawMaterialBuilder.getEntity());
        Mockito.when(rawmaterialService.save(ArgumentMatchers.any(RawMaterial.class))).thenReturn(RawMaterialBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(RawMaterialBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(rawmaterialService, Mockito.times(1)).save(ArgumentMatchers.any(RawMaterial.class));
        Mockito.verifyNoMoreInteractions(rawmaterialService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(rawmaterialMapper.asEntity(ArgumentMatchers.any())).thenReturn(RawMaterialBuilder.getEntity());
        Mockito.when(rawmaterialService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(RawMaterialBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(RawMaterialBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(rawmaterialService, Mockito.times(1)).update(ArgumentMatchers.any(RawMaterial.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(rawmaterialService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(rawmaterialService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(rawmaterialService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(rawmaterialService);
    }