package com.bisa.app.services;

import com.bisa.app.models.Persona;

import java.util.List;
import java.util.UUID;

public interface PersonaService {
  List<Persona> getAllPersonas();
  Persona getPersona(UUID uuid);
  Persona createPersona(Persona persona);
}
