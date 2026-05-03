package com.msduoc.reservadehoteles.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class HabitacionModelTest {
    @Test
    void testGetterAndSetters() {
        Habitacion habitacion = new Habitacion();
        habitacion.setId(1L);
        habitacion.setNumeroHabitacion(1);

        assertEquals(1L, habitacion.getId());
        assertEquals(1, habitacion.getNumeroHabitacion());
    }
}
