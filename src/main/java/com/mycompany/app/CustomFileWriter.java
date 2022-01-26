package com.mycompany.app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CustomFileWriter {
  public CustomFileWriter (List<String> args) {
    try {
      FileWriter myWriter = new FileWriter("rebeldes.txt");

      for (String arg : args) {
        myWriter.write(String.valueOf(arg) + "\n");
      }

      myWriter.close();

      System.out.println("ARQUIVO GERADO COM SUCESSO");
    } catch (IOException e) {
      System.out.println("ERRO DURANTE ESCRITA DO ARQUIVO");
      e.printStackTrace();
    }
  }
}
