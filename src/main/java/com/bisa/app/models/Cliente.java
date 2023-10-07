package com.bisa.app.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
public class Cliente {
  @Id
  @GeneratedValue(strategy = AUTO)
  private UUID id;
  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "persona_id", referencedColumnName = "id")
  private Persona informacionPersonal;
  @Column(unique = true)
  private String email;
  private String telefono;
  private String ocupacion;
  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "referencias_personales_id", referencedColumnName = "id")
  private ReferenciasPersonales referenciasPersonales;
  @Enumerated(EnumType.STRING)
  private Estado estado;
  @Enumerated(EnumType.STRING)
  private Accesibilidad accesibilidad;
}
