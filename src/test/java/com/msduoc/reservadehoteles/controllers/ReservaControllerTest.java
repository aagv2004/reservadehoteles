package com.msduoc.reservadehoteles.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;
import com.msduoc.reservadehoteles.models.Cliente;
import com.msduoc.reservadehoteles.models.Reserva;
import com.msduoc.reservadehoteles.service.ReservaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ReservaController.class)
public class ReservaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReservaService reservaService;

    private ObjectMapper mapper;
    private Reserva reserva;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Alejandro");
        reserva = new Reserva();
        reserva.setId(1L);
        reserva.setCliente(cliente);

        mapper = new ObjectMapper();
    }

    @Test
    void testGetAllReservas() throws Exception {
        when(reservaService.getAllReservas()).thenReturn(Arrays.asList(reserva));
        mockMvc.perform(get("/reservas"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(Arrays.asList(reserva))));
    }

    @Test
    void testGetReservaById() throws Exception {
        when(reservaService.getReservaById(1L)).thenReturn(Optional.of(reserva));
        mockMvc.perform(get("/reservas/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(reserva)));
    }

    @Test
    void testCreateReserva() throws Exception {
        when(reservaService.createReserva(any(Reserva.class))).thenReturn(reserva);
        mockMvc.perform(post("/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(reserva)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(reserva)));
    }

    @Test
    void testUpdateReserva() throws Exception {
        when(reservaService.updateReserva(eq(1L), any(Reserva.class))).thenReturn(reserva);
        mockMvc.perform(put("/reservas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(reserva)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(reserva)));
    }

    @Test
    void testDeleteReserva() throws Exception {
        mockMvc.perform(delete("/reservas/1"))
                .andExpect(status().isOk());
        verify(reservaService).deleteReserva(1L);
    }
}
