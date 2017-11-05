package br.senac.rj.exercicio;

import br.senac.rj.exercicio.model.Apolice;
import br.senac.rj.exercicio.model.Segurado;

import java.util.*;

public class TestarApoliceList {
	private List<Apolice> listaApolices;
	private float percentualAtualizacao;

	public void executar() {
		List<Apolice> listaApolice = null;
		float percentualAtualizacao = 0.0f;
		
		TestarApoliceList ta = new TestarApoliceList();
		
		ta.carregarApolices();
		
		ta.apresentarApolices();

		percentualAtualizacao = ta.informarPercentualAtualizacaoTodasApolices();
		ta.atualizarApolices();
		
		ta.apresentarApolices();
	}
	
	@SuppressWarnings("resource")
	public void carregarApolices() {
		Scanner entrada;
		listaApolices = new ArrayList<>();
		
		entrada = new Scanner(System.in);

		for (int i = 0; i < 1; i++) {
			System.out.print("Informe a matricula do Segurado: ");
			String matriculaSegurado = entrada.nextLine();

			System.out.print("Informe o nome do Segurado: ");
			String nomeSegurado = entrada.nextLine();

			System.out.print("Informe o CPF do Segurado: ");
			String cpfSegurado = entrada.nextLine();

			System.out.print("Informe a data de Nascimento do Segurado (yyyy/mm/dd): ");
			String dataNascimentoSegurado = entrada.nextLine();

			System.out.print("Informe o endereco do Segurado: ");
			String enderecoSegurado = entrada.nextLine();

			Segurado segurado = new Segurado();
			segurado.setMatricula(Long.parseLong(matriculaSegurado));
			segurado.setNome(nomeSegurado);
			segurado.setCpf(cpfSegurado);
			segurado.setDataNascimento(new Date(Date.parse(dataNascimentoSegurado)));
			segurado.setEndereco(enderecoSegurado);

			System.out.print("Informe o Valor Segurado: ");
			double valorSegurado = Double.parseDouble(entrada.nextLine());
			
			System.out.print("Informe a UF da Apolice ('MG', 'PR', 'RJ', 'SP'): ");
			String uf = entrada.nextLine().substring(0, 2);
			
			Apolice apolice = new Apolice(i+1, segurado, uf, valorSegurado);
			apolice.calcularPremio();
			
			listaApolices.add(apolice);
		}
	}

	@SuppressWarnings("resource")
	public float informarPercentualAtualizacaoTodasApolices() {
		float percentualAtualizacao = 0.0f;
		Scanner entrada;
		
		entrada = new Scanner(System.in);
		System.out.print("Informe o percentual de atualizacao do valor segurado: ");
		percentualAtualizacao = Float.parseFloat(entrada.nextLine());
		
		return percentualAtualizacao;
	}

	public void atualizarApolices() {
		for(Apolice apol: listaApolices) {
			apol.atualizarValorSegurado(percentualAtualizacao);
		}
	}
	
	public void apresentarApolices() {
		ListIterator<Apolice> iterator = listaApolices.listIterator();
		while (iterator.hasNext()) {
			Apolice ap = iterator.next();
			System.out.println("Apolice:  " + ap.toString());
		}
	}
}
