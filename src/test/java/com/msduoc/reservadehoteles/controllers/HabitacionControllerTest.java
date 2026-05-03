package com.msduoc.reservadehoteles.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import com.msduoc.reservadehoteles.models.Habitacion;
import com.msduoc.reservadehoteles.service.HabitacionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(HabitacionController.class)
public class HabitacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private HabitacionService habitacionService;

    private ObjectMapper mapper;
    private Habitacion habitacion;

    @BeforeEach
    void setUp() {
        habitacion = new Habitacion();
        habitacion.setId(1L);
        habitacion.setNumeroHabitacion(21);

        mapper = new ObjectMapper();
    }

    @Test
    void testGetAllHabitaciones() throws Exception {
        when(habitacionService.getAllHabitaciones()).thenReturn(Arrays.asList(habitacion));
        mockMvc.perform(get("/habitaciones"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(Arrays.asList(habitacion))));
    }

    @Test
    void testGetHabitacionById() throws Exception {
        when(habitacionService.getHabitacionById(1L)).thenReturn(Optional.of(habitacion));
        mockMvc.perform(get("/habitaciones/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(habitacion)));
    }

    @Test
    void testCreateHabitacion() throws Exception {
        when(habitacionService.createHabitacion(any(Habitacion.class))).thenReturn(habitacion);
        mockMvc.perform(post("/habitaciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(habitacion)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(habitacion)));
    }

    @Test
    void testUpdateHabitacion() throws Exception {
        when(habitacionService.updateHabitacion(eq(1L), any(Habitacion.class))).thenReturn(habitacion);
        mockMvc.perform(put("/habitaciones/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(habitacion)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(habitacion)));
    }

    @Test
    void testDeleteHabitacion() throws Exception {
        mockMvc.perform(delete("/habitaciones/1"))
                .andExpect(status().isOk());
        verify(habitacionService).deleteHabitacion(1L);
    }
}
