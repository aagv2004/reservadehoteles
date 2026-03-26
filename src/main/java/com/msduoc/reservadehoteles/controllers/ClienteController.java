package com.msduoc.reservadehoteles.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.msduoc.reservadehoteles.models.Cliente;

import java.util.ArrayList;

@RestController
public class ClienteController {
    private List<Cliente> clientes = new ArrayList<>();

    public ClienteController() {
        clientes.add(new Cliente(1, "Alejandro", "Gonzalez", 22));
        clientes.add(new Cliente(2, "Pedro", "Gonzalez", 22));
        clientes.add(new Cliente(3, "Eduardo", "Vergara", 22));

    }

    @GetMapping("/clientes")
    public List<Cliente> getClientes() {
        return clientes;
    }

    @GetMapping("/clientes/{id}")
    public Cliente getClientePorId(@PathVariable int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

}
