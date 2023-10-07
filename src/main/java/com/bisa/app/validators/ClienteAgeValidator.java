package com.bisa.app.validators;

import com.bisa.app.exceptions.InvalidDataException;
import com.bisa.app.models.Cliente;

import java.time.LocalDate;
import java.time.Period;

public class ClienteAgeValidator {
  public static void validate(Cliente cliente) {
    int age = Period.between(
        cliente.getInformacionPersonal().getFechaDeNacimiento(),
        LocalDate.now()
    ).getYears();
    if (age < 20) {
      throw new InvalidDataException("The client must be over 20 years old.");
    }
  }
}
