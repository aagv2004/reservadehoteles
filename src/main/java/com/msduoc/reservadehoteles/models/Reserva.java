package com.msduoc.reservadehoteles.models;

// import java.util.Map;
enum EstadoReserva {
    Pendiente, Confirmada, Cancelada, Completada
}

public class Reserva {
    private int id;
    private EstadoReserva estado;
    private String fechaEntrada;
    private String fechaSalida;
    private String fechaReserva;
    private int clienteId;
    private int habitacionId;

    public Reserva(int id, EstadoReserva estado, String fechaEntrada, String fechaSalida, String fechaReserva, int clienteId, int habitacionId) {
        this.id = id;
        this.estado = estado;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.fechaReserva = fechaReserva;
        this.clienteId = clienteId;
        this.habitacionId = habitacionId;
    }

    public int getId() {
        return id;
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
    public int getClienteId() {
        return clienteId;
    }
    public int getHabitacionId() {
        return habitacionId;
    }

    public void setEstado(EstadoReserva estado) {
        if (estado == null) {
            System.out.println("Error de consistencia: El estado no puede estar vacío");
        } else {
            this.estado = estado;
        }
    }
    public void setFechaEntrada(String fechaEntrada) {
        if (fechaEntrada == null || fechaEntrada.trim().isEmpty()) {
            System.out.println("Error de consistencia: La fecha de entrada no puede estar vacía.");
        } else {
            this.fechaEntrada = fechaEntrada;
        }
    }
    public void setFechaSalida(String fechaSalida) {
        if (fechaSalida == null || fechaSalida.trim().isEmpty()) {
            System.out.println("Error de consistencia: La fecha de salida no puede estar vacía.");
        } else {
            this.fechaSalida = fechaSalida;
        }
    }
    public void setFechaReserva(String fechaReserva) {
        if (fechaReserva == null || fechaReserva.trim().isEmpty()) {
            System.out.println("Error de consistencia: La fecha de reserva no puede estar vacía.");
        } else {
            this.fechaReserva = fechaReserva;
        }
    }

}
