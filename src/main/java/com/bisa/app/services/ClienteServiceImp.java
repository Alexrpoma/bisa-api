package com.bisa.app.services;

import com.bisa.app.models.Cliente;
import com.bisa.app.models.Estado;
import com.bisa.app.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public record ClienteServiceImp(ClienteRepository clienteRepository) implements ClienteService{
  @Override
  public List<Cliente> allClientes() {
    return clienteRepository.findAll();
  }

  @Override
  public Cliente getCliente(UUID uuid) {
    return clienteRepository.findById(uuid)
        .orElseThrow(() -> new RuntimeException("The Client %s doesn't exist.".formatted(uuid)));
  }

  @Override
  public Cliente createCliente(Cliente cliente) {
    if(clienteRepository.existsClienteByEmail(cliente.getEmail())) {
      throw new RuntimeException("The email %s is taken!.".formatted(cliente.getEmail()));
    }
    cliente.setEstado(Estado.BLOQUEADO);
    return clienteRepository.save(cliente);
  }
}
