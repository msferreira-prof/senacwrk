package br.senac.rj.exercicio.dao;

import br.senac.rj.exercicio.dao.factory.MySqlDAOFactory;
import br.senac.rj.exercicio.model.Segurado;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MySqlSeguradoDAO implements SeguradoDAO {

    public MySqlSeguradoDAO() {

    }

    @Override
    public long incluir(Segurado segurado) {
        long matricula = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;

        // criar a conexao
        conn = MySqlDAOFactory.createConnection();

        // consultar os alunos que existem na tabela
        try {
            String sql = "INSERT INTO segurado (cpf, nome, dataNascimento, endereco) VALUES (?, ?, ?, ?);";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, segurado.getCpf());
            pstmt.setString(2, segurado.getNome());
            pstmt.setDate(3, new java.sql.Date(segurado.getDataNascimento().getTime()));
            pstmt.setString(4, segurado.getEndereco());

            pstmt.execute();

            ResultSet tableKeys = pstmt.getGeneratedKeys();
            tableKeys.next();
            matricula = tableKeys.getInt(1);

            segurado.setMatricula(matricula);

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

        return matricula;

    }

    @Override
    public Segurado consultarPorMatricula(long matricula) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Segurado seguradoConsultado = null;

        // criar a conexao
        conn = MySqlDAOFactory.createConnection();

        // consultar os alunos que existem na tabela
        try {
            String sql = "SELECT * FROM segurado WHERE matricula=" + matricula + ";";
            stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                seguradoConsultado = new Segurado();
                seguradoConsultado.setMatricula(rs.getLong("matricula"));
                seguradoConsultado.setEndereco(rs.getString("endereco"));
                seguradoConsultado.setDataNascimento(rs.getDate("dataNascimento"));
                seguradoConsultado.setNome(rs.getString("nome"));
                seguradoConsultado.setCpf(rs.getString("cpf"));
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

        return seguradoConsultado;

    }

    @Override
    public Segurado consultarPorCpf(String cpf) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Segurado seguradoConsultado = null;

        // criar a conexao
        conn = MySqlDAOFactory.createConnection();

        // consultar os alunos que existem na tabela
        try {
            String sql = "SELECT * FROM segurado WHERE cpf=" + cpf + ";";
            stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                seguradoConsultado = new Segurado();
                seguradoConsultado.setMatricula(rs.getLong("matricula"));
                seguradoConsultado.setEndereco(rs.getString("endereco"));
                seguradoConsultado.setDataNascimento(rs.getDate("dataNascimento"));
                seguradoConsultado.setNome(rs.getString("nome"));
                seguradoConsultado.setCpf(rs.getString("cpf"));
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

        return seguradoConsultado;
    }

    @Override
    public List<Segurado> listar() {// consultar os alunos que existem na tabela
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Segurado> listaSegurados = null;

        // criar a conexao
        conn = MySqlDAOFactory.createConnection();

        // consultar os alunos que existem na tabela
        try {
            listaSegurados = new ArrayList<>();

            String sql = "SELECT * FROM segurado;";
            stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Segurado segurado = new Segurado();
                segurado.setMatricula(rs.getLong("matricula"));
                segurado.setEndereco(rs.getString("endereco"));
                segurado.setDataNascimento(rs.getDate("dataNascimento"));
                segurado.setNome(rs.getString("nome"));
                segurado.setCpf(rs.getString("cpf"));

                listaSegurados.add(segurado);
            }

            rs.close();
            
            if (listaSegurados.isEmpty()) {
                listaSegurados = null;
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

        return listaSegurados;
    }

    @Override
    public boolean atualizar(Segurado segurado) {
        Connection conn;
        Statement stmt;
        String sql;
        boolean retorno;

        retorno = false;
        stmt = null;
        conn = MySqlDAOFactory.createConnection();

        try {
            DateFormat df = new SimpleDateFormat("yy-MM-dd");

            sql = "Update segurado ";
            sql = sql + "Set cpf = '" + segurado.getCpf() + "', ";
            sql = sql + "nome = '" + segurado.getNome() + "', ";
            sql = sql + "endereco = '" + segurado.getEndereco() + "', ";
            sql = sql + "dataNascimento = '" + df.format(segurado.getDataNascimento()) + "' ";
            sql = sql + "WHERE matricula = " + segurado.getMatricula() + ";";

            stmt = conn.createStatement();
            int qtdRegistros = stmt.executeUpdate(sql);
            if (qtdRegistros > 0) {
                retorno = true;
                System.out.printf("Matricula [%d] foi atualizada com sucesso!\n", segurado.getMatricula());
            }

        } catch (SQLException e) {
            System.out.println("Erro na atualizacao do segurado " + e.getMessage());

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

        return retorno;
    }

    @Override
    public boolean remover(long numero) {
        return false;
    }

    @Override
    public Segurado consultarPorNome(String nome) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Segurado seguradoConsultado = null;

        // criar a conexao
        conn = MySqlDAOFactory.createConnection();

        // consultar os alunos que existem na tabela
        try {
            String sql = "SELECT * FROM segurado WHERE nome like '" + nome.trim() + "%';";
            stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                seguradoConsultado = new Segurado();
                seguradoConsultado.setMatricula(rs.getLong("matricula"));
                seguradoConsultado.setEndereco(rs.getString("endereco"));
                seguradoConsultado.setDataNascimento(rs.getDate("dataNascimento"));
                seguradoConsultado.setNome(rs.getString("nome"));
                seguradoConsultado.setCpf(rs.getString("cpf"));
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

        return seguradoConsultado;
    }
}
