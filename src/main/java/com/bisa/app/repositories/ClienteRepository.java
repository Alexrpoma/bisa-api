package com.bisa.app.repositories;

import com.bisa.app.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
  @Query("SELECT CASE WHEN Count(c) > 0 THEN TRUE ELSE FALSE END FROM Cliente c WHERE c.email = ?1")
  boolean existsClienteByEmail(String email);
}