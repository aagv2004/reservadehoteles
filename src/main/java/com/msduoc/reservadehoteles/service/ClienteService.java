package com.msduoc.reservadehoteles.service;

import java.util.List;
import java.util.Optional;
import com.msduoc.reservadehoteles.models.Cliente;

public interface ClienteService {
    List<Cliente> getAllClientes();
    Optional<Cliente> getClienteById(Long id);
    Cliente createCliente(Cliente cliente);
    Cliente updateCliente(Long id, Cliente cliente);
    void deleteCliente(Long id);
}
