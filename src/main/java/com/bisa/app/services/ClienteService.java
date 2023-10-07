package com.bisa.app.services;

import com.bisa.app.dtos.ClienteCreadoDTO;
import com.bisa.app.dtos.ClientesAccesibilidadDTO;
import com.bisa.app.models.Cliente;
import com.bisa.app.models.UpdateReferenciaPersonal;

import java.util.List;
import java.util.UUID;

public interface ClienteService {
  List<Cliente> allClientes();
  List<ClientesAccesibilidadDTO> allClientesAccesibilidad();
  Cliente getCliente(UUID uuid);
  ClienteCreadoDTO createCliente(Cliente cliente);
  Cliente updateReferenciasPersonales(UUID clienteId, UpdateReferenciaPersonal updateReferenciaPersonal);
  void deleteReferenciaPersonal(UUID clienteId, UpdateReferenciaPersonal updateReferenciaPersonal);
}
