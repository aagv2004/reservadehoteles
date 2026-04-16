package com.msduoc.reservadehoteles.models;


import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name="Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long id;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$", message = "Este campo es obligatorio y permite solo letras.")
    @Column(name = "nombre")
    private String nombre;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$", message = "Este campo es obligatorio y permite solo letras.")
    @Column(name = "apellido")
    private String apellido;

    @Positive(message = "La edad no puede ser menor que cero.")
    @Column(name = "edad")
    private int edad;

    @OneToMany(mappedBy = "cliente")
    private List<Reserva> reservas;

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

}
