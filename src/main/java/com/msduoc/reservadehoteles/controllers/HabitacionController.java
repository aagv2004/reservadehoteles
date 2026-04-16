package com.msduoc.reservadehoteles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.msduoc.reservadehoteles.enums.EstadoReserva;
// import com.msduoc.reservadehoteles.enums.TipoHabitacion;
// import com.msduoc.reservadehoteles.models.Cliente;
import com.msduoc.reservadehoteles.models.Habitacion;
// import com.msduoc.reservadehoteles.models.Reserva;
import com.msduoc.reservadehoteles.service.HabitacionService;
import java.util.List;
import java.util.Optional;
// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
// import java.util.ArrayList;
// import org.springframework.web.bind.annotation.RequestParam;


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

    // @GetMapping("/canceladas")
    // public List<Habitacion> getCanceladas() {
    //     List<Habitacion> canceladas = new ArrayList<>();

    //     for (Habitacion hab : habitaciones) {
    //         for (Reserva res : hab.getReservas()) {
    //             if (res.getEstado().equals(EstadoReserva.CANCELADA)) {
    //                 canceladas.add(hab);
    //                 break;
    //             }
    //         }
    //     }
    //     return canceladas;
    // }

    // @GetMapping("/habitaciones/disponibles")
    // public List<Habitacion> getHabitacionesDisponibles(@RequestParam String fechaEntrada, @RequestParam String fechaSalida) {
    //     List<Habitacion> disponibles = new ArrayList<>();

    //     // Transformar a Date porque el perla usó String
    //     LocalDate busquedaEntrada = LocalDate.parse(fechaEntrada, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    //     LocalDate busquedaSalida = LocalDate.parse(fechaSalida, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    //     // Recorrer habitaciones para revisar las reservas de esas habitaciones
    //     for (Habitacion hab : habitaciones) {
    //         boolean ocupada = false;

    //         for (Reserva res : hab.getReservas()) {

    //             // Transformar a Date también las fechas que tengan esas reservas
    //             LocalDate resEntrada = LocalDate.parse(res.getFechaEntrada(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    //             LocalDate resSalida = LocalDate.parse(res.getFechaSalida(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    //             // Finalmente comparar si la fecha de entrada recibida es anterior a la salida de la reserva encontrada
    //             // y si la fecha de salida recibida es posterior a la fecha de entrada encontrada
    //             // Entonces significa que la habitación está ocupada en esas fechas por lo que no la mostrará.

    //             if (busquedaEntrada.isBefore(resSalida) && busquedaSalida.isAfter(resEntrada)) {
    //                 ocupada = true;
    //                 break;
    //             }
    //         }
    //         // Si no se cumple, la agrega a las disponibles.
    //         if (!ocupada) {
    //             disponibles.add(hab);
    //         }
    //     }
    //     return disponibles;
    // }

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
