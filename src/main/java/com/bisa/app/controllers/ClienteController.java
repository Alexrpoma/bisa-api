package com.bisa.app.controllers;

import com.bisa.app.models.Cliente;
import com.bisa.app.models.UpdateReferenciaPersonal;
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

  @PutMapping("/{clienteId}/referencia")
  public ResponseEntity<Cliente> updateReferenciasPersonales(
      @PathVariable(name = "clienteId") UUID clienteId,
      @RequestBody UpdateReferenciaPersonal updateReferenciaPersonal
      ) {
    return ResponseEntity.ok(clienteService.updateReferenciasPersonales(clienteId, updateReferenciaPersonal));
  }

  @DeleteMapping("/{clienteId}/referencia")
  public ResponseEntity<Void> deleteReferenciaPersonal(
      @PathVariable(name = "clienteId") UUID clienteId,
      @RequestBody UpdateReferenciaPersonal updateReferenciaPersonal
      ) {
    clienteService.deleteReferenciaPersonal(clienteId, updateReferenciaPersonal);
    return ResponseEntity.noContent().build();
  }
}
