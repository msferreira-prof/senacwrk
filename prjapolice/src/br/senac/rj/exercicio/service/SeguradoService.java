package br.senac.rj.exercicio.service;

import br.senac.rj.exercicio.dao.SeguradoDAO;
import br.senac.rj.exercicio.dao.factory.DAOFactory;
import br.senac.rj.exercicio.dao.factory.MySqlDAOFactory;
import br.senac.rj.exercicio.model.Segurado;

import java.util.List;

public class SeguradoService {
    private DAOFactory factory;
    SeguradoDAO seguradoDAO;

    public SeguradoService() {
        factory = DAOFactory.getDAOFactory(1);
        seguradoDAO = factory.getSeguradoDAO();
    }

    public long incluir(Segurado novoSegurado) {
        long matricula = 0;
        matricula = seguradoDAO.incluir(novoSegurado);
        return matricula;
    }

    public boolean atualizar(Segurado seguradoAtualizado) {
        return seguradoDAO.atualizar(seguradoAtualizado);
    }

    public Segurado consultarPorMatricula(long matricula) {
        Segurado segurado = null;
        segurado = seguradoDAO.consultarPorMatricula(matricula);
        return segurado;
    }

    public Segurado consultarPorCpf(String cpf) {
        Segurado segurado = null;
        segurado = seguradoDAO.consultarPorCpf(cpf);
        return segurado;
    }

        public Segurado consultarPorNome(String nome) {
        Segurado segurado = null;
        segurado = seguradoDAO.consultarPorNome(nome);
        return segurado;
    }

    
    public List<Segurado> listar() {
        List<Segurado> segurados = null;
        segurados = seguradoDAO.listar();
        return segurados;
    }
}
