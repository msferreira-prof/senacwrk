package br.senac.rj.exercicio;

import br.senac.rj.exercicio.jdbc.TestarApoliceJDBC;
import br.senac.rj.exercicio.model.Apolice;

import java.util.List;

public class Main {

  public static void main(String[] args) {
      TestarApoliceJDBC tajdbc = new TestarApoliceJDBC();
      tajdbc.executar();
      tajdbc = null;
  }
}