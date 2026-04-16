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
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> getClienteById(Long id) {
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
            return null;
        }
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
