package com.bisa.app.services;

import com.bisa.app.dtos.ClientesAccesibilidadDTO;
import com.bisa.app.dtos.ClientesAccesibilidadDTOMapper;
import com.bisa.app.exceptions.DuplicateResourceException;
import com.bisa.app.exceptions.NotFoundException;
import com.bisa.app.models.*;
import com.bisa.app.repositories.ClienteRepository;
import com.bisa.app.repositories.PersonaRepository;
import com.bisa.app.validators.ClienteAgeValidator;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public record ClienteServiceImp(
    ClienteRepository clienteRepository,
    PersonaRepository personaRepository,
    ClientesAccesibilidadDTOMapper clientesAccesibilidadDTOMapper
) implements ClienteService{
  @Override
  public List<Cliente> allClientes() {
    return clienteRepository.findAll();
  }

  @Override
  public List<ClientesAccesibilidadDTO> allClientesAccesibilidad() {
    return clienteRepository.findAll().stream()
        .map(clientesAccesibilidadDTOMapper)
        .toList();
  }

  @Override
  public Cliente getCliente(UUID uuid) {
    return clienteRepository.findById(uuid)
        .orElseThrow(() -> new NotFoundException("The Client %s doesn't exist.".formatted(uuid)));
  }

  @Override
  public Cliente createCliente(Cliente cliente) {
    if(clienteRepository.existsClienteByEmail(cliente.getEmail())) {
      throw new DuplicateResourceException("The email %s is taken!.".formatted(cliente.getEmail()));
    }
    ClienteAgeValidator.validate(cliente);
    cliente.setReferenciasPersonales(
        ReferenciasPersonales.builder()
            .listReferenciaCliente(new HashSet<>())
            .listReferenciaPersona(new HashSet<>())
            .totalReferencias(0)
            .referenciasClientes(0)
            .build()
    );
    cliente.setEstado(Estado.BLOQUEADO);
    cliente.setAccesibilidad(Accesibilidad.NULA);
    return clienteRepository.save(cliente);
  }

  @Override
  public Cliente updateReferenciasPersonales(UUID clienteId, UpdateReferenciaPersonal updateReferenciaPersonal) {
    UUID referenciaId = updateReferenciaPersonal.referenciaId();
    boolean existsRefereciaId = false;
    if (clienteId.equals(referenciaId)) {
      throw new DuplicateResourceException("The Client %s can't be a reference of himself.".formatted(clienteId));
    }
    Cliente cliente = clienteRepository.findById(clienteId)
        .orElseThrow(
            () -> new NotFoundException("The Client %s doesn't exist.".formatted(clienteId))
        );
    if (cliente.getInformacionPersonal().getId().equals(referenciaId)) {
      throw new DuplicateResourceException("The Client %s can't be a reference of himself.".formatted(clienteId));
    }
    boolean existsReferenciaCliente = clienteRepository.existsById(referenciaId);
    if (existsReferenciaCliente) {
      cliente.getReferenciasPersonales().getListReferenciaCliente().add(referenciaId);
      cliente.getReferenciasPersonales().setReferenciasClientes(
          cliente.getReferenciasPersonales().getListReferenciaCliente().size()
      );
      cliente.getReferenciasPersonales().setTotalReferencias(
          cliente.getReferenciasPersonales().getTotalReferencias() + 1
      );
      existsRefereciaId = true;
    }
    boolean existsReferenciaPersona = personaRepository.existsById(referenciaId);
    if (existsReferenciaPersona) {
      cliente.getReferenciasPersonales().getListReferenciaPersona().add(referenciaId);
      cliente.getReferenciasPersonales().setTotalReferencias(
          cliente.getReferenciasPersonales().getListReferenciaPersona().size()
      );
      cliente.getReferenciasPersonales().setTotalReferencias(
          cliente.getReferenciasPersonales().getTotalReferencias() + 1
      );
      existsRefereciaId = true;
    }
    if (!existsRefereciaId) {
      throw new NotFoundException("The reference %s doesn't exist.".formatted(referenciaId));
    }

    cliente.setEstado(Estado.ACTIVO);

    int totalReferencias = cliente.getReferenciasPersonales().getTotalReferencias();
    int referenciasClientes = cliente.getReferenciasPersonales().getReferenciasClientes();

    if (totalReferencias >= 2 && referenciasClientes >= 2 || totalReferencias >= 3 && referenciasClientes >= 1) {
      cliente.setAccesibilidad(Accesibilidad.BUENA);
    }
    if (totalReferencias >= 2 && referenciasClientes == 0 || totalReferencias == 1 && referenciasClientes == 1) {
      cliente.setAccesibilidad(Accesibilidad.REGULAR);
    }
    if (totalReferencias == 1 && referenciasClientes == 0) {
      cliente.setAccesibilidad(Accesibilidad.MALA);
    }
    if (totalReferencias == 0 && referenciasClientes == 0) {
      cliente.setAccesibilidad(Accesibilidad.NULA);
    }
    return clienteRepository.save(cliente);
  }

  @Override
  public void deleteReferenciaPersonal(UUID clienteId, UpdateReferenciaPersonal updateReferenciaPersonal) {
    UUID referenciaId = updateReferenciaPersonal.referenciaId();
    boolean existsRefereciaId = false;
    if (clienteId.equals(referenciaId)) {
      throw new DuplicateResourceException("The Client %s can't be a reference of himself.".formatted(clienteId));
    }
    Cliente cliente = clienteRepository.findById(clienteId)
        .orElseThrow(
            () -> new NotFoundException("The Client %s doesn't exist.".formatted(clienteId))
        );
    if (cliente.getInformacionPersonal().getId().equals(referenciaId)) {
      throw new DuplicateResourceException("The Client %s can't be a reference of himself.".formatted(clienteId));
    }
    boolean existsReferenciaCliente = clienteRepository.existsById(referenciaId);
    if (existsReferenciaCliente && cliente.getReferenciasPersonales().getListReferenciaCliente().contains(referenciaId)) {
      cliente.getReferenciasPersonales().getListReferenciaCliente().remove(referenciaId);
      cliente.getReferenciasPersonales().setReferenciasClientes(
          cliente.getReferenciasPersonales().getListReferenciaCliente().size()
      );
      cliente.getReferenciasPersonales().setTotalReferencias(
          cliente.getReferenciasPersonales().getTotalReferencias() - 1
      );
      existsRefereciaId = true;
    }
    boolean existsReferenciaPersona = personaRepository.existsById(referenciaId);
    if (existsReferenciaPersona && cliente.getReferenciasPersonales().getListReferenciaPersona().contains(referenciaId)) {
      cliente.getReferenciasPersonales().getListReferenciaPersona().remove(referenciaId);
      cliente.getReferenciasPersonales().setTotalReferencias(
          cliente.getReferenciasPersonales().getTotalReferencias() - 1
      );
      existsRefereciaId = true;
    }

    if (!existsRefereciaId) {
      throw new NotFoundException("The reference %s doesn't exist.".formatted(referenciaId));
    }

    int totalReferencias = cliente.getReferenciasPersonales().getTotalReferencias();
    int referenciasClientes = cliente.getReferenciasPersonales().getReferenciasClientes();

    if (totalReferencias >= 2 && referenciasClientes >= 2 || totalReferencias >= 3 && referenciasClientes >= 1) {
      cliente.setAccesibilidad(Accesibilidad.BUENA);
    }
    if (totalReferencias >= 2 && referenciasClientes == 0 || totalReferencias == 1 && referenciasClientes == 1) {
      cliente.setAccesibilidad(Accesibilidad.REGULAR);
    }
    if (totalReferencias == 1 && referenciasClientes == 0) {
      cliente.setAccesibilidad(Accesibilidad.MALA);
    }
    if (totalReferencias == 0 && referenciasClientes == 0) {
      cliente.setAccesibilidad(Accesibilidad.NULA);
    }
    if (totalReferencias == 0 && referenciasClientes == 0) {
      cliente.setEstado(Estado.BLOQUEADO);
    }
    clienteRepository.save(cliente);
  }
}
