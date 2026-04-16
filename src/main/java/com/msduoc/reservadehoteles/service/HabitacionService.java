package com.msduoc.reservadehoteles.service;

import java.util.List;
import java.util.Optional;
import com.msduoc.reservadehoteles.models.Habitacion;

public interface HabitacionService {
    List<Habitacion> getAllHabitaciones();
    Optional<Habitacion> getHabitacionById(Long id);
    Habitacion createHabitacion(Habitacion habitacion);
    Habitacion updateHabitacion(Long id, Habitacion habitacion);
    void deleteHabitacion(Long id);
    
}
