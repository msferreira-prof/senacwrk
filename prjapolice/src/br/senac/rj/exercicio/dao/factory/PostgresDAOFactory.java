package br.senac.rj.exercicio.dao.factory;

import br.senac.rj.exercicio.dao.ApoliceDAO;
import br.senac.rj.exercicio.dao.MySqlApoliceDAO;
import br.senac.rj.exercicio.dao.SeguradoDAO;

import java.sql.Connection;

public class PostgresDAOFactory extends DAOFactory {
    public static final String DRIVER = "";
    public static final String DBURL = "";

    public static Connection createConnection() {
        return null;
    }

    @Override
    public ApoliceDAO getApoliceDAO() {
        return null;
    }

    @Override
    public SeguradoDAO getSeguradoDAO() {
        return null;
    }

}

