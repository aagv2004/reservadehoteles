package com.msduoc.reservadehoteles.models;

import java.util.List;
import com.msduoc.reservadehoteles.enums.TipoHabitacion;

public class Habitacion {
    private int id;
    private int numeroHabitacion;
    private TipoHabitacion tipoHabitacion;
    private double precioPorNoche;
    private List<Reserva> reservas;

    public Habitacion(int id, int numeroHabitacion, TipoHabitacion tipoHabitacion,
            double precioPorNoche, List<Reserva> reservas) {
        this.id = id;
        this.numeroHabitacion = numeroHabitacion;
        this.tipoHabitacion = tipoHabitacion;
        this.precioPorNoche = precioPorNoche;
        this.reservas = reservas;
    }

    public int getId() {
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
