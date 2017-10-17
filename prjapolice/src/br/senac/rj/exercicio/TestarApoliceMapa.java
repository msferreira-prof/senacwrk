package br.senac.rj.exercicio;

import java.util.Scanner;

import br.senac.rj.exercicio.model.Apolice;
import br.senac.rj.exercicio.simular.bd.MapaApolices;

public class TestarApoliceMapa {
	
	public static void main(String[] args) {
		MapaApolices apolices = null;
		float percentualAtualizacao = 0.0f;
		
		apolices = carregarApolices();
		
		apolices.apresentarApolices();

		percentualAtualizacao = informarPercentualAtualizacaoTodasApolices();
		
		apolices.atualizarApolices(percentualAtualizacao);
		
		apolices.apresentarApolices();
	}
	
	@SuppressWarnings("resource")
	public static MapaApolices carregarApolices() {
		MapaApolices apolices = new MapaApolices();
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
			
			apolices.incluir(apolice);
		}

		return apolices;
	}

	@SuppressWarnings("resource")
	public static float informarPercentualAtualizacaoTodasApolices() {
		float percentualAtualizacao = 0.0f;
		Scanner entrada;
		
		entrada = new Scanner(System.in);
		System.out.print("Informe o percentual de atualização do valor segurado: ");
		percentualAtualizacao = Float.parseFloat(entrada.nextLine());
		
		return percentualAtualizacao;
	}
}
