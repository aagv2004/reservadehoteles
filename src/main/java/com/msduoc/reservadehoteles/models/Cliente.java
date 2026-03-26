package com.msduoc.reservadehoteles.models;

public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    private int edad;

    public Cliente(int id, String nombre, String apellido, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public int getId() {
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

    public void setNombre(String nombre) {
        if (nombre == null || apellido.trim().isEmpty()) {
            System.out.println("Error de consistencia: el nombre no puede estar vacío.");
        } else {
            this.nombre = nombre;
        }
    }
    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            System.out.println("Error de consistencia: el apellido no puede estar vacío.");
        } else {
            this.apellido = apellido;
        }
    }
    public void setEdad(int edad) {
        if (edad < 0 || edad > 120) {
            System.out.println("Error: edad debe ser mayor que 0.");
        } else {
            this.edad = edad;
        }
    }

}
