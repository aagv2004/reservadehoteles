package com.msduoc.reservadehoteles.controllers;


import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.msduoc.reservadehoteles.models.Cliente;
import com.msduoc.reservadehoteles.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService clienteService;

    // Endpoints: GET

    @GetMapping
    public List<Cliente> getAllClientes() {
        log.info("/GET todos los clientes.");
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    public Optional<Cliente> getClienteById(@PathVariable Long id) {
        log.info("/GET detalle cliente id: {}", id);
        return clienteService.getClienteById(id);
    }

    // Endpoints: POST

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        log.info("/POST creando cliente: {} {}", cliente.getNombre(), cliente.getApellido());
        return clienteService.createCliente(cliente);
    }

    // Endpoints: PUT

    @PutMapping("/{id}")
    public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        log.info("/PUT actualizando cliente con id: {}", id);
        return clienteService.updateCliente(id, cliente);
    }

    // Endpoints: DELETE

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {
        log.warn("/DELETE eliminando cliente con id: {}", id);
        clienteService.deleteCliente(id);
    }
}
