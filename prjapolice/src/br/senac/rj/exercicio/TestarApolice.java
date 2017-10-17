package br.senac.rj.exercicio;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import br.senac.rj.exercicio.model.Apolice;

public class TestarApolice {

	public static void main(String[] args) {
		List<Apolice> listaApolice = null;
		float percentualAtualizacao = 0.0f;
		
		TestarApolice ta = new TestarApolice();
		
		listaApolice = ta.carregarApolices();
		
		ta.apresentarApolices(listaApolice);

		percentualAtualizacao = ta.informarPercentualAtualizacaoTodasApolices();
		ta.atualizarApolices(listaApolice, percentualAtualizacao);
		
		ta.apresentarApolices(listaApolice);
	}
	
	@SuppressWarnings("resource")
	public List<Apolice> carregarApolices() {
		List<Apolice> apolices = new ArrayList<>();
		Scanner entrada;
		
		entrada = new Scanner(System.in);

		for (int i = 0; i < 1; i++) {
			System.out.print("Informe o nome do Segurado: ");
			String nomeSegurado = entrada.nextLine();
			
			System.out.print("Informe a idade do Segurado: ");
			int idade = Integer.parseInt(entrada.nextLine());
			
			System.out.print("Informe o valor segurado: ");
			double valorSegurado = Double.parseDouble(entrada.nextLine());
			
			System.out.print("Informe a UF do segurado ('MG', 'PR', 'RJ', 'SP'): ");
			String uf = entrada.nextLine().substring(0, 2);
			
			Apolice apolice = new Apolice(nomeSegurado, idade, uf, valorSegurado);
			apolice.calcularPremio();
			
			apolices.add(apolice);
		}

		return apolices;
	}

	@SuppressWarnings("resource")
	public float informarPercentualAtualizacaoTodasApolices() {
		float percentualAtualizacao = 0.0f;
		Scanner entrada;
		
		entrada = new Scanner(System.in);
		System.out.print("Informe o percentual de atualização do valor segurado: ");
		percentualAtualizacao = Float.parseFloat(entrada.nextLine());
		
		return percentualAtualizacao;
	}

	public void atualizarApolices(List<Apolice> listaApol, float percentual) {
		for(Apolice apol: listaApol) {
			apol.atualizarValorSegurado(percentual);
		}
	}
	
	public void apresentarApolices(List<Apolice> listaApolices) {
		ListIterator<Apolice> iterator = listaApolices.listIterator();
		while (iterator.hasNext()) {
			Apolice ap = iterator.next();
			System.out.println("Apolice:  " + ap.toString());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
