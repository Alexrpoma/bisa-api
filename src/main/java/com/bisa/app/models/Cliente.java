package com.bisa.app.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
public class Cliente {
  @Id
  @GeneratedValue(strategy = AUTO)
  private UUID id;
  @OneToOne
  @JoinColumn(name = "id_persona", referencedColumnName = "id")
  private Persona personalInfo;
  @Column(unique = true)
  private String email;
  private String telefono;
  private String ocupacion;
  private int references;
  @Enumerated(EnumType.STRING)
  private Estado estado;
}
