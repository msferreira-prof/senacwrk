package br.senac.rj.exercicio;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class TestarApoliceMapaSimples {

	public static void main(String[] args) {
		Map<Integer, Apolice> mapaApolices = new HashMap<>();
		float percentualAtualizacao = 0.0f;
		
		TestarApoliceMapaSimples ta = new TestarApoliceMapaSimples();
		
		mapaApolices = ta.carregarApolices();
		
		ta.apresentarApolices(mapaApolices);

		percentualAtualizacao = ta.informarPercentualAtualizacaoTodasApolices();
		ta.atualizarApolices(mapaApolices, percentualAtualizacao);
		
		ta.apresentarApolices(mapaApolices);
	}
	
	@SuppressWarnings("resource")
	public Map<Integer, Apolice> carregarApolices() {
		Map<Integer, Apolice> apolices = new HashMap<>();
		Scanner entrada;
		
		entrada = new Scanner(System.in);

		for (int i = 0; i < 2; i++) {
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
			
			int ultimaChaveMapa = recuperarUltimaChaveMapa(apolices);
			apolices.put(new Integer(ultimaChaveMapa), apolice);
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

	public void atualizarApolices(Map<Integer, Apolice> mapaApolices, float percentual) {
		Set<Integer> chaves = mapaApolices.keySet();
		for(Integer chave: chaves) {
			mapaApolices.get(chave).atualizarValorSegurado(percentual);
		}
	}
	
	public void apresentarApolices(Map<Integer, Apolice> mapaApolices) {
		Set<Integer> chaves = mapaApolices.keySet();  
		Iterator<Integer> iterator = chaves.iterator();
		while (iterator.hasNext()) {
			Integer chave = iterator.next();
			Apolice ap = mapaApolices.get(chave);
			System.out.printf("Apolice [%d]:  %s\n", chave.intValue(), ap.toString());
		}
	}
	
	public int recuperarUltimaChaveMapa(Map<Integer, Apolice> mapaApolices) {
		int novaChave = 0;
		
		if (mapaApolices.isEmpty() | mapaApolices.size() == 0) {
			novaChave = 1;
		} else {
			int maiorChave = 0;
			Set<Integer> chaves = mapaApolices.keySet();
			for(Integer chave: chaves) {
				if (chave.intValue() > maiorChave) {
					maiorChave = chave.intValue();
				}
			}
			novaChave = maiorChave + 1;
		}
		
		return novaChave;
	}
	
}
