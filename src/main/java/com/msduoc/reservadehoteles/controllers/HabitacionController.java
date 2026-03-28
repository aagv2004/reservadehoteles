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
        List<Reserva> reservasHabitacion201 = new ArrayList<>();
        List<Reserva> reservasHabitacion202 = new ArrayList<>();
        List<Reserva> reservasHabitacion203 = new ArrayList<>();
        List<Reserva> reservasHabitacion204 = new ArrayList<>();
        List<Reserva> reservasHabitacion205 = new ArrayList<>();
        List<Reserva> reservasHabitacion206 = new ArrayList<>();
        List<Reserva> reservasHabitacion207 = new ArrayList<>();
        List<Reserva> reservasHabitacion208 = new ArrayList<>();

        // Habitaciones disponibles pares de 200, 202, 204, 206, 208
        reservasHabitacion201.add(new Reserva(
            1, 
            EstadoReserva.PENDIENTE, 
            "01/01/2024", 
            "05/02/2024", 
            "12/12/2023", 
            new Cliente(1, "Alejandro", "Gonzalez", 22)));
        reservasHabitacion203.add(new Reserva(
            2, 
            EstadoReserva.COMPLETADA, 
            "07/02/2024", 
            "12/02/2024", 
            "06/02/2024",
            new Cliente(2, "Pedro", "Gonzalez", 45)));
        reservasHabitacion205.add(new Reserva(
            3, 
            EstadoReserva.CONFIRMADA, 
            "02/01/2025", 
            "06/01/2025", 
            "25/12/2024", 
            new Cliente(3, "Rodrigo", "Vera", 25)));
        reservasHabitacion207.add(new Reserva(
            4, 
            EstadoReserva.PENDIENTE, 
            "05/10/2025", 
            "09/10/2025", 
            "25/09/2025",
            new Cliente(4, "Eduardo", "Vergara", 32)));
        reservasHabitacion208.add(new Reserva(
            5, 
            EstadoReserva.CANCELADA, 
            "01/01/2022", 
            "02/01/2022", 
            "29/12/2021", 
            new Cliente(5, "Juan", "Valdez", 33)));


        habitaciones.add(new Habitacion(1, 201, TipoHabitacion.SIMPLE, 5000,reservasHabitacion201));
        habitaciones.add(new Habitacion(2, 202, TipoHabitacion.DOBLE, 10000, reservasHabitacion202));
        habitaciones.add(new Habitacion(3, 203, TipoHabitacion.SUITE, 15000, reservasHabitacion203));
        habitaciones.add(new Habitacion(4, 204, TipoHabitacion.SIMPLE, 5000, reservasHabitacion204));
        habitaciones.add(new Habitacion(5, 205, TipoHabitacion.SUITE, 15000, reservasHabitacion205));
        habitaciones.add(new Habitacion(6, 206, TipoHabitacion.DOBLE, 10000, reservasHabitacion206));
        habitaciones.add(new Habitacion(7, 207, TipoHabitacion.SUITE, 15000, reservasHabitacion207));
        habitaciones.add(new Habitacion(8, 208, TipoHabitacion.SIMPLE, 5000, reservasHabitacion208));
    }

    // Comparar fechas para ver si están disponibles las habitaciones:


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

        LocalDate busquedaEntrada = LocalDate.parse(fechaEntrada, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate busquedaSalida = LocalDate.parse(fechaSalida, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        for (Habitacion hab : habitaciones) {
            boolean ocupada = false;

            for (Reserva res : hab.getReservas()) {
                LocalDate resEntrada = LocalDate.parse(res.getFechaEntrada(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalDate resSalida = LocalDate.parse(res.getFechaSalida(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                if (busquedaEntrada.isBefore(resSalida) && busquedaSalida.isAfter(resEntrada)) {
                    ocupada = true;
                    break;
                }
            }
            if (!ocupada) {
                disponibles.add(hab);
            }
        }
        return disponibles;
    }
    

}
