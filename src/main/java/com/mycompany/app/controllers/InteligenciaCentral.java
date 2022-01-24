package com.mycompany.app.controllers;

public class InteligenciaCentral {
  private final Integer lineSize = 60;
  private final String processName = "Inteligência Central";
  private final Integer padding = (lineSize - processName.length()) / 2;

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
    System.out.println(centeredText("ESCOLHA UMA DAS OPÇÕES ABAIXO\n"));

    String[] opcoes = {
      "Adicionar um novo Rebelde",
      "Listar todos os Rebeldes",
      "Finalizar aplicação"
    };

    for (int i = 0, len = opcoes.length; i < len; i++) {
      int questionIndex = i + 1;

      System.out.printf("%d - %s\n", questionIndex, opcoes[i]);
    }

    clear();
  }

  private String centeredText (String text) {
    String sidePadding = " ".repeat((lineSize - text.length()) / 2);

    return sidePadding + text + sidePadding;
  }

  public void clear () {
    System.out.flush();
    System.out.print("\033[H\033[2J");
  }
}
