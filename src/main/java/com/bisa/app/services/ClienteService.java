package com.bisa.app.services;

import com.bisa.app.models.Cliente;

import java.util.List;
import java.util.UUID;

public interface ClienteService {
  List<Cliente> allClientes();
  Cliente getCliente(UUID uuid);
  Cliente createCliente(Cliente cliente);
  Cliente updateReferenciasPersonales(UUID clienteId, UUID referenciaId);
}
