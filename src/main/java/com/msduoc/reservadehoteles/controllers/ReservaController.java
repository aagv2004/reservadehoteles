package com.msduoc.reservadehoteles.controllers;

import java.util.List;
import java.util.Optional;
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
import com.msduoc.reservadehoteles.enums.EstadoReserva;
import com.msduoc.reservadehoteles.models.Reserva;
import com.msduoc.reservadehoteles.service.ReservaService;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    private static final Logger log = LoggerFactory.getLogger(ReservaController.class);

    @Autowired
    private ReservaService reservaService;

    // Endpoints: GET
    @GetMapping
    public List<Reserva> getAllReservas() {
        log.info("/GET todas las reservas");
        return reservaService.getAllReservas();
    }

    @GetMapping("/{id}")
    public Optional<Reserva> getReservaById(@PathVariable Long id) {
        log.info("/GET detalle reserva id: {}", id);
        return reservaService.getReservaById(id);
    }

    @GetMapping("/estado/{estado}")
    public List<Reserva> getReservasByEstado(@PathVariable String estado) {
        EstadoReserva estadoEnum = EstadoReserva.valueOf(estado.toUpperCase());
        return reservaService.findByEstado(estadoEnum);
    }

    // Endpoints: POST

    @PostMapping
    public Reserva createReserva(@RequestBody Reserva reserva) {
        log.info("/POST creando reserva asociada al cliente con id: {}", reserva.getCliente().getId());
        return reservaService.createReserva(reserva);
    }

    // Endpoints: PUT

    @PutMapping("/{id}")
    public Reserva updateReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
        log.info("/PUT actualizando reserva con id: {}", id);
        return reservaService.updateReserva(id, reserva);
    }

    // Endpoints: DELETE

    @DeleteMapping("/{id}")
    public void deleteReserva(@PathVariable Long id) {
        log.warn("/DELETE eliminando reserva con id: {}", id);
        reservaService.deleteReserva(id);
    }
}
