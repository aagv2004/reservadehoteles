package com.msduoc.reservadehoteles.models;

import com.msduoc.reservadehoteles.enums.EstadoReserva;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "estado")
    private EstadoReserva estado;

    @Column(name = "fechaEntrada")
    private String fechaEntrada;

    @Column(name = "fechaSalida")
    private String fechaSalida;

    @Column(name = "fechaReserva")
    private String fechaReserva;

    @Column(name = "cliente")
    private Cliente cliente;


    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
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

    public void setId(Long id) {
        this.id = id;
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
