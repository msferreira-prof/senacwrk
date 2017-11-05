package br.senac.rj.exercicio.dao.factory;

import br.senac.rj.exercicio.dao.ApoliceDAO;
import br.senac.rj.exercicio.dao.MySqlApoliceDAO;
import br.senac.rj.exercicio.dao.MySqlSeguradoDAO;
import br.senac.rj.exercicio.dao.SeguradoDAO;
import br.senac.rj.exercicio.dao.factory.DAOFactory;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDAOFactory extends DAOFactory {
    public static final String DBDRIVER = "com.mysql.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/curso";

    public static Connection createConnection() {
        Connection conn = null;
        // carregar o Driver para conexao com o banco de dados
        try {
            Class.forName(DBDRIVER);
            System.out.println("\nDriver carregado");

            // criar conexao com o banco de dados
            conn = DriverManager.getConnection(DBURL, "root", "");
            conn.setAutoCommit(true);
            
            System.out.println("Conexao criada");

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar driver: " + e);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar o SGBD MySQL: " + e);
        }

        return conn;
    }

    @Override
    public ApoliceDAO getApoliceDAO() {
        return new MySqlApoliceDAO();
    }

    @Override
    public SeguradoDAO getSeguradoDAO() {
        return new MySqlSeguradoDAO();
    }
}