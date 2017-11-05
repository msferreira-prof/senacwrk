package br.senac.rj.exercicio.dao;

import br.senac.rj.exercicio.dao.factory.MySqlDAOFactory;
import br.senac.rj.exercicio.model.Apolice;
import br.senac.rj.exercicio.model.Segurado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlApoliceDAO implements ApoliceDAO {

    public MySqlApoliceDAO() {

    }

    @Override
    public long incluir(Apolice apolice) {
        long numero = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;

        // criar a conexao
        conn = MySqlDAOFactory.createConnection();

        // consultar os alunos que existem na tabela
        try {
            String sql = "INSERT INTO apolice (segurado, uf, valorSegurado, valorPremio, valorDesconto) VALUES (?, ?, ?, ?, ?);";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setLong(1, apolice.getSegurado().getMatricula());
            pstmt.setString(2, apolice.getUf());
            pstmt.setDouble(3, apolice.getValorSegurado());
            pstmt.setDouble(4, apolice.getValorPremio());
            pstmt.setDouble(5, apolice.getValorDesconto());

            pstmt.execute();

            ResultSet tableKeys = pstmt.getGeneratedKeys();
            tableKeys.next();
            numero = tableKeys.getInt(1);

            apolice.setNumero(numero);

        } catch (SQLException e) {
            System.out.println("Erro ao conectar o SGBD MySQL: " + e);

        } finally {
            // fechar a conexao com o banco de dados
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar o statement: " + e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Erro a conexao com o SGBD: " + e);
                }
            }
        }

        return numero;

    }

    @Override
    public Apolice consultarPorNumero(long numero) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Apolice apoliceConsultada = null;

        // criar a conexao
        conn = MySqlDAOFactory.createConnection();

        // consultar os alunos que existem na tabela
        try {
            String sql = "SELECT * FROM apolice INNER JOIN segurado WHERE numero = " + numero + ";";
            stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);

            if (rs.next()) {

                apoliceConsultada = new Apolice();
                Segurado segurado = new Segurado();

                apoliceConsultada.setNumero(rs.getLong("numero"));
                apoliceConsultada.setUf(rs.getString("uf"));
                apoliceConsultada.setValorSegurado(rs.getFloat("valorSegurado"));
                apoliceConsultada.setValorPremio(rs.getFloat("valorPremio"));
                apoliceConsultada.setValorDesconto(rs.getFloat("valorDesconto"));

                segurado.setMatricula(rs.getLong("matricula"));
                segurado.setEndereco(rs.getString("endereco"));
                segurado.setDataNascimento(rs.getDate("dataNascimento"));
                segurado.setNome(rs.getString("nome"));
                segurado.setCpf(rs.getString("cpf"));

                apoliceConsultada.setSegurado(segurado);
            }

            rs.close();

        } catch (SQLException e) {
            System.out.println("Erro ao conectar o SGBD MySQL: " + e);

        } finally {
            // fechar a conexao com o banco de dados
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar o statement: " + e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Erro a conexao com o SGBD: " + e);
                }
            }
        }

        return apoliceConsultada;
    }

    @Override
    public Apolice consultarPorSegurado(long numero) {
        return null;
    }

    @Override
    public List<Apolice> listar() {// consultar os alunos que existem na tabela
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Apolice> listaApolices = null;

        // criar a conexao
        conn = MySqlDAOFactory.createConnection();

        // consultar os alunos que existem na tabela
        try {
            listaApolices = new ArrayList<>();

            String sql = "SELECT * FROM apolice INNER JOIN segurado;";
            stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Apolice ap = new Apolice();
                ap.setNumero(rs.getLong("numero"));
                ap.setUf(rs.getString("uf"));
                ap.setValorSegurado(rs.getFloat("valorSegurado"));
                ap.setValorPremio(rs.getFloat("valorPremio"));
                ap.setValorDesconto(rs.getFloat("valorDesconto"));

                Segurado segurado = new Segurado();
                segurado.setMatricula(rs.getLong("matricula"));
                segurado.setEndereco(rs.getString("endereco"));
                segurado.setDataNascimento(rs.getDate("dataNascimento"));
                segurado.setNome(rs.getString("nome"));
                segurado.setCpf(rs.getString("cpf"));

                ap.setSegurado(segurado);

                listaApolices.add(ap);
            }

            rs.close();

            if (listaApolices.isEmpty()) {
                listaApolices = null;
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao conectar o SGBD MySQL: " + e);

        } finally {
            // fechar a conexao com o banco de dados
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar o statement: " + e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Erro a conexao com o SGBD: " + e);
                }
            }
        }

        return listaApolices;
    }

    @Override
    public List<Apolice> listarPorUf(String uf) {
        return null;
    }

    @Override
    public boolean atualizar(Apolice apolice) {
        return false;
    }

    @Override
    public boolean remover(long numero) {
        return false;
    }
}
