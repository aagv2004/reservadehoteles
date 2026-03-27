package com.msduoc.reservadehoteles.validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.msduoc.reservadehoteles.controllers.ClienteController;
import com.msduoc.reservadehoteles.controllers.HabitacionController;
import com.msduoc.reservadehoteles.controllers.ReservaController;
import com.msduoc.reservadehoteles.models.Cliente;
import com.msduoc.reservadehoteles.models.Habitacion;
import com.msduoc.reservadehoteles.models.Reserva;

// Archivo para no meter todo en el controller y poder usar capas
// No sé cómo funciona bien el Service y no lo hemos visto, no me quiero arriesgar.

public class ReservaValidator {

    // Acá voy a verificar que exista el cliente y que tenga más de 18 años,
    // Tengo entendido que se verifica que el check in lo haga un mayor de edad, más que nada por
    // eso. Static me permite usar funciones en otros archivos.

    public static Cliente validarClienteObtenerlo(int id) throws Exception {
        Cliente cliente = ClienteController.buscarPorId(id);
        if (cliente == null)
            throw new Exception("No existe un cliente con el ID: " + id);
        if (cliente.getEdad() < 18)
            throw new Exception("El cliente es menor de edad: " + cliente.getEdad());
        return cliente;
    }

    // Acá verifico que exista esa habitación específica.
    // Y verificaré que no se pueda reservar una habitación que tenga precioPorNoche < 0.

    public static Habitacion validarHabitacion(int id) throws Exception {
        Habitacion habitacion = HabitacionController.buscarPorId(id);
        if (habitacion == null)
            throw new Exception("No existe una habitación con el ID: " + id);
        if (habitacion.getPrecioPorNoche() <= 0)
            throw new Exception(
                    "No se puede crear una reserva si el precio de la habitación es 0 o es negativo.");
        return habitacion;
    }

    // Formatos para las fechas: un reggex que me tome 2 numeros, los separe por /, otros 2 numeros
    // y luego 4 numeros, esto es para consistencia más que nada ya que aparecía como requisito de
    // usuario.
    // en concreto: DD/MM/YYYY

    public static void validarFormatoFecha(String fecha) throws Exception {
        String formato = "\\d{2}/\\d{2}/\\d{4}";
        if (!fecha.matches(formato))
            throw new Exception(
                    "Formato de fecha inválido. Debe ser DD/MM/YYY, ejemplo: 26/03/2026");
    }

    // DateTimeFormatter y .ofPattern me permiten mostrarles el formato en el que está el string que
    // quiero cambiar
    // .parse(CharSequence text, DateTimeFormatter) me pide un String y un formato ¿LocalDate lo
    // transforma?

    public static LocalDate convertirFecha(String fechaStr) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(fechaStr, formato);
    }

    public static Reserva validarReservaObtenerla(int id) throws Exception {
        Reserva reserva = ReservaController.buscarPorId(id);
        if (reserva == null)
            throw new Exception("No existe una reserva con el ID: " + id);
        return reserva;
    }

}
