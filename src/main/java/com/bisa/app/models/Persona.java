package com.bisa.app.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
  @Id
  @GeneratedValue(strategy = AUTO)
  private UUID id;
  private String nombre;
  private String apellidoPaterno;
  private String apellidoMaterno;
  private LocalDateTime fechaNacimiento;
  @OneToOne
  @JoinColumn(name = "id_direccion", referencedColumnName = "id")
  private Direccion direccion;
}
