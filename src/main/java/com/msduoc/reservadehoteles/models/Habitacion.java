package com.msduoc.reservadehoteles.models;

import com.msduoc.reservadehoteles.enums.TipoHabitacion;

public class Habitacion {
    private int id;
    private int numeroHabitacion;
    private boolean activa;
    private TipoHabitacion tipoHabitacion;
    private double precioPorNoche;

    public Habitacion(int id, int numeroHabitacion, boolean activa, TipoHabitacion tipoHabitacion, double precioPorNoche) {
        this.id = id;
        this.numeroHabitacion = numeroHabitacion;
        this.activa = activa;
        this.tipoHabitacion = tipoHabitacion;
        this.precioPorNoche = precioPorNoche;
    } 

    public int getId() {
        return id;
    }
    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }
    public boolean getActiva() {
        return activa;
    }
    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }
    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        if (numeroHabitacion < 0) {
            System.out.println("Error de consistencia: el numero de habitación no puede ser menor que 0.");
        } else {
            this.numeroHabitacion = numeroHabitacion;
        }
    }
    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }
    public void setPrecioPorNoche(double precioPorNoche) {
        if (precioPorNoche < 0) {
            System.out.println("Error de consistencia: el precioPorNoche no puede ser menor que 0.");
        } else {
            this.precioPorNoche = precioPorNoche;
        }
    }
}
