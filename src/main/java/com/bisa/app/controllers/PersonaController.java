package com.bisa.app.controllers;

import com.bisa.app.models.Persona;
import com.bisa.app.services.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/persona")
public class PersonaController {

  private final PersonaService personaService;

  @GetMapping
  public ResponseEntity<List<Persona>> all() {
    return ResponseEntity.ok(personaService.getAllPersonas());
  }
}
