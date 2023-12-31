package com.bisa.app.services;

import com.bisa.app.exceptions.NotFoundException;
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
    return personaRepository.findById(uuid)
        .orElseThrow(() -> new NotFoundException("The person %s doesn't exist.".formatted(uuid)));
  }

  @Override
  public Persona createPersona(Persona persona) {
    return personaRepository.save(persona);
  }

}
