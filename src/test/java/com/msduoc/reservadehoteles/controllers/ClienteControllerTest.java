package com.msduoc.reservadehoteles.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import com.msduoc.reservadehoteles.models.Cliente;
import com.msduoc.reservadehoteles.service.ClienteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClienteService clienteService;

    private ObjectMapper mapper;
    private Cliente cliente;
    
    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Alejandro");

        mapper = new ObjectMapper();
    }

    @Test
    void testGetAllClientes() throws Exception {
        when(clienteService.getAllClientes()).thenReturn(Arrays.asList(cliente));
        mockMvc.perform(get("/clientes"))
            .andExpect(status().isOk())
            .andExpect(content().json(mapper.writeValueAsString(Arrays.asList(cliente))));
    }

    @Test
    void testGetClienteById() throws Exception {
        when(clienteService.getClienteById(1L)).thenReturn(Optional.of(cliente));
        mockMvc.perform(get("/clientes/1"))
            .andExpect(status().isOk())
            .andExpect(content().json(mapper.writeValueAsString(cliente)));
    }

    @Test
    void testCreateCliente() throws Exception {
        when(clienteService.createCliente(any(Cliente.class))).thenReturn(cliente);
        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(cliente)));
    }

    @Test
    void testUpdateCliente() throws Exception {
        when(clienteService.updateCliente(eq(1L), any(Cliente.class))).thenReturn(cliente);
        mockMvc.perform(put("/clientes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(cliente)));
    }

    @Test
    void testDeleteCliente() throws Exception {
        mockMvc.perform(delete("/clientes/1"))
                .andExpect(status().isOk());
        verify(clienteService).deleteCliente(1L);
    }
}
