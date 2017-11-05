package br.senac.rj.exercicio.dao;

import br.senac.rj.exercicio.model.Segurado;

import java.util.List;

public interface SeguradoDAO {
    public long incluir(Segurado segurado);
    public Segurado consultarPorMatricula(long matricula);
    public Segurado consultarPorCpf(String cpf);
    public Segurado consultarPorNome(String nome);
    public List<Segurado> listar();
    public boolean atualizar(Segurado segurado);
    public boolean remover(long numero);
}
