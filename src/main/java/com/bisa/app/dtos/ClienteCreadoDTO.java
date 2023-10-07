package com.bisa.app.dtos;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ClienteCreadoDTO(
    UUID id,
    String estado,
    String email,
    String telefono,
    String ocupacion
) {
}
