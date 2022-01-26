package com.mycompany.app.models;

import com.mycompany.app.enums.Raca;
import lombok.Data;

@Data
public class Rebelde {
  private final Raca raca;
  private final String name;
  private final Integer idade;

  public Rebelde(String name, Integer idade, Raca raca) {
    this.raca = raca;
    this.name = name;
    this.idade = idade;
  }

  @Override
  public String toString () {
    return String.format("%s, %s de %d anos", name, raca, idade);
  }
}
