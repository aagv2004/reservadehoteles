package com.msduoc.reservadehoteles.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.msduoc.reservadehoteles.models.Habitacion;
import com.msduoc.reservadehoteles.models.Reserva;
import com.msduoc.reservadehoteles.repository.HabitacionRepository;
import com.msduoc.reservadehoteles.repository.ReservaRepository;

@Service
public class HabitacionServiceImpl implements HabitacionService {
    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public List<Habitacion> getAllHabitaciones() {
        if (habitacionRepository.findAll().isEmpty()) {
            throw new RuntimeException("/GET no hay habitaciones para mostrar.");
        }
        return habitacionRepository.findAll();
    }

    @Override
    public Optional<Habitacion> getHabitacionById(Long id) {
        if (habitacionRepository.findById(id) == null) {
            throw new RuntimeException("/GET id de habitación no encontrado para mostrar.");
        }
        return habitacionRepository.findById(id);
    }

    @Override
    public List<Habitacion> getHabitacionesDisponibles() {
        List<Habitacion> todas = habitacionRepository.findAll();
        List<Reserva> reservasActivas = reservaRepository.findAll();

        List<Habitacion> disponibles = new ArrayList<>();

        for (Habitacion hab : todas) {
            boolean estaOcupada = false;

            for (Reserva res : reservasActivas) {
                if (res.getHabitacion().getId().equals(hab.getId())) {
                    if (!res.getEstado().toString().equalsIgnoreCase("CANCELADA")) {
                        estaOcupada = true;
                        break;
                    }
                }
            }

            if (estaOcupada == false) {
                disponibles.add(hab);
            }
        }

        return disponibles;
    }

    @Override
    public Habitacion createHabitacion(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    @Override
    public Habitacion updateHabitacion(Long id, Habitacion habitacion) {
        if (habitacionRepository.existsById(id)) {
            Habitacion existente = habitacionRepository.findById(id).get();


            habitacion.setId(id);

            if (habitacion.getNumeroHabitacion() == 0) {
                habitacion.setNumeroHabitacion(existente.getNumeroHabitacion());
            }

            if (habitacion.getTipoHabitacion() == null) {
                habitacion.setTipoHabitacion(existente.getTipoHabitacion());
            }

            if (habitacion.getPrecioPorNoche() == 0) {
                habitacion.setPrecioPorNoche(existente.getPrecioPorNoche());
            }

            return habitacionRepository.save(habitacion);
        } else {
            throw new RuntimeException("/PUT id de habitación no encontrado para actualizar.");
        }
    }

    @Override
    public void deleteHabitacion(Long id) {
        if (habitacionRepository.findById(id) == null) {
            throw new RuntimeException("/DELETE id de habitación no encontrado para eliminar.");
        }
        habitacionRepository.deleteById(id);
    }
}
