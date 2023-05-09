package Laboratorio04;

import java.time.LocalDate;
import java.util.List;
import java.time.Period;

	public class ClientePF extends Cliente {
	    
	    private final String cpf;
	    private LocalDate dataNascimento;
	    private LocalDate dataLicenca;
	    private String educacao;
	    private String genero;
	    private String classeEconomica;
	    

	  public ClientePF(String nome, String endereco, List<Veiculo> listaVeiculos, LocalDate dataNascimento, String educacao, String genero,
	                     String classeEconomica, String cpf, LocalDate dataLicenca) {
	        super(nome, endereco,listaVeiculos);
	        this.cpf = cpf;
	        this.dataNascimento = dataNascimento;
	        this.dataLicenca = dataLicenca;
	        this.educacao = educacao;
	        this.genero = genero;
	        this.classeEconomica = classeEconomica;
	    }

	    public String getCpf() {
	        return cpf;
	    }

	    public LocalDate getDataNascimento() {
	        return dataNascimento;
	    }

	    public void setDataNascimento(LocalDate dataNascimento) {
	        this.dataNascimento = dataNascimento;
	    }
	    
	    public LocalDate getDataLicenca() {
	        return dataLicenca;
	        }
	    
	    public void setDataLicenca(LocalDate dataLicenca) {
	        this.dataLicenca = dataLicenca;
	        }
	    
	    public String getEducacao() {
	        return educacao;
	        }
	    
	    public void setEducacao(String educacao) {
	        this.educacao = educacao;
	        }
	    
	    public String getGenero() {
	        return genero;
	        }
	    
	    public void setGenero(String genero) {
	        this.genero = genero;
	        }
	    
	    public String getClasseEconomica() {
	        return classeEconomica;
	        }
	    
	    public void setClasseEconomica(String classeEconomica) {
	        this.classeEconomica = classeEconomica;
	        }
	    @Override
	    public String toString() {
	        return super.toString() +
	                "\nCPF: " + this.getCpf() +
	                "\nData de Nascimento: " + this.getDataNascimento() +
	        		"\nData da Licença: " + this.getDataLicenca() +
	        		"\nEducação: " + this.getEducacao() +
	        		"\nGênero: " + this.getGenero() +
	        		"\nClasse Econômica: " + this.getClasseEconomica();
	    }


	    public double calcularValorSeguro() {
	        double valorBase = CalcSeguro.VALOR_BASE.getValor();
	        double fatorIdade = fatorIdade();
	        int quantidadeSinistros = getSinistros().size();

	        return valorBase * fatorIdade * (1 + 0.1 * quantidadeSinistros);
	    }
	    @Override
	    public double calculaScore() {
	        double VALOR_BASE =CalcSeguro.VALOR_BASE.getValor();
	        double FATOR_IDADE = fatorIdade();
	        int quantidadeCarros = getListaVeiculos().size();

	        return VALOR_BASE * FATOR_IDADE * quantidadeCarros;
	    }

	    private double fatorIdade() {
	        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();

	        if (idade < 18) {
	            return CalcSeguro.FATOR_18_30.getValor();
	        } else if (idade >= 30 && idade <= 60) {
	            return CalcSeguro.FATOR_30_60.getValor();
	        } else {
	            return CalcSeguro.FATOR_60_90.getValor();
	        }
	    }
}
