package com.msduoc.reservadehoteles.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.msduoc.reservadehoteles.enums.TipoHabitacion;
import com.msduoc.reservadehoteles.models.Habitacion;

import java.util.List;
import java.util.ArrayList;

@RestController
public class HabitacionController {
    // "La tabla"
    private static List<Habitacion> habitaciones = new ArrayList<>();

    // Poblamos esa "tabla"
    public HabitacionController() {
        habitaciones.add(new Habitacion(1, 201, TipoHabitacion.SIMPLE, 5000));
        habitaciones.add(new Habitacion(2, 202, TipoHabitacion.DOBLE, 10000));
        habitaciones.add(new Habitacion(3, 203, TipoHabitacion.SUITE, 15000));
        habitaciones.add(new Habitacion(4, 204, TipoHabitacion.SIMPLE, 5000));
        habitaciones.add(new Habitacion(5, 205, TipoHabitacion.SUITE, 15000));
        habitaciones.add(new Habitacion(6, 206, TipoHabitacion.DOBLE, 10000));
        habitaciones.add(new Habitacion(7, 207, TipoHabitacion.SUITE, 15000));
        habitaciones.add(new Habitacion(8, 208, TipoHabitacion.SIMPLE, 5000));
    }

    // Funciones generales y de uso "flexible"
    public static Habitacion buscarPorId(int id) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getId() == id) {
                return habitacion;
            }
        }
        return null;
    }

    public static List<Habitacion> mostrarLista() {
        return habitaciones;
    }

    // Endpoints
    @GetMapping("/habitaciones")
    public List<Habitacion> getHabitaciones() {
        return mostrarLista();
    }

    @GetMapping("/habitaciones/{id}")
    public Habitacion getHabitacionPorId(@PathVariable int id) {
        return buscarPorId(id);
    }


}
