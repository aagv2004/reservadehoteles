package com.msduoc.reservadehoteles.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.msduoc.reservadehoteles.models.Cliente;
import com.msduoc.reservadehoteles.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService{
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getAllClientes() {
        if (clienteRepository.findAll().isEmpty()) {
            throw new RuntimeException("/GET no hay clientes para mostrar.");
        }
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> getClienteById(Long id) {
        if (clienteRepository.findById(id) == null) {
            throw new RuntimeException("/GET id de cliente no encontrado para mostrar.");
        }
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateCliente(Long id, Cliente cliente) {
        if (clienteRepository.existsById(id)) {
            Cliente existente = clienteRepository.findById(id).get();

            cliente.setId(id);
            if (cliente.getNombre() == null) {
                cliente.setNombre(existente.getNombre());
            }

            if (cliente.getApellido() == null) {
                cliente.setApellido(existente.getApellido());
            }

            if (cliente.getEdad() == 0) {
                cliente.setEdad(existente.getEdad());
            }

            return clienteRepository.save(cliente);
        } else {
            throw new RuntimeException("/PUT id de cliente no encontrado para actualizar.");
        }
    }

    @Override
    public void deleteCliente(Long id) {
        if (clienteRepository.findById(id) == null) {
            throw new RuntimeException("/DELETE id de cliente no encontrado para eliminar.");
        }
        clienteRepository.deleteById(id);
    }
}
