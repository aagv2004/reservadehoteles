package com.msduoc.reservadehoteles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.msduoc.reservadehoteles.enums.EstadoReserva;
import com.msduoc.reservadehoteles.models.Habitacion;
import com.msduoc.reservadehoteles.models.Reserva;
import com.msduoc.reservadehoteles.service.HabitacionService;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.util.ArrayList;


@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {
    @Autowired
    private HabitacionService habitacionService;
    
    // Endpoints: GET
    @GetMapping
    public List<Habitacion> getHabitaciones() {
        return habitacionService.getAllHabitaciones();
    }

    @GetMapping("/{id}")
    public Optional<Habitacion> getHabitacionPorId(@PathVariable Long id) {
        return habitacionService.getHabitacionById(id);
    }

    @GetMapping("/canceladas")
    public List<Habitacion> getCanceladas() {
        List<Habitacion> canceladas = new ArrayList<>();

        for (Habitacion hab : habitacionService.getAllHabitaciones()) {
            for (Reserva res : hab.getReservas()) {
                if (res.getEstado().equals(EstadoReserva.CANCELADA)) {
                    canceladas.add(hab);
                    break;
                }
            }
        }
        return canceladas;
    }

    @GetMapping("/disponibles")
    public List<Habitacion> getDisponibles(
        @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate entrada, 
        @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate salida
    ) {
        // Necesitamos todas y un array para guardar las disponibles
        List<Habitacion> todas = habitacionService.getAllHabitaciones();
        List<Habitacion> disponibles = new ArrayList<>();

        // Necesitamos recorrer todas para saber que de hecho existen
        for (Habitacion hab : todas) {
            boolean ocupada = false;

            if (hab.getReservas() == null || hab.getReservas().isEmpty()) {
                disponibles.add(hab);
                continue;
            }

            // Acá aplicamos el filtro, si la fecha de entrada (del request) es anterior a la fecha de salida (de la reserva)
            // Y, además la fecha de salida (del request) es posterior a la fecha de entrada (de la reserva), entonces está ocupada.
            for (Reserva res : hab.getReservas()) {
                if (entrada.isBefore(res.getFechaSalida()) && salida.isAfter(res.getFechaEntrada())) {
                    ocupada = true;
                    break;
                }
            }

            // Solo agregamos las que estén ocupada = false
            if (ocupada == false) {
                disponibles.add(hab);
            }
        }
        return disponibles;
    }

    // Endpoints: POST

    @PostMapping
    public Habitacion creaHabitacion(@RequestBody Habitacion habitacion) {
        return habitacionService.createHabitacion(habitacion);
    }

    // Endpoints: PUT
    
    @PutMapping("/{id}")
    public Habitacion updateHabitacion(@PathVariable Long id, Habitacion habitacion) {
        return habitacionService.updateHabitacion(id, habitacion);
    }

    // Endpoints: DELETE

    @DeleteMapping("/{id}")
    public void deleteHabitacion(@PathVariable Long id) {
        habitacionService.deleteHabitacion(id);
    }
}
