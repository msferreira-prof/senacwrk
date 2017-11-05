package br.senac.rj.exercicio.dao.factory;

import br.senac.rj.exercicio.dao.ApoliceDAO;
import br.senac.rj.exercicio.dao.SeguradoDAO;

public abstract class DAOFactory {
    // lista de tipos de DAO por SGBD
    public static final int MYSQL = 1;
    public static final int POSTGRES = 2;

    // metodo para criar a fabrica correspondente
    public abstract ApoliceDAO getApoliceDAO();
    public abstract SeguradoDAO getSeguradoDAO();

    // metodo que retorna a fabrica correspondente
    public static DAOFactory getDAOFactory(int fabrica) {

        switch (fabrica) {
            case MYSQL:
                return new MySqlDAOFactory();
            case POSTGRES:
                return new PostgresDAOFactory();
            default:
                return null;
        }
    }
}
