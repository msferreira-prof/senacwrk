package br.senac.rj.exercicio.simulacaobancodados;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import br.senac.rj.exercicio.model.Apolice;
import br.senac.rj.exercicio.model.ApoliceSimples;

public class MapaApolices {
	private Map<Integer, ApoliceSimples> apolices;
	private int ultimaChaveGerada; 
	
	
	public MapaApolices() {
		super();
		apolices = new HashMap<>();
	}

	public Map<Integer, ApoliceSimples> getApolices() {
		return apolices;
	}

	public void setApolices(Map<Integer, ApoliceSimples> apolices) {
		this.apolices = apolices;
	}
	
	public void apresentarApolices() {
		Set<Integer> chavesMapa = this.apolices.keySet();
		Iterator<Integer> iterator = chavesMapa.iterator();
		while (iterator.hasNext()) {
			Integer chave = iterator.next();
			ApoliceSimples apolice = apolices.get(chave);
			System.out.printf("Apolice [%d]:  %s\n", 
							 chave.intValue(), 
							 apolice.toString());
		}
	}
	
	public void atualizarApolices(float percentualReajuste) {
		Set<Integer> chavesMapa = this.apolices.keySet();
		for(Integer chave: chavesMapa) {
			ApoliceSimples apolice = apolices.get(chave);
			apolice.atualizarValorSegurado(percentualReajuste);
		}
	}
	
	public void incluir(ApoliceSimples apolice) {
		int novaChave = 0;
		int qtdChavesMapa = apolices.size();
		
		if (qtdChavesMapa == 0) {
			novaChave = 1;
		} else {
			novaChave = ultimaChaveGerada + 1; 
		}
		
		apolices.put(new Integer(novaChave), apolice);
		ultimaChaveGerada = novaChave;		
	}
	
}










