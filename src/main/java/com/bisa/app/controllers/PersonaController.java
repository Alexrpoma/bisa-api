package com.bisa.app.controllers;

import com.bisa.app.models.Persona;
import com.bisa.app.services.PersonaService;
import com.bisa.app.validators.IdValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/persona")
public class PersonaController {

  private final PersonaService personaService;
  private final IdValidator idValidator;

  @GetMapping
  public ResponseEntity<List<Persona>> all() {
    return ResponseEntity.ok(personaService.getAllPersonas());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Persona> get(@PathVariable(name = "id") String id) {
    return ResponseEntity.ok(personaService.getPersona(idValidator.apply(id)));
  }

  @PostMapping
  public ResponseEntity<Persona> create(@RequestBody Persona persona) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(personaService.createPersona(persona));
  }
}
