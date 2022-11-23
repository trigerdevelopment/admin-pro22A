package com.adminpro22.triger.controller.impl;

import com.adminpro22.triger.controller.impl.BankMovementControllerImpl;
import com.adminpro22.triger.controller.impl.CustomUtils;
import com.adminpro22.triger.dto.BankMovementDTO;
import com.adminpro22.triger.mapper.BankMovementMapper;
import com.adminpro22.triger.models.BankMovement;
import com.adminpro22.triger.service.BankMovementService;
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
public class BankMovementControllerImplTest {
    //TODO: create the data Test generator class BankMovementBuilder
    private static final String ENDPOINT_URL = "/bank-movements";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private BankMovementControllerImpl bankmovementController;
    @MockBean
    private BankMovementService bankmovementService;
    @MockBean
    private BankMovementMapper bankmovementMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.bankmovementController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(bankmovementMapper.asDTOList(ArgumentMatchers.any())).thenReturn(BankMovementBuilder.getListDTO());

        Mockito.when(bankmovementService.findAll()).thenReturn(BankMovementBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(bankmovementMapper.asDTO(ArgumentMatchers.any())).thenReturn(BankMovementBuilder.getDTO());

        Mockito.when(bankmovementService.findById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.of(BankMovementBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(bankmovementService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(bankmovementService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(bankmovementMapper.asEntity(ArgumentMatchers.any())).thenReturn(BankMovementBuilder.getEntity());
        Mockito.when(bankmovementService.save(ArgumentMatchers.any(BankMovement.class))).thenReturn(BankMovementBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(BankMovementBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(bankmovementService, Mockito.times(1)).save(ArgumentMatchers.any(BankMovement.class));
        Mockito.verifyNoMoreInteractions(bankmovementService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(bankmovementMapper.asEntity(ArgumentMatchers.any())).thenReturn(BankMovementBuilder.getEntity());
        Mockito.when(bankmovementService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(BankMovementBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(BankMovementBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(bankmovementService, Mockito.times(1)).update(ArgumentMatchers.any(BankMovement.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(bankmovementService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(bankmovementService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(bankmovementService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(bankmovementService);
    }