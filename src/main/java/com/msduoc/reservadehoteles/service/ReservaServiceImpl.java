package com.msduoc.reservadehoteles.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.msduoc.reservadehoteles.enums.EstadoReserva;
import com.msduoc.reservadehoteles.models.Reserva;
import com.msduoc.reservadehoteles.repository.ClienteRepository;
import com.msduoc.reservadehoteles.repository.HabitacionRepository;
import com.msduoc.reservadehoteles.repository.ReservaRepository;
import com.msduoc.reservadehoteles.models.Cliente;
import com.msduoc.reservadehoteles.models.Habitacion;

@Service
public class ReservaServiceImpl implements ReservaService{
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Override
    public List<Reserva> getAllReservas() {
        if (reservaRepository.findAll().isEmpty()) {
            throw new RuntimeException("/GET No hay ninguna reserva.");
        }
        return reservaRepository.findAll();
    }

    @Override 
    public Optional<Reserva> getReservaById(Long id) {
        if (reservaRepository.findById(id) != null) {
            return reservaRepository.findById(id);
        } else {
            throw new RuntimeException("/GET id de reserva no encontrado para mostrar.");
        }
    }

    @Override
    public Reserva createReserva(Reserva reserva) {
        // El request me está entregando la habitación?
        if (reserva.getHabitacion() == null || reserva.getHabitacion().getId() == null) {
            throw new RuntimeException("/POST Debe especificar una habitación válida.");
        }

        // El request me está entregando el cliente?
        if (reserva.getCliente() == null || reserva.getCliente().getId() == null) {
            throw new RuntimeException("/POST Debe especificar un cliente válido.");
        }

        Long clienteId = reserva.getCliente().getId();
        Long habitacionId = reserva.getHabitacion().getId();

        Cliente cliExistente = clienteRepository.findById(clienteId).get();
        Habitacion habExistente = habitacionRepository.findById(habitacionId).get();

        // Mostrar datos de otras entidades
        reserva.setCliente(cliExistente);
        reserva.setHabitacion(habExistente);

        // Automatizar fecha y estado de reserva
        reserva.setFechaReserva(LocalDate.now());
        reserva.setEstado(EstadoReserva.PENDIENTE);

        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva updateReserva(Long id, Reserva reserva) {
        if (reservaRepository.existsById(id)) {
            Reserva existente = reservaRepository.findById(id).get();

            reserva.setId(id);
            if (reserva.getEstado() == null) {
                reserva.setEstado(existente.getEstado());
            }

            if (reserva.getFechaEntrada() == null) {
                reserva.setFechaEntrada(existente.getFechaEntrada());
            }

            if (reserva.getFechaSalida() == null) {
                reserva.setFechaSalida(existente.getFechaSalida());
            }

            if (reserva.getFechaReserva() == null) {
                reserva.setFechaReserva(existente.getFechaReserva());
            }

            if (reserva.getCliente() == null) {
                reserva.setCliente(existente.getCliente());
            }

            if (reserva.getHabitacion() == null) {
                reserva.setHabitacion(existente.getHabitacion());
            }

            return reservaRepository.save(reserva);
        } else {
            throw new RuntimeException("/PUT id de reserva no encontrado para actualizar.");
        }
    }

    @Override
    public void deleteReserva(Long id) {
        if (reservaRepository.findById(id) != null) {
            reservaRepository.deleteById(id);
        } else {
            throw new RuntimeException("/DELETE id de reserva no encontrado para eliminar.");
        }
    }

    @Override
    public List<Reserva> findByEstado(EstadoReserva estado) {
        List<Reserva> todas = reservaRepository.findAll();
        List<Reserva> filtradas = new ArrayList<>();

        for (Reserva res : todas) {
            if (res.getEstado().equals(estado)) {
                filtradas.add(res);
            }
        }

        return filtradas;
    }
}
