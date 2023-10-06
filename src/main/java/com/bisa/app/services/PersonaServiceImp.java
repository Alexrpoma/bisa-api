package com.bisa.app.services;

import com.bisa.app.models.Persona;
import com.bisa.app.repositories.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public record PersonaServiceImp(PersonaRepository personaRepository) implements PersonaService {
  @Override
  public List<Persona> getAllPersonas() {
    return personaRepository.findAll();
  }

  @Override
  public Persona getPersona(UUID uuid) {
    return null;
  }

  @Override
  public Persona createPersona(Persona persona) {
    return null;
  }

}
