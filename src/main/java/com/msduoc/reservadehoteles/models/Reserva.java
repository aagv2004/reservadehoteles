package com.msduoc.reservadehoteles.models;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.msduoc.reservadehoteles.enums.EstadoReserva;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserva_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "fecha_entrada")
    private LocalDate fechaEntrada;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "fecha_salida")
    private LocalDate fechaSalida;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "fecha_reserva")
    private LocalDate fechaReserva;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;


    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }
    
    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

}
