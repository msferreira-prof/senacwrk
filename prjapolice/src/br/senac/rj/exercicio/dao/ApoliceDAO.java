package br.senac.rj.exercicio.dao;

import br.senac.rj.exercicio.model.Apolice;

import java.util.List;

public interface ApoliceDAO {
    public long incluir(Apolice apolice);
    public Apolice consultarPorNumero(long numero);
    public Apolice consultarPorSegurado(long numero);
    public List<Apolice> listar();
    public List<Apolice> listarPorUf(String uf);
    public boolean atualizar(Apolice apolice);
    public boolean remover(long numero);
}
