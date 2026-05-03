package com.msduoc.reservadehoteles.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.msduoc.reservadehoteles.enums.EstadoReserva;
import com.msduoc.reservadehoteles.models.Habitacion;
import com.msduoc.reservadehoteles.models.Reserva;
import com.msduoc.reservadehoteles.repository.HabitacionRepository;
import com.msduoc.reservadehoteles.repository.ReservaRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class HabitacionServiceImplTest {
    
    @Mock
    private HabitacionRepository habitacionRepository;

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private HabitacionServiceImpl habitacionService;

    private Habitacion habitacion;

    @BeforeEach
    void setUp() {
        habitacion = new Habitacion();
        habitacion.setId(2L);
        habitacion.setNumeroHabitacion(101);
        habitacion.setPrecioPorNoche(50000);
    }

    @Test
    void testsGetAllHabitaciones() {
        List<Habitacion> expected = Arrays.asList(habitacion);
        when(habitacionRepository.findAll()).thenReturn(expected);
        assertEquals(expected, habitacionService.getAllHabitaciones());

    }

    @Test
    void testGetHabitacionesDisponibles() {
        Habitacion h1 = new Habitacion();
        h1.setId(1L);
        Habitacion h2 = new Habitacion();
        h2.setId(2L);

        Reserva reservaActiva = new Reserva();
        reservaActiva.setHabitacion(h1);
        reservaActiva.setEstado(EstadoReserva.CONFIRMADA);

        when(habitacionRepository.findAll()).thenReturn(Arrays.asList(h1, h2));
        when(reservaRepository.findAll()).thenReturn(Arrays.asList(reservaActiva));

        List<Habitacion> disponibles = habitacionService.getHabitacionesDisponibles();

        assertEquals(1, disponibles.size());
        assertEquals(2L, disponibles.get(0).getId());
    }

    @Test
    void testCreateHabitacion() {
        when(habitacionRepository.save(any(Habitacion.class))).thenReturn(habitacion);
        Habitacion creada = habitacionService.createHabitacion(habitacion);
        assertNotNull(creada);
        assertEquals(101, creada.getNumeroHabitacion());
    }

    @Test
    void testUpdateHabitacionExists() {
        when(habitacionRepository.existsById(2L)).thenReturn(true);
        when(habitacionRepository.findById(2L)).thenReturn(Optional.of(habitacion));

        when(habitacionRepository.save(habitacion)).thenReturn(habitacion);

        Habitacion result = habitacionService.updateHabitacion(2L, habitacion);

        assertEquals(2L, habitacion.getId());
        assertEquals(habitacion, result);
        verify(habitacionRepository).save(habitacion);
    }

    @Test
    void testUpdateHabitacionNotExists() {
        when(habitacionRepository.existsById(2L)).thenReturn(false);
        
        assertThrows(RuntimeException.class, () -> habitacionService.updateHabitacion(2L, habitacion));
        verify(habitacionRepository, never()).save(any());
    }

    @Test
    void testDeleteHabitacion() {
        habitacionService.deleteHabitacion(2L);
        verify(habitacionRepository).deleteById(2L);
    }


}
