package com.adminpro22.triger.controller.impl;

import com.adminpro22.triger.controller.impl.BankControllerImpl;
import com.adminpro22.triger.controller.impl.CustomUtils;
import com.adminpro22.triger.dto.BankDTO;
import com.adminpro22.triger.mapper.BankMapper;
import com.adminpro22.triger.models.Bank;
import com.adminpro22.triger.service.BankService;
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
public class BankControllerImplTest {
    //TODO: create the data Test generator class BankBuilder
    private static final String ENDPOINT_URL = "/banks";
    @MockBean
    private ReferenceMapper referenceMapper;
    @InjectMocks
    private BankControllerImpl bankController;
    @MockBean
    private BankService bankService;
    @MockBean
    private BankMapper bankMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.bankController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(bankMapper.asDTOList(ArgumentMatchers.any())).thenReturn(BankBuilder.getListDTO());

        Mockito.when(bankService.findAll()).thenReturn(BankBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(bankMapper.asDTO(ArgumentMatchers.any())).thenReturn(BankBuilder.getDTO());

        Mockito.when(bankService.findById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.of(BankBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(bankService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(bankService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(bankMapper.asEntity(ArgumentMatchers.any())).thenReturn(BankBuilder.getEntity());
        Mockito.when(bankService.save(ArgumentMatchers.any(Bank.class))).thenReturn(BankBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(BankBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(bankService, Mockito.times(1)).save(ArgumentMatchers.any(Bank.class));
        Mockito.verifyNoMoreInteractions(bankService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(bankMapper.asEntity(ArgumentMatchers.any())).thenReturn(BankBuilder.getEntity());
        Mockito.when(bankService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(BankBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(CustomUtils.asJsonString(BankBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(bankService, Mockito.times(1)).update(ArgumentMatchers.any(Bank.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(bankService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(bankService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(bankService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(bankService);
    }