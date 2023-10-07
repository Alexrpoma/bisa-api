package com.bisa.app.controllers;

import com.bisa.app.models.Cliente;
import com.bisa.app.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cliente")
public class ClienteController {

  private final ClienteService clienteService;

  @GetMapping
  public ResponseEntity<List<Cliente>> all() {
    return ResponseEntity.ok(clienteService.allClientes());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cliente> getCliente(@PathVariable(name = "id") UUID id) {
    return ResponseEntity.ok(clienteService.getCliente(id));
  }

  @PostMapping
  public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(clienteService.createCliente(cliente));
  }

  @PutMapping("/{clienteId}/referencia/{referenciaId}")
  public ResponseEntity<Cliente> updateReferenciasPersonales(
      @PathVariable(name = "clienteId") UUID clienteId,
      @PathVariable(name = "referenciaId") UUID referenciaId
  ) {
    return ResponseEntity.ok(clienteService.updateReferenciasPersonales(clienteId, referenciaId));
  }
  
}
