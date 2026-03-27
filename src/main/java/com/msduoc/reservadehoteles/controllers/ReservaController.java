package com.msduoc.reservadehoteles.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.msduoc.reservadehoteles.enums.EstadoReserva;
import com.msduoc.reservadehoteles.models.Cliente;
import com.msduoc.reservadehoteles.models.Habitacion;
import com.msduoc.reservadehoteles.models.Reserva;
import com.msduoc.reservadehoteles.validators.ReservaValidator;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ReservaController {
    private final HabitacionController habitacionController;

    // "La tabla"
    private static List<Reserva> reservas = new ArrayList<>();

    // Quiero "crear" una especie de "autoincrementable" con esto
    private static int contadorId = 8;

    // Poblamos "la tabla"
    public ReservaController(ClienteController clienteController,
            HabitacionController habitacionController) {
        reservas.add(new Reserva(1, "01/01/2024", "05/02/2024", "12/12/2023", 1, 1));
        reservas.add(new Reserva(2, "06/03/2024", "12/03/2024", "03/03/2024", 2, 2));
        reservas.add(new Reserva(3, "01/04/2024", "04/04/2024", "22/03/2024", 2, 2));
        reservas.add(new Reserva(4, "06/03/2024", "12/03/2024", "03/03/2024", 2, 2));
        reservas.add(new Reserva(5, "06/03/2024", "12/03/2024", "03/03/2024", 2, 2));
        reservas.add(new Reserva(6, "06/03/2024", "12/03/2024", "03/03/2024", 2, 2));
        reservas.add(new Reserva(7, "06/03/2024", "12/03/2024", "03/03/2024", 2, 2));
        reservas.add(new Reserva(8, "06/03/2024", "12/03/2024", "03/03/2024", 2, 2));
        this.habitacionController = habitacionController;
    }

    // Funciones generales y flexibles
    public static Reserva buscarPorId(int id) {
        for (Reserva reserva : reservas) {
            if (reserva.getId() == id)
                return reserva;
        }
        return null;
    }

    public static boolean esHabitacionDisponible(int habitacionId, String fEntrada,
            String fSalida) {
        // Reformateamos fechas manejadas en string a dates para poder compararlas. (gracias a
        // convertirFecha)
        LocalDate nuevaEntrada = ReservaValidator.convertirFecha(fEntrada);
        LocalDate nuevaSalida = ReservaValidator.convertirFecha(fSalida);

        for (Reserva reserva : reservas) {
            if (reserva.getHabitacionId() == habitacionId
                    && reserva.getEstado() != EstadoReserva.CANCELADA) {
                LocalDate antiguaEntrada =
                        ReservaValidator.convertirFecha(reserva.getFechaEntrada());
                LocalDate antiguaSalida = ReservaValidator.convertirFecha(reserva.getFechaSalida());

                if (nuevaEntrada.isBefore(antiguaSalida) && nuevaSalida.isAfter(antiguaEntrada)) {
                    return false; // si me dan una nueva entrada antes de que termine la fSalida de
                                  // ESA RESERVA
                                  // Y además una nueva salida después de mi antigua entrada (cuando
                                  // ya empezó)
                                  // Es absurdo por lo que es falso, no tiene sentido.
                }
            }
        }
        return true;
    }

    // Endpoints
    @GetMapping("/reservas") // Muestra todo
    public List<Reserva> getReservas() {
        return reservas;
    }

    @GetMapping("/reservas/{id}") // Por Id
    public Reserva getReservaPorId(@PathVariable int id) {
        return buscarPorId(id);
    }

    @GetMapping("/reservas/crear") //
    public String crearReserva(@RequestParam("fechaEntrada") String nuevaFechaEntrada,
            @RequestParam("fechaSalida") String nuevaFechaSalida,
            @RequestParam("fechaReserva") String nuevaFechaReserva,
            @RequestParam("clienteId") int ocuparClienteId,
            @RequestParam("habitacionId") int ocuparHabitacionId) {
        try {
            ReservaValidator.validarFormatoFecha(nuevaFechaEntrada);
            ReservaValidator.validarFormatoFecha(nuevaFechaSalida);
            ReservaValidator.validarFormatoFecha(nuevaFechaReserva);

            Cliente clienteEncontrado =
                    ReservaValidator.validarClienteObtenerlo(ocuparHabitacionId);
            Habitacion habitacionEncontrada =
                    ReservaValidator.validarHabitacion(ocuparHabitacionId);

            int nuevoId = contadorId++;
            ocuparHabitacionId = habitacionEncontrada.getId();
            ocuparClienteId = clienteEncontrado.getId();
            Reserva nueva = new Reserva(nuevoId, nuevaFechaEntrada, nuevaFechaSalida,
                    nuevaFechaReserva, ocuparClienteId, ocuparHabitacionId);

            reservas.add(nueva);
            return "Reserva n°" + nuevoId + " Creada para el cliente "
                    + clienteEncontrado.getNombre() + " " + clienteEncontrado.getApellido();
        } catch (Exception e) {
            return "Error de consistencia: " + e.getMessage();
        }
    }

    @GetMapping("/reservas/cancelar")
    public String cancelarReserva(@RequestParam int id, @RequestParam EstadoReserva nuevoEstado) {
        try {
            Reserva porCancelar = ReservaValidator.validarReservaObtenerla(id);
            porCancelar.setEstado(nuevoEstado);

            return "Reserva n°" + id + " cancelada";
        } catch (Exception e) {
            return "Error al cancelar: " + e.getMessage();
        }
    }

    @GetMapping("/reservas")
    public List<Reserva> obtenerReservasDisponibles(@RequestParam String fechaEntrada,
            @RequestParam String fechaSalida) {
        for (Habitacion habitacion : HabitacionController.mostrarLista()) {
            if (esHabitacionDisponible(habitacion.getId(), fechaEntrada, fechaSalida)) {
                // hasta aquí quedé.
            }
        }

        return null;
    }



}
