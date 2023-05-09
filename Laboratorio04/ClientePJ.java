package Laboratorio04;

import java.time.LocalDate;
import java.util.List;

public class ClientePJ extends Cliente {
	 private String cnpj;
	 private LocalDate dataFundacao;
	 private int qtdeFuncionarios;

	 public ClientePJ(String nome, String endereco, List<Veiculo> veiculos, String cnpj, LocalDate dataFundacao, int qtdeFuncionarios) {
	     super(nome, endereco,veiculos);
	     this.cnpj = cnpj;
	     this.dataFundacao = dataFundacao;
	     this.qtdeFuncionarios = qtdeFuncionarios;
	    }

	 public String getCnpj() {
	     return cnpj;
	    }

	 public LocalDate getDataFundacao() {
	     return dataFundacao;
	    }

	 public void setDataFundacao(LocalDate dataFundacao) {
	     this.dataFundacao = dataFundacao;
	    }
	 public int getQtdeFuncionarios() {
	        return qtdeFuncionarios;
	    }

	    public void setQtdeFuncionarios(int qtdeFuncionarios) {
	        this.qtdeFuncionarios = qtdeFuncionarios;
	    }

	 @Override
	 public String toString() {
	     return super.toString() 
	     + "\nCNPJ = " + this.getCnpj() + 
	     "\nDataFundacao = " + this.getDataFundacao() +
	     "\nQtdeFuncionarios = " + this.getQtdeFuncionarios();
	    }
	 public double calculaScore() {
	        double VALOR_BASE = CalcSeguro.VALOR_BASE.getValor();
	        int quantidadeCarros = getListaVeiculos().size();

	        return VALOR_BASE * (1 + (double) qtdeFuncionarios / 100) * quantidadeCarros;
	    }
	    
	  public double calcularValorSeguro() {
	        double valorBase = CalcSeguro.VALOR_BASE.getValor();
	        double fatorFuncionarios = 1 + (double) getQtdeFuncionarios() / 100;
	        int quantidadeSinistros = getSinistros().size();

	        return valorBase * fatorFuncionarios * (1 + 0.1 * quantidadeSinistros);
	    }
}
	



