package com.bisa.app.dtos;

import com.bisa.app.models.Accesibilidad;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ClientesAccesibilidadDTO(
    UUID id,
    String email,
    String telefono,
    String ocupacion,
    Accesibilidad accesibilidad
) {
}
