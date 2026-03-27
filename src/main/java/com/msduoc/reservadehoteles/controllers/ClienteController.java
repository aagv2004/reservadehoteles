package com.msduoc.reservadehoteles.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.msduoc.reservadehoteles.models.Cliente;

import java.util.ArrayList;

@RestController
public class ClienteController {
    // Lista que almacena los objetos (la tabla)
    private static List<Cliente> clientes = new ArrayList<>();

    // Poblar la "tabla"
    public ClienteController() {
        clientes.add(new Cliente(1, "Alejandro", "Gonzalez", 22));
        clientes.add(new Cliente(2, "Pedro", "Gonzalez", 50));
        clientes.add(new Cliente(3, "Eduardo", "Vergara", 32));
        clientes.add(new Cliente(4, "Juan", "Villavicencio", 45));
        clientes.add(new Cliente(5, "Diego", "Alcayaga", 25));
        clientes.add(new Cliente(6, "Karen", "Rodríguez", 22));
        clientes.add(new Cliente(7, "Tamara", "Gonzalez", 23));
        clientes.add(new Cliente(8, "Dalia", "Sepúlveda", 33));
    }

    // Funciones generales y de uso "flexible"
    public static Cliente buscarPorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    // Endpoints
    @GetMapping("/clientes")
    public List<Cliente> getClientes() {
        return clientes;
    }

    @GetMapping("/clientes/{id}")
    public Cliente getClientePorId(@PathVariable int id) {
        return buscarPorId(id);
    }

}
