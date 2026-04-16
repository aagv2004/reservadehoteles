package com.msduoc.reservadehoteles.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.msduoc.reservadehoteles.models.Habitacion;
import com.msduoc.reservadehoteles.repository.HabitacionRepository;

@Service
public class HabitacionServiceImpl implements HabitacionService {
    @Autowired
    private HabitacionRepository habitacionRepository;

    @Override
    public List<Habitacion> getAllHabitaciones() {
        return habitacionRepository.findAll();
    }

    @Override
    public Optional<Habitacion> getHabitacionById(Long id) {
        return habitacionRepository.findById(id);
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

            if (habitacion.getReservas() == null) {
                habitacion.setReservas(existente.getReservas());
            }

            return habitacionRepository.save(habitacion);
        } else {
            return null;
        }
    }

    @Override
    public void deleteHabitacion(Long id) {
        habitacionRepository.deleteById(id);
    }
}
