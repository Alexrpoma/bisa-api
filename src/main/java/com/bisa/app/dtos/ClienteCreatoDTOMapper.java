package com.bisa.app.dtos;

import com.bisa.app.models.Cliente;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ClienteCreatoDTOMapper implements Function<Cliente, ClienteCreadoDTO> {
  @Override
  public ClienteCreadoDTO apply(Cliente cliente) {
    return ClienteCreadoDTO.builder()
        .id(cliente.getId())
        .estado("CREADO")
        .email(cliente.getEmail())
        .telefono(cliente.getTelefono())
        .ocupacion(cliente.getOcupacion())
        .build();
  }
}
