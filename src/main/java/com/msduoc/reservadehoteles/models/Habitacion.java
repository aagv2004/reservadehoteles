package com.msduoc.reservadehoteles.models;

import java.util.List;
import com.msduoc.reservadehoteles.enums.TipoHabitacion;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Habitacion")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "habitacion_id")
    private Long id;

    @NotNull(message = "numero de habitación es un campo obligatorio.")
    @Positive(message = "El numero de habitación no puede ser menor que cero.")
    @Column(name = "numero_habitacion")
    private int numeroHabitacion;

    @NotNull(message = "tipo de habitación es un campo obligatorio.")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_habitacion")
    private TipoHabitacion tipoHabitacion;

    @NotNull(message = "precio por noche es un campo obligatorio.")
    @Positive(message = "el precio por noche no puede ser menor que cero.")
    @Column(name = "precio_noche")
    private double precioPorNoche;

    @OneToMany(mappedBy = "habitacion", cascade = CascadeType.ALL)
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

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
