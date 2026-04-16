package com.msduoc.reservadehoteles.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.msduoc.reservadehoteles.models.Reserva;
import com.msduoc.reservadehoteles.repository.ReservaRepository;

@Service
public class ReservaServiceImpl implements ReservaService{
    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    @Override 
    public Optional<Reserva> getReservaById(Long id) {
        return reservaRepository.findById(id);
    }

    @Override
    public Reserva createReserva(Reserva reserva) {
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
            return null;
        }
    }

    @Override
    public void deleteReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}
