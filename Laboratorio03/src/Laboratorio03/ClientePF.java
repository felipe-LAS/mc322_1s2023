package Laboratorio03;

import java.time.LocalDate;
import java.util.List;

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

    public boolean validarCPF() {
        String cpf = this.getCpf().replaceAll("\\D", ""); // Remove todos os caracteres não numéricos do CPF

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        boolean todosDigitosIguais = true;
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                todosDigitosIguais = false;
                break;
            }
        }
        if (todosDigitosIguais) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma += digito * (10 - i);
        }
        int resto = soma % 11;
        int digitoVerificador1 = (resto < 2) ? 0 : (11 - resto);

        // Verifica o primeiro dígito verificador
        if (digitoVerificador1 != Character.getNumericValue(cpf.charAt(9))) {
            return false;
        }

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma += digito * (11 - i);
        }
        resto = soma % 11;
        int digitoVerificador2 = (resto < 2) ? 0 : (11 - resto);

        // Verifica o segundo dígito verificador
        if (digitoVerificador2 != Character.getNumericValue(cpf.charAt(10))) {
            return false;
        }

        // Se chegou até aqui, o CPF é válido
        return true;
    } 
}

