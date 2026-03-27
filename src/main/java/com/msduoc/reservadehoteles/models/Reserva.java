package com.msduoc.reservadehoteles.models;

import com.msduoc.reservadehoteles.enums.EstadoReserva;

public class Reserva {
    private int id;
    private EstadoReserva estado;
    private String fechaEntrada;
    private String fechaSalida;
    private String fechaReserva;
    private int clienteId;
    private int habitacionId;

    public Reserva(int id, String fechaEntrada, String fechaSalida, String fechaReserva,
            int clienteId, int habitacionId) {
        this.id = id;
        this.clienteId = clienteId;
        this.habitacionId = habitacionId;
        this.estado = EstadoReserva.PENDIENTE;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.fechaReserva = fechaReserva;
    }

    public int getId() {
        return id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public int getHabitacionId() {
        return habitacionId;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

}
