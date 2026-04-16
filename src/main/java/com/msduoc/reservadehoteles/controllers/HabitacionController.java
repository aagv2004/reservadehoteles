package com.msduoc.reservadehoteles.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.msduoc.reservadehoteles.models.Habitacion;
import com.msduoc.reservadehoteles.service.HabitacionService;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {
    private static final Logger log = LoggerFactory.getLogger(HabitacionController.class);
    @Autowired
    private HabitacionService habitacionService;
    
    // Endpoints: GET
    @GetMapping
    public List<Habitacion> getHabitaciones() {
        log.info("/GET todas las habitaciones.");
        return habitacionService.getAllHabitaciones();
    }

    @GetMapping("/{id}")
    public Optional<Habitacion> getHabitacionPorId(@PathVariable Long id) {
        log.info("/GET habitación por id.");
        return habitacionService.getHabitacionById(id);
    }

    @GetMapping("/disponibles")
    public List<Habitacion> getDisponibles() {
        log.info("/GET habitaciones disponibles.");
        return habitacionService.getHabitacionesDisponibles();
    }

    // Endpoints: POST

    @PostMapping
    public Habitacion creaHabitacion(@RequestBody Habitacion habitacion) {
        log.info("/POST creando habitación número: {}", habitacion.getNumeroHabitacion());
        return habitacionService.createHabitacion(habitacion);
    }

    // Endpoints: PUT
    
    @PutMapping("/{id}")
    public Habitacion updateHabitacion(@PathVariable Long id, Habitacion habitacion) {
        log.info("/PUT actualizando habitación número: {}", habitacion.getNumeroHabitacion());
        return habitacionService.updateHabitacion(id, habitacion);
    }

    // Endpoints: DELETE

    @DeleteMapping("/{id}")
    public void deleteHabitacion(@PathVariable Long id) {
        log.info("/DELETE eliminando habitación con id: {}", id);
        habitacionService.deleteHabitacion(id);
    }
}
