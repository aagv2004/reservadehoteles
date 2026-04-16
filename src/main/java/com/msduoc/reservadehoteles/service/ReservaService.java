package com.msduoc.reservadehoteles.service;

import java.util.List;
import java.util.Optional;
import com.msduoc.reservadehoteles.models.Reserva;

public interface ReservaService {
    List<Reserva> getAllReservas();
    Optional<Reserva> getReservaById(Long id);
    Reserva createReserva(Reserva reserva);
    Reserva updateReserva(Long id, Reserva reserva);
    void deleteReserva(Long id);
}
