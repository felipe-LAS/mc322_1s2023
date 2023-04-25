package Laboratorio03;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Criando clientes
        ClientePF clientePF = new ClientePF("Felipe", "Rua RM, 456", new ArrayList<Veiculo>(), LocalDate.of(2002, 10, 02), "Superior",
                "Masculino", "Classe Média", "166.954.437-00", LocalDate.of(2020, 12, 20));

        ClientePJ clientePJ = new ClientePJ("Empresa Country", "Avenida GN, 276", new ArrayList<Veiculo>(), "05.316.035/0001-58", LocalDate.of(1999, 10, 20)
                );

        // Adicionando veículos aos clientes
        Veiculo veiculo1 = new Veiculo("Ferrari", "GT", "AFF-2134", 2020);
        Veiculo veiculo2 = new Veiculo("Bugatti", "Veron", "DEF-5987", 2000);

        clientePF.getListaVeiculos().add(veiculo1);
        clientePJ.getListaVeiculos().add(veiculo2);

        // Validando CPF e CNPJ
        if (clientePF.validarCPF()) {
            System.out.println("CPF válido!");
        } else {
            System.out.println("CPF inválido!");
        }

        if (clientePJ.validarCNPJ()) {
            System.out.println("CNPJ válido!");
        } else {
            System.out.println("CNPJ inválido!");
        }

        // Instanciando seguradora
        Seguradora seguradora = new Seguradora("Seguradora SafeCar", "(11)2451-2890", "safecar@gmail.com", "AvenidaBrasil");

        // Cadastrando clientes na seguradora
        seguradora.cadastrarCliente(clientePF);
        
        System.out.println("------------");
        
        seguradora.cadastrarCliente(clientePJ);

        // Listando clientes e sinistros
        System.out.println("Clientes da Seguradora:");
        seguradora.listarClientes("PF");
        
        System.out.println("------------");
        
        seguradora.listarClientes("PJ");
        
        Sinistro sinistro = new Sinistro(25789, "10/10/2010", "Avenida Paulista", seguradora, veiculo1, clientePF);
        seguradora.gerarSinistro(sinistro);
        

        Sinistro sinistro2 = new Sinistro(202020, "01/09/2015", "Avenida Oscar", seguradora, veiculo2, clientePJ);
        seguradora.gerarSinistro(sinistro2);
        
        System.out.println("\nSinistros da Seguradora:");
        seguradora.listarSinistros();
        
        System.out.println("------------");

        // Visualizando sinistro de um cliente
        System.out.println("\nVisualizacao do sinistro do clientePF " + clientePF.getNome() + ":");
        seguradora.visualizarSinistro(clientePF.getNome());

        // Leitura de dados do teclado
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nDigite o nome do cliente a ser removido: ");
        String nomeClienteRemover = scanner.nextLine();

        // Removendo cliente da seguradora
        seguradora.removerCliente(nomeClienteRemover);

        System.out.println("\nClientes da Seguradora (após remoção):");
        seguradora.listarClientes("PF");
        seguradora.listarClientes("PJ");
        scanner.close();
    }
}