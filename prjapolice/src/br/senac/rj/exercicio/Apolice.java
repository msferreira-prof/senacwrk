package br.senac.rj.exercicio;

public class Apolice {
	private String nomeSegurado;
	private int idade;
	private String uf;
	private double valorSegurado;
	private double valorPremio;
	private double valorDesconto;

	public String getNomeSegurado() {
		return nomeSegurado;
	}

	public void setNomeSegurado(String nomeSegurado) {
		this.nomeSegurado = nomeSegurado;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
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

	public Apolice(String nomeSegurado, int idade, String uf, double valorSegurado) {
		this.nomeSegurado = nomeSegurado;
		this.idade = idade;
		this.uf = uf;
		this.valorSegurado = valorSegurado;
	}

	public Apolice() {
		super();
	}

	@Override
	public String toString() {
		return "Apolice [nomeSegurado=" + nomeSegurado + ", idade=" + idade + ", uf=" + uf + ", valorSegurado="
				+ valorSegurado + ", valorPremio=" + valorPremio + ", valorDesconto=" + valorDesconto + "]";
	}
	
	public void imprimir() {
		System.out.println(this.toString());
	}
	
	public void calcularPremio() {
		double percentual = 0d;
		double valorDesconto = 0d;
		double valorBase = 0.0d;
		
		// percentual conforme a idade do segurado
		if (this.idade < 18) {
			percentual = 2.5 / 100.0;
		} else if (this.idade >= 18 & this.idade <= 25) {
			percentual = 15.0 / 100.0;
		} else if (this.idade > 25 & this.idade <= 36) {
			percentual = 10.0 / 100.0;
		} else {
			percentual = 5.0 /100.0;
		}
		
		// calcular valor de desconto
		valorBase = this.valorSegurado * percentual;
		this.calcularValorDesconto(valorBase);
		
		this.valorPremio = valorBase - this.valorDesconto; 
	}
	
	private void calcularValorDesconto(double valorBase) {
		double percentualDesconto = 0.0d;
		
		if ("RJ".equalsIgnoreCase(this.uf)) {
			percentualDesconto = 10d / 100d;
		} else if ("PR".equalsIgnoreCase(this.uf)) {
			percentualDesconto = 15d / 100d;
		} else if ("SP".equalsIgnoreCase(this.uf)) {
			percentualDesconto = 9d / 100d;
		} else { // MG
			percentualDesconto = 12d / 100d;
		}
		
		this.valorDesconto = valorBase * percentualDesconto;
	}
	
	public void atualizarValorSegurado(float percentual) {
		this.valorSegurado = this.valorSegurado * (1 + percentual / 100.0d);
		this.calcularPremio();		
	}
	
}
