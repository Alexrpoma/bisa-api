package com.bisa.app.config;

import com.bisa.app.models.*;
import com.bisa.app.repositories.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Configuration
public class Config {
  @Bean
  CommandLineRunner commandLineRunner(ClienteRepository repository) {
    return args -> {
      Cliente cliente0 = Cliente.builder()
          .informacionPersonal(
              Persona.builder()
                  .nombre("Juan")
                  .apellidoPaterno("Perez")
                  .apellidoMaterno("Gonzalez")
                  .carnetDeIdentidad(7005896)
                  .fechaDeNacimiento(LocalDate.parse("1990-01-01"))
                  .direccion(
                      Direccion.builder()
                          .ubicacionGeografica("La Paz - Bolivia")
                          .zona("Miraflores")
                          .barrio("La fuente")
                          .calle("Calle 1")
                          .numeroDeDomicilio(2045)
                          .build()
                  )
                  .build()
          )
          .email("juan@gmail.com")
          .telefono("7777777")
          .ocupacion("Estudiante")
          .referenciasPersonales(
              ReferenciasPersonales.builder()
                  .listReferenciaCliente(new HashSet<>())
                  .listReferenciaPersona(new HashSet<>())
                  .totalReferencias(0)
                  .referenciasClientes(0)
                  .build()
          )
          .estado(Estado.BLOQUEADO)
          .accesibilidad(Accesibilidad.NULA)
          .build();
      Cliente cliente1 = Cliente.builder()
          .informacionPersonal(
              Persona.builder()
                  .nombre("Maria")
                  .apellidoPaterno("Gonzales")
                  .apellidoMaterno("Perez")
                  .carnetDeIdentidad(7005896)
                  .fechaDeNacimiento(LocalDate.parse("2000-01-01"))
                  .direccion(
                      Direccion.builder()
                          .ubicacionGeografica("La Paz - Bolivia")
                          .zona("Miraflores")
                          .barrio("La fuente")
                          .calle("Calle 1")
                          .numeroDeDomicilio(2045)
                          .build()
                  )
                  .build()
          )
          .email("maria@hotmail.com")
          .telefono("73012456")
          .ocupacion("Ingeniera")
          .referenciasPersonales(
              ReferenciasPersonales.builder()
                  .listReferenciaCliente(new HashSet<>())
                  .listReferenciaPersona(new HashSet<>())
                  .totalReferencias(0)
                  .referenciasClientes(0)
                  .build()
          )
          .estado(Estado.BLOQUEADO)
          .accesibilidad(Accesibilidad.NULA)
          .build();
      Cliente cliente2 = Cliente.builder()
          .informacionPersonal(
              Persona.builder()
                  .nombre("Pedro")
                  .apellidoPaterno("Gonzales")
                  .apellidoMaterno("Perez")
                  .carnetDeIdentidad(7005896)
                  .fechaDeNacimiento(LocalDate.parse("1999-01-01"))
                  .direccion(
                      Direccion.builder()
                          .ubicacionGeografica("La Paz - Bolivia")
                          .zona("Miraflores")
                          .barrio("La fuente")
                          .calle("Calle 1")
                          .numeroDeDomicilio(2045)
                          .build()
                  )
                  .build()
          )
          .email("pedro@yahoo.com")
          .telefono("72048963")
          .ocupacion("Abogado")
          .referenciasPersonales(
              ReferenciasPersonales.builder()
                  .listReferenciaCliente(new HashSet<>())
                  .listReferenciaPersona(new HashSet<>())
                  .totalReferencias(0)
                  .referenciasClientes(0)
                  .build()
          )
          .estado(Estado.BLOQUEADO)
          .accesibilidad(Accesibilidad.NULA)
          .build();
      Cliente cliente3 = Cliente.builder()
          .informacionPersonal(
              Persona.builder()
                  .nombre("Jose")
                  .apellidoPaterno("Gonzales")
                  .apellidoMaterno("Perez")
                  .carnetDeIdentidad(7005896)
                  .fechaDeNacimiento(LocalDate.parse("1990-01-01"))
                  .direccion(
                      Direccion.builder()
                          .ubicacionGeografica("La Paz - Bolivia")
                          .zona("Miraflores")
                          .barrio("La fuente")
                          .calle("Calle 1")
                          .numeroDeDomicilio(2045)
                          .build()
                  )
                  .build()
          )
          .email("jose@gmail.com")
          .telefono("7401234")
          .ocupacion("Medico")
          .referenciasPersonales(
              ReferenciasPersonales.builder()
                  .listReferenciaCliente(new HashSet<>())
                  .listReferenciaPersona(new HashSet<>())
                  .totalReferencias(0)
                  .referenciasClientes(0)
                  .build()
          )
          .estado(Estado.BLOQUEADO)
          .accesibilidad(Accesibilidad.NULA)
          .build();
      repository.saveAll(List.of(cliente0, cliente1, cliente2, cliente3));
    };
  }
}
