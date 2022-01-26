package com.mycompany.app.controllers;

import com.mycompany.app.CustomFileWriter;
import com.mycompany.app.enums.Raca;
import com.mycompany.app.models.Rebelde;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InteligenciaCentral {
  private final List<Rebelde> rebeldes = new ArrayList<>();

  private final Integer lineSize = 60;
  private final String processName = "Inteligência Central";
  private final Integer padding = (lineSize - processName.length()) / 2;

  private final Scanner scan = new Scanner(System.in);

  public void run () {
    clear();

    System.out.println("#".repeat(lineSize));
    System.out.println("#" + " ".repeat(lineSize - 2) + "#");
    System.out.println("#" + " ".repeat(padding - 1) + processName + " ".repeat(padding - 1) + "#");
    System.out.println("#" + " ".repeat(lineSize - 2) + "#");
    System.out.println("#".repeat(lineSize));

    mainMenu();
  }

  private void mainMenu() {
    String[] opcoes = {
      "Adicionar um novo Rebelde",
      "Listar todos os Rebeldes",
      "Finalizar a aplicação"
    };
    
    int opt = 0;

    do {
      centeredText("ESCOLHA UMA DAS OPÇÕES ABAIXO\n");

      for (int i = 0, len = opcoes.length; i < len; i++) {
        int questionIndex = i + 1;

        System.out.printf("%d - %s\n", questionIndex, opcoes[i]);
      }

      opt = getNumberInput("OPÇÃO INVÁLIDA, INSIRA NOVAMENTE");
    } while (opt == 0 || opt > opcoes.length || opt < 1);

    centeredText(String.format("Você decidiu %s".toUpperCase(), opcoes[opt - 1]));

    switch (opt) {
      case 1:
        adicionarRebelde();
        break;
      case 2:
        listarRebeldes();
        break;
      case 3:
        System.exit(0);
    }

    mainMenu();
  }

  private void listarRebeldes() {
    System.out.println("\n");

    if (rebeldes.isEmpty()) {
      centeredText("NÃO EXISTEM REBELDES LISTADOS\n\n");

      return;
    }

    List<String> rebeldesList = new ArrayList<>();

    for (Rebelde rebelde : rebeldes) {
      rebeldesList.add(rebelde.toString());
    }

    CustomFileWriter CFW = new CustomFileWriter(rebeldesList);

    System.out.println("\n\n");
  }

  private void adicionarRebelde() {
    clear();

    centeredText("QUAL O SEU NOME, JOVEM REBELDE?");
    System.out.print("#: ");
    String name = scan.next();

    centeredText(String.format("OK %s. QUAL A SUA IDADE?".toUpperCase(), name));
    int idade = getNumberInput("VALOR NUMERICO NÃO INFORMADO");


    Raca[] racas = Raca.values();

    for (int i = 0, len = racas.length; i < len; i++) {
      System.out.printf("%d - %s\n", i + 1, racas[i]);
    }

    int racaIndex = 0;
    Raca raca;

    do {
      centeredText("QUAL SUA RAÇA?");

      racaIndex = getNumberInput("VALOR NÃO NUMERICO INFORMADO");
    } while (!isBetweenRange(racaIndex, 0, racas.length + 1));

    switch (racaIndex) {
      case 2:
        raca = Raca.GREE;
        break;
      case 3:
        raca = Raca.RAKATA;
        break;
      case 1:
      default:
        raca = Raca.HUMANO;
    }

    if (raca == Raca.HUMANO && idade < 18) {
      centeredText("DESCULPE, NÃO ACEITAMOS HUMANOS MENORES");

      return;
    }

    centeredText("UM NOVO REBELDE JUNTOU-SE A NÓS");
    centeredText(String.format("SEJA BEM VINDO %s".toUpperCase(), name));
    System.out.println("\n\n");

    rebeldes.add(
      new Rebelde(name, idade, raca)
    );
  }

  private Integer getNumberInput (String failMessage) {
    int value = 0;

    do {
      try {
        value = scan.nextInt();
      } catch (InputMismatchException e) {
        centeredText(failMessage);

        scan.nextLine();
      }
    } while (value < 1);

    return value;
  }

  private boolean isBetweenRange (int value, int min, int max) {
    return value > min && value < max;
  }

  private void centeredText (String text) {
    String sidePadding = " ".repeat((lineSize - text.length()) / 2);

    System.out.println(sidePadding + text + sidePadding);
  }

  public void clear () {
    System.out.flush();
    System.out.print("\033[H\033[2J");
  }
}
