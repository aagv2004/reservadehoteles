package com.msduoc.reservadehoteles.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.msduoc.reservadehoteles.models.Reserva;
import com.msduoc.reservadehoteles.models.Cliente;
import com.msduoc.reservadehoteles.models.Habitacion;
import com.msduoc.reservadehoteles.enums.EstadoReserva;
import com.msduoc.reservadehoteles.repository.ReservaRepository;
import com.msduoc.reservadehoteles.repository.ClienteRepository;
import com.msduoc.reservadehoteles.repository.HabitacionRepository;

@ExtendWith(MockitoExtension.class)
public class ReservaServiceImplTest {

    @Mock
    private ReservaRepository reservaRepository;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private HabitacionRepository habitacionRepository;

    @InjectMocks
    private ReservaServiceImpl reservaService;

    private Reserva reserva;
    private Cliente cliente;
    private Habitacion habitacion;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);

        habitacion = new Habitacion();
        habitacion.setId(1L);

        reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setHabitacion(habitacion);
    }

    @Test
    void testGetAllReservas() {
        List<Reserva> expected = Arrays.asList(reserva);
        when(reservaRepository.findAll()).thenReturn(expected);
        assertEquals(expected, reservaService.getAllReservas());
        
    }

    @Test
    void testGetReservaById() {
        when(reservaRepository.findById(3L)).thenReturn(Optional.of(reserva));
        assertEquals(Optional.of(reserva), reservaService.getReservaById(3L));
    }

    @Test
    void testCreateReserva() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(habitacionRepository.findById(1L)).thenReturn(Optional.of(habitacion));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        Reserva creada = reservaService.createReserva(reserva);

        assertNotNull(creada);
        assertEquals(EstadoReserva.PENDIENTE, creada.getEstado());
        verify(reservaRepository).save(any(Reserva.class));
    }

    @Test
    void testCreateReservaSinHabitacionThrowsException() {
        reserva.setHabitacion(null);
        
        assertThrows(RuntimeException.class, () -> {
            reservaService.createReserva(reserva);
        });
    }

    @Test
    void testDeleteReserva() {
        reservaService.deleteReserva(1L);
        verify(reservaRepository).deleteById(1L);
    }
}