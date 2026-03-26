package com.msduoc.reservadehoteles.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.msduoc.reservadehoteles.enums.EstadoReserva;
import com.msduoc.reservadehoteles.models.Reserva;

@RestController
public class ReservaController {
    private List<Reserva> reservas = new ArrayList<>();

    public ReservaController() {
        reservas.add(new Reserva(1, EstadoReserva.COMPLETADA, "01/01/2024", "05/02/2024", "12/12/2023", 1, 1));
        reservas.add(new Reserva(2, EstadoReserva.CONFIRMADA, "06/03/2024", "12/03/2024", "03/03/2024", 2, 2));
        reservas.add(new Reserva(3, EstadoReserva.CANCELADA, "16/03/2024", "20/03/2024", "10/03/2024", 3, 3));
    }
    
    @GetMapping("/reservas")
    public List<Reserva> getReservas() {
        return reservas;
    }

    @GetMapping("/reservas/{id}")
    public Reserva getReservaPorId(@PathVariable int id) {
        for (Reserva reserva : reservas) {
            if (reserva.getId() == id) {
                return reserva;
            }
        }
        return null;
    }

}
