package com.bisa.app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Direccion {
  @Id
  @GeneratedValue(strategy = AUTO)
  private UUID id;
  private String ubicacionGeografica;
  private String zona;
  private String barrio;
  private String calle;
  private int numeroDeDomicilio;
}
