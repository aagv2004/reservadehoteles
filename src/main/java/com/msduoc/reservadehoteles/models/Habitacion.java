package com.msduoc.reservadehoteles.models;

import java.util.List;
import com.msduoc.reservadehoteles.enums.TipoHabitacion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Habitacion")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numeroHabitacion")
    private int numeroHabitacion;

    @Column(name = "tipoHabitacion")
    private TipoHabitacion tipoHabitacion;

    @Column(name = "precioPorNoche")
    private double precioPorNoche;

    @Column(name = "reservas")
    private List<Reserva> reservas;

    public Long getId() {
        return id;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public void setPrecioPorNoche(double precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }
}
