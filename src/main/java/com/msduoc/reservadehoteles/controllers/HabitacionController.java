package com.msduoc.reservadehoteles.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.msduoc.reservadehoteles.enums.EstadoReserva;
import com.msduoc.reservadehoteles.enums.TipoHabitacion;
import com.msduoc.reservadehoteles.models.Cliente;
import com.msduoc.reservadehoteles.models.Habitacion;
import com.msduoc.reservadehoteles.models.Reserva;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HabitacionController {
    // "La tabla"
    private List<Habitacion> habitaciones = new ArrayList<>();



    // Poblamos esa "tabla"
    public HabitacionController() {
        // 1. Poblado de información: Al igual que con los packs de mascotas, 
        // aquí definimos los clientes una sola vez para reutilizarlos en las reservas.
        Cliente c1 = new Cliente(1, "Alejandro", "Gonzalez", 22);
        Cliente c2 = new Cliente(2, "Pedro", "Gonzalez", 45);
        Cliente c3 = new Cliente(3, "Rodrigo", "Vera", 25);
        Cliente c4 = new Cliente(4, "Eduardo", "Vergara", 32);
        Cliente c5 = new Cliente(5, "Juan", "Valdez", 33);

        // Crear reservas de habitaciones con List.of() para que esté más ordenado

        habitaciones.add(new Habitacion(1, 201, TipoHabitacion.SIMPLE, 5000, List.of(
        new Reserva(1, EstadoReserva.PENDIENTE, "01/01/2024", "05/02/2024", "12/12/2023", c1))));

        habitaciones.add(new Habitacion(2, 202, TipoHabitacion.DOBLE, 10000, List.of())); // Sin reservas

        habitaciones.add(new Habitacion(3, 203, TipoHabitacion.SUITE, 15000, List.of(
            new Reserva(2, EstadoReserva.COMPLETADA, "07/02/2024", "12/02/2024", "06/02/2024", c2)
        )));

        habitaciones.add(new Habitacion(4, 204, TipoHabitacion.SIMPLE, 5000, List.of())); // Sin reservas

        habitaciones.add(new Habitacion(5, 205, TipoHabitacion.SUITE, 15000, List.of(
        new Reserva(3, EstadoReserva.CONFIRMADA, "02/01/2025", "06/01/2025", "25/12/2024", c3))));

        habitaciones.add(new Habitacion(6, 206, TipoHabitacion.DOBLE, 10000, List.of())); // Sin reservas

        habitaciones.add(new Habitacion(7, 207, TipoHabitacion.SUITE, 15000, List.of(
        new Reserva(4, EstadoReserva.PENDIENTE, "05/10/2025", "09/10/2025", "25/09/2025", c4))));

        habitaciones.add(new Habitacion(8, 208, TipoHabitacion.SIMPLE, 5000, List.of(
        new Reserva(5, EstadoReserva.CANCELADA, "01/01/2022", "02/01/2022", "29/12/2021", c5))));
    }

    // Endpoints
    @GetMapping("/habitaciones")
    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    @GetMapping("/habitaciones/{id}")
    public Habitacion getHabitacionPorId(@PathVariable int id) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getId() == id) {
                return habitacion;
            }
        }
        return null;
    }

    @GetMapping("/habitaciones/canceladas")
    public List<Habitacion> getCanceladas() {
        List<Habitacion> canceladas = new ArrayList<>();

        for (Habitacion hab : habitaciones) {
            for (Reserva res : hab.getReservas()) {
                if (res.getEstado().equals(EstadoReserva.CANCELADA)) {
                    canceladas.add(hab);
                    break;
                }
            }
        }
        return canceladas;
    }

    @GetMapping("/habitaciones/disponibles")
    public List<Habitacion> getHabitacionesDisponibles(@RequestParam String fechaEntrada, @RequestParam String fechaSalida) {
        List<Habitacion> disponibles = new ArrayList<>();

        // Transformar a Date porque el perla usó String
        LocalDate busquedaEntrada = LocalDate.parse(fechaEntrada, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate busquedaSalida = LocalDate.parse(fechaSalida, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Recorrer habitaciones para revisar las reservas de esas habitaciones
        for (Habitacion hab : habitaciones) {
            boolean ocupada = false;

            for (Reserva res : hab.getReservas()) {

                // Transformar a Date también las fechas que tengan esas reservas
                LocalDate resEntrada = LocalDate.parse(res.getFechaEntrada(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalDate resSalida = LocalDate.parse(res.getFechaSalida(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                // Finalmente comparar si la fecha de entrada recibida es anterior a la salida de la reserva encontrada
                // y si la fecha de salida recibida es posterior a la fecha de entrada encontrada
                // Entonces significa que la habitación está ocupada en esas fechas por lo que no la mostrará.

                if (busquedaEntrada.isBefore(resSalida) && busquedaSalida.isAfter(resEntrada)) {
                    ocupada = true;
                    break;
                }
            }
            // Si no se cumple, la agrega a las disponibles.
            if (!ocupada) {
                disponibles.add(hab);
            }
        }
        return disponibles;
    }
    

}
