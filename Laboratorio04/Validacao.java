package Laboratorio04;

public class Validacao {
	
	public static boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("\\D", ""); // Remove todos os caracteres não numéricos do CPF

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

	  public static boolean validarCNPJ(String cnpj) {
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
	  
	  public static boolean validaNome(String nome) {
		    if (nome == null || nome.isEmpty()) {
		        return false;
		    }

		    for (int i = 0; i < nome.length(); i++) {
		        char c = nome.charAt(i);
		        if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
		            return false;
		        }
		    }
		    return true;
		}
}
