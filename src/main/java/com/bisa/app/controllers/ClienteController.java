package com.bisa.app.controllers;

import com.bisa.app.dtos.ClienteCreadoDTO;
import com.bisa.app.dtos.ClientesAccesibilidadDTO;
import com.bisa.app.models.Cliente;
import com.bisa.app.models.UpdateReferenciaPersonal;
import com.bisa.app.services.ClienteService;
import com.bisa.app.validators.IdValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cliente")
public class ClienteController {

  private final ClienteService clienteService;
  private final IdValidator idValidator;

  @GetMapping
  public ResponseEntity<List<ClientesAccesibilidadDTO>> allClientesAccesibilidad() {
    return ResponseEntity.ok(clienteService.allClientesAccesibilidad());
  }

  @GetMapping("/detalle")
  public ResponseEntity<List<Cliente>> allDetalleClientes() {
    return ResponseEntity.ok(clienteService.allClientes());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cliente> getCliente(@PathVariable(name = "id") String  id) {
    return ResponseEntity.ok(clienteService.getCliente(idValidator.apply(id)));
  }

  @GetMapping("/accesibilidad/{accesibilidad}")
  public ResponseEntity<List<ClienteCreadoDTO>> getClientesByAccesibilidad(
      @PathVariable(name = "accesibilidad") String accesibilidad) {
    return ResponseEntity.ok(clienteService.getClientesByAccesibilidad(accesibilidad));
  }

  @PostMapping
  public ResponseEntity<ClienteCreadoDTO> createCliente(@RequestBody Cliente cliente) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(clienteService.createCliente(cliente));
  }

  @PutMapping("/{clienteId}/referencia")
  public ResponseEntity<Cliente> updateReferenciasPersonales(
      @PathVariable(name = "clienteId") String clienteId,
      @RequestBody UpdateReferenciaPersonal updateReferenciaPersonal
      ) {
    return ResponseEntity.ok(clienteService
        .updateReferenciasPersonales(idValidator.apply(clienteId), updateReferenciaPersonal));
  }

  @DeleteMapping("/{clienteId}/referencia")
  public ResponseEntity<Void> deleteReferenciaPersonal(
      @PathVariable(name = "clienteId") String clienteId,
      @RequestBody UpdateReferenciaPersonal updateReferenciaPersonal
      ) {
    clienteService.deleteReferenciaPersonal(idValidator.apply(clienteId), updateReferenciaPersonal);
    return ResponseEntity.noContent().build();
  }
}
