package com.bisa.app.dtos;

import com.bisa.app.models.Cliente;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ClientesAccesibilidadDTOMapper implements Function<Cliente, ClientesAccesibilidadDTO> {
  @Override
  public ClientesAccesibilidadDTO apply(Cliente cliente) {
    return ClientesAccesibilidadDTO.builder()
        .id(cliente.getId())
        .email(cliente.getEmail())
        .telefono(cliente.getTelefono())
        .ocupacion(cliente.getOcupacion())
        .accesibilidad(cliente.getAccesibilidad())
        .build();
  }
}
