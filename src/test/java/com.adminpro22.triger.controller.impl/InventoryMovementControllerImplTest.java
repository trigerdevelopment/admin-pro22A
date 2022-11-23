package com.adminpro22.triger.controller.impl;

import com.adminpro22.triger.controller.impl.CustomUtils;
import com.adminpro22.triger.controller.impl.InventoryMovementControllerImpl;
import com.adminpro22.triger.dto.InventoryMovementDTO;
import com.adminpro22.triger.mapper.InventoryMovementMapper;
import com.adminpro22.triger.models.InventoryMovement;
import com.adminpro22.triger.service.InventoryMovementService;
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
public class InventoryMovementControllerImplTest {
    //TODO: create the data Test generator class InventoryMovementBuilder
    private static final String ENDPOINT_URL = "/inventory-movements";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private InventoryMovementControllerImpl inventorymovementController;
    @MockBean
    private InventoryMovementService inventorymovementService;
    @MockBean
    private InventoryMovementMapper inventorymovementMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.inventorymovementController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(inventorymovementMapper.asDTOList(ArgumentMatchers.any())).thenReturn(InventoryMovementBuilder.getListDTO());

        Mockito.when(inventorymovementService.findAll()).thenReturn(InventoryMovementBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(inventorymovementMapper.asDTO(ArgumentMatchers.any())).thenReturn(InventoryMovementBuilder.getDTO());

        Mockito.when(inventorymovementService.findById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.of(InventoryMovementBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(inventorymovementService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(inventorymovementService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(inventorymovementMapper.asEntity(ArgumentMatchers.any())).thenReturn(InventoryMovementBuilder.getEntity());
        Mockito.when(inventorymovementService.save(ArgumentMatchers.any(InventoryMovement.class))).thenReturn(InventoryMovementBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(InventoryMovementBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(inventorymovementService, Mockito.times(1)).save(ArgumentMatchers.any(InventoryMovement.class));
        Mockito.verifyNoMoreInteractions(inventorymovementService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(inventorymovementMapper.asEntity(ArgumentMatchers.any())).thenReturn(InventoryMovementBuilder.getEntity());
        Mockito.when(inventorymovementService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(InventoryMovementBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(InventoryMovementBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(inventorymovementService, Mockito.times(1)).update(ArgumentMatchers.any(InventoryMovement.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(inventorymovementService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(inventorymovementService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(inventorymovementService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(inventorymovementService);
    }