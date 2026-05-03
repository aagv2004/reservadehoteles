package com.msduoc.reservadehoteles.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ReservaModelTest {
    @Test
    void testGetterAndSetters() {
        Cliente cliente = new Cliente();
        cliente.setId(201L);
        cliente.setNombre("Alejandro");
        cliente.setApellido("Gonzalez");
        Reserva reserva = new Reserva();
        reserva.setId(1L);
        reserva.setCliente(cliente);

        assertEquals(1L, reserva.getId());
        assertEquals("Alejandro", reserva.getCliente().getNombre());
    }
}
