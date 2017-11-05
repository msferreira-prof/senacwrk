package br.senac.rj.exercicio.service;

import br.senac.rj.exercicio.dao.ApoliceDAO;
import br.senac.rj.exercicio.dao.factory.DAOFactory;
import br.senac.rj.exercicio.model.Apolice;

import java.util.List;

public class ApoliceService {
    private DAOFactory factory;
    ApoliceDAO apoliceDAO;

    public ApoliceService() {
        factory = DAOFactory.getDAOFactory(1);
        apoliceDAO = factory.getApoliceDAO();
    }

    public long incluir(Apolice novaApolice) {
        long numero = 0;
        numero = apoliceDAO.incluir(novaApolice);
        return numero;
    }

    public boolean atualizar(Apolice apoliceAtualizada) {
        return apoliceDAO.atualizar(apoliceAtualizada);
    }

    public Apolice consultarPorNumero(long numero) {
        Apolice apolice = null;
        apolice = apoliceDAO.consultarPorNumero(numero);
        return apolice;
    }

    public Apolice consultarPorMatricula(long matricula) {
        Apolice apolice = null;
        apolice = apoliceDAO.consultarPorSegurado(matricula);
        return apolice;
    }

    public List<Apolice> listar() {
        List<Apolice> apolices = null;
        apolices = apoliceDAO.listar();
        return apolices;
    }
}
