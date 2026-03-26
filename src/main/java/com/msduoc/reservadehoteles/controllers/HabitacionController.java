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
    private List<Habitacion> habitaciones = new ArrayList<>();

    public HabitacionController() {
        habitaciones.add(new Habitacion(1, 201, true, TipoHabitacion.SIMPLE, 5000));
        habitaciones.add(new Habitacion(2, 202, true, TipoHabitacion.DOBLE, 10000));
        habitaciones.add(new Habitacion(3, 203, false, TipoHabitacion.SUITE, 15000));
    }

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


}
