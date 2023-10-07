package com.bisa.app.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReferenciasPersonales {
  @Id
  @GeneratedValue(strategy = AUTO)
  private UUID id;
  private Set<UUID> listReferenciaCliente;
  private Set<UUID> listReferenciaPersona;
  private int totalReferencias;
  private int referenciasClientes;
}
