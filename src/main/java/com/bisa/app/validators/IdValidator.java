package com.bisa.app.validators;

import com.bisa.app.exceptions.InvalidDataException;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.Function;

@Component
public class IdValidator implements Function<String, UUID> {
  @Override
  public UUID apply(String id) {
    try {
      return UUID.fromString(id);
    } catch (IllegalArgumentException e) {
      throw new InvalidDataException(e.getMessage());
    }
  }
}
