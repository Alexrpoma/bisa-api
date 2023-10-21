package com.bisa.app.repositories;

import com.bisa.app.models.Accesibilidad;
import com.bisa.app.models.Cliente;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ClientAccessibilityRepository extends PagingAndSortingRepository<Cliente, Accesibilidad> {
  List<Cliente> findAllByAccesibilidad(Accesibilidad accesibilidad);
}
