package Laboratorio03;

import java.time.LocalDate;
import java.util.List;

public class ClientePJ extends Cliente {
	 private String cnpj;
	 private LocalDate dataFundacao;

	 public ClientePJ(String nome, String endereco, List<Veiculo> veiculos, String cnpj, LocalDate dataFundacao) {
	     super(nome, endereco,veiculos);
	     this.cnpj = cnpj;
	     this.dataFundacao = dataFundacao;
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

	 @Override
	 public String toString() {
	     return super.toString() 
	     + "\nCNPJ = " + this.getCnpj() + 
	     "\nDataFundacao = " + this.getDataFundacao();
	    }

	// Método que valida o CNPJ do cliente
	    public boolean validarCNPJ() {
	     cnpj = cnpj.replaceAll("[^0-9]", ""); // Remove qualquer caractere que não seja numero do CNPJ

	     if (cnpj.length() != 14) // Verifica se o CNPJ tem exatamente 14 digitos
	        return false;

	        int soma = 0;
	        int digito;

	        String cnpjCalculado = cnpj.substring(0, 12); // Converte o CNPJ em um array de caracteres

	        char[] caracteresCnpj = cnpj.toCharArray();
	        for (int i = 0; i < 4; i++) {
	           if (Character.isDigit(caracteresCnpj[i])) { // Verifica se o caractere é um digito
	             soma += Integer.parseInt(String.valueOf(caracteresCnpj[i])) * (6 - (i + 1)); // Realiza o calculo de acordo com a formula da Receita Federal
               }
	                }

	         for (int i = 0; i < 8; i++) {
	            if (Character.isDigit(caracteresCnpj[i + 4])) {  // Verifica se o caractere é um digito
	              soma += Integer.parseInt(String.valueOf(caracteresCnpj[i + 4])) * (10 - (i + 1)); // Realiza o calculo de acordo com a formula da Receita Federal
	                }
	            }

	         digito = 11 - (soma % 11);

	         if (digito > 9)
	         digito = 0;

	         cnpjCalculado = cnpjCalculado + digito;

	         soma = 0;
	         // Calculo do terceiro digito veriificador do CNPJ
	         for (int i = 0; i < 5; i++) {
	             if (Character.isDigit(caracteresCnpj[i])) {
	                 soma += Integer.parseInt(String.valueOf(caracteresCnpj[i])) * (7 - (i + 1));
	                }
	            }

	         for (int i = 0; i < 8; i++) {
	             if (Character.isDigit(caracteresCnpj[i + 5])) {
	                 soma += Integer.parseInt(String.valueOf(caracteresCnpj[i + 5])) * (10 - (i + 1));
	                }
	            }

	         digito = 11 - (soma % 11);

	         if (digito > 9)
	             digito = 0;

	         cnpjCalculado = cnpjCalculado + digito;

	         return cnpj.equals(cnpjCalculado); // Compara o CNPJ original com o CNPJ calculado com os dígitos verificadores. Se forem iguais, retorna true, caso contrário, retorna false.
	        }
	}
	


