package br.senac.rj.exercicio.jdbc;

import br.senac.rj.exercicio.model.Apolice;
import br.senac.rj.exercicio.model.Segurado;
import br.senac.rj.exercicio.service.SeguradoService;

import java.util.*;

public class TestarApoliceJDBC {
    private List<Apolice> listaApolices;
    private List<Segurado> listaSegurados;

    public void executar() {
        this.gerarSegurados();

        int qtdInseridos = this.inserirSegurados();

        System.out.printf("\nForam inseridos %d segurados", qtdInseridos);

        this.listarSegurados();

        this.pesquisarSegurado();
        
        this.atualizarSegurado();
        
        this.listarSegurados();
    }

    public void gerarSegurados() {
        listaSegurados = new ArrayList<>();

        for(int i=0; i<10; i++) {
            long cpf = 11111111110l + Integer.toUnsignedLong(i);
            Segurado s = new Segurado();
            s.setCpf(Long.toString(cpf));
            s.setNome("Segurado " + (i+1));
            s.setDataNascimento(new Date(75, i, 10));
            s.setEndereco("Rua da Casa do Segurado, " + (i+1));

            listaSegurados.add(s);
        }
    }

    public int inserirSegurados() {
        int qtdInserida = 0;

        SeguradoService ss = new SeguradoService();

        for(Segurado segurado: listaSegurados) {
            long matriculaGerada = ss.incluir(segurado);
            if (matriculaGerada > 0) {
                System.out.printf("Segurado %s recebeu a matricula: %s", 
                			segurado.getNome(), segurado.getMatricula());

                qtdInserida++;
            }
        }

        return qtdInserida;
    }

    public void listarSegurados() {
        List<Segurado> segurados = new ArrayList<>();

        SeguradoService ss = new SeguradoService();
        segurados = ss.listar();

        for(Segurado s: segurados) {
            System.out.println(s);
        }
    }

    public void pesquisarSegurado() {
        Scanner entrada;
        SeguradoService ss;

        ss = new SeguradoService();

        entrada = new Scanner(System.in);

        for (int i = 0; i < 1; i++) {
            System.out.print("Informe a matricula do Segurado: ");
            String matriculaSegurado = entrada.nextLine();

            Segurado seguradoConsultado = ss.consultarPorMatricula(Long.parseLong(matriculaSegurado));

            if (seguradoConsultado != null) {
                System.out.printf("O Segurado com a matricula %s: [%s]", matriculaSegurado, seguradoConsultado.toString());
            } else {
                System.out.printf("Matricula [%s] nao foi encontrada");
            }
        }
    }

    public void atualizarSegurado() {
    	Segurado s = new Segurado();
    	s.setMatricula(6);
    	s.setCpf("66699966699");
    	s.setNome("Segurado Atualizado");
    	s.setEndereco("Rua da Casa da Esquina, 3000");
    	s.setDataNascimento(new Date(1996, 12, 01));
    	
    	SeguradoService ss = new SeguradoService();
    	ss.atualizar(s);
    	
    }
}
