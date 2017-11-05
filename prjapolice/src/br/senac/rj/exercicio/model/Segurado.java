package br.senac.rj.exercicio.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class Segurado implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long matricula;
    private String cpf;
    private String nome;
    private String endereco;
    private Date dataNascimento;


    public long getMatricula() {
        return matricula;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public long getIdade() {
        int idade = 0;
        Date hoje = Calendar.getInstance().getTime();
        idade = hoje.getYear() - dataNascimento.getYear();
        return idade;
    }

    public long getIdade2() {
        LocalDate hoje = LocalDate.now();
        LocalDate nascimento = LocalDate.of(dataNascimento.getYear(), dataNascimento.getMonth(), dataNascimento.getDay());
        long idade = ChronoUnit.DAYS.between(nascimento, hoje);

        return idade;
    }

    public Segurado() {
    }

    public Segurado(long matricula, String cpf, String nome, String endereco, Date dataNascimento) {
        this.matricula = matricula;
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Segurado{" +
                "matricula=" + matricula +
                ", cpf=" + cpf +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}
