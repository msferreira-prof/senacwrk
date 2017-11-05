package br.senac.rj.exercicio.model;

import java.io.Serializable;

public class Apolice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1675951038069155044L;
	private long numero;
	private Segurado segurado;
	private String uf;
	private double valorSegurado;
	private double valorPremio;
	private double valorDesconto;

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public Segurado getSegurado() {
		return segurado;
	}

	public void setSegurado(Segurado segurado) {
		this.segurado = segurado;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public double getValorSegurado() {
		return valorSegurado;
	}

	public void setValorSegurado(double valorSegurado) {
		this.valorSegurado = valorSegurado;
	}

	public double getValorPremio() {
		return valorPremio;
	}

	public void setValorPremio(double valorPremio) {
		this.valorPremio = valorPremio;
	}

	public double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public Apolice() {
		super();
	}

	public Apolice(long numero, Segurado segurado, String uf, double valorSegurado) {
		super();
		this.numero = numero;
		this.segurado = segurado;
		this.uf = uf;
		this.valorSegurado = valorSegurado;
	}

	@Override
	public String toString() {
		return "Apolice [numero=" + numero + "segurado=" + segurado + ", uf=" + getUf() + ", valorSegurado="
				+ getValorSegurado() + ", valorPremio=" + getValorPremio() + ", valorDesconto=" + getValorDesconto() + "]";
	}

	public void imprimir() {
		System.out.println(this.toString());
	}

	public void calcularPremio() {
		double percentual = 0d;
		double valorDesconto = 0d;
		double valorBase = 0.0d;

		// percentual conforme a idade do segurado
		if (this.segurado.getIdade() < 18) {
			percentual = 2.5 / 100.0;
		} else if (this.segurado.getIdade() >= 18 & this.segurado.getIdade() <= 25) {
			percentual = 15.0 / 100.0;
		} else if (this.segurado.getIdade() > 25 & this.segurado.getIdade() <= 36) {
			percentual = 10.0 / 100.0;
		} else {
			percentual = 5.0 /100.0;
		}

		// calcular valor de desconto
		valorBase = this.getValorSegurado() * percentual;
		this.calcularValorDesconto(valorBase);

		this.setValorPremio(valorBase - this.getValorDesconto());
	}

	private void calcularValorDesconto(double valorBase) {
		double percentualDesconto = 0.0d;

		if ("RJ".equalsIgnoreCase(this.getUf())) {
			percentualDesconto = 10d / 100d;
		} else if ("PR".equalsIgnoreCase(this.getUf())) {
			percentualDesconto = 15d / 100d;
		} else if ("SP".equalsIgnoreCase(this.getUf())) {
			percentualDesconto = 9d / 100d;
		} else { // MG
			percentualDesconto = 12d / 100d;
		}

		this.setValorDesconto(valorBase * percentualDesconto);
	}

	public void atualizarValorSegurado(float percentual) {
		this.setValorSegurado(this.getValorSegurado() * (1 + percentual / 100.0d));
		this.calcularPremio();
	}


}
