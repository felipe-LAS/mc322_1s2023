package Laboratorio04;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppMain {
	Seguradora seguradora;
    List<Cliente> clientes = new ArrayList<>();
    List<Veiculo> veiculos = new ArrayList<>();
    List<Seguradora> seguradoras = new ArrayList<>();
    List<Sinistro> sinistros = new ArrayList<>();

    public static void main(String[] args) {
        AppMain main = new AppMain();
        main.instanciarObjetos();
        main.run();
    }

    public void instanciarObjetos() {
        // Criando clientes
        ClientePF clientePF = new ClientePF("Felipe", "Rua RM, 456", new ArrayList<Veiculo>(), LocalDate.of(2002, 10, 02), "Superior",
                "Masculino", "Classe Média", "166.954.437-00", LocalDate.of(2020, 12, 20));

        ClientePJ clientePJ = new ClientePJ("Empresa Country", "Avenida GN, 276", new ArrayList<Veiculo>(), "05.316.035/0001-58", LocalDate.of(1999, 10, 20), 100);

     // Para clientePF
        double valorSeguroPF = clientePF.calcularValorSeguro();
        double scorePF = clientePF.calculaScore();
        System.out.printf("Valor do seguro (ClientePF): %.2f\n", valorSeguroPF);
        System.out.printf("Score (ClientePF): %.2f\n", scorePF);

     // Para clientePJ
        double valorSeguroPJ = clientePJ.calcularValorSeguro();
        double scorePJ = clientePJ.calculaScore();
        System.out.printf("Valor do seguro (ClientePJ): %.2f\n", valorSeguroPJ);
        System.out.printf("Score (ClientePJ): %.2f\n", scorePJ);

        // Adicionando veículos aos clientes
        Veiculo veiculo1 = new Veiculo("Ferrari", "GT", "AFF-2134", 2020);
        Veiculo veiculo2 = new Veiculo("Bugatti", "Veron", "DEF-5987", 2000);

        clientePF.getListaVeiculos().add(veiculo1);
        clientePJ.getListaVeiculos().add(veiculo2);

        // Instanciando uma seguradora
        seguradora = new Seguradora("Seguradora SafeCar", "(11)2451-2890", "safecar@gmail.com", "AvenidaBrasil");

        // Gerar sinistros
        Sinistro sinistro1 = new Sinistro(1, "01/05/2023", "Rua 4, 111", seguradora, veiculo1, clientePF);
        Sinistro sinistro2 = new Sinistro(2, "02/05/2023", "Rua 5, 222", seguradora, veiculo2, clientePJ);

        // Adicionando os objetos às listas
        clientes.add(clientePF);
        clientes.add(clientePJ);
        veiculos.add(veiculo1);
        veiculos.add(veiculo2);
        seguradoras.add(seguradora);
        sinistros.add(sinistro1);
        sinistros.add(sinistro2);
        seguradora.cadastrarCliente(clientePF);
        seguradora.cadastrarCliente(clientePJ);
        seguradora.listarSinistros();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        MenuOperacoes menuOperacao;
        int opcao;

        do {
            System.out.println("\n==== Menu Principal ====");
            for (MenuOperacoes menu : MenuOperacoes.values()) {
                if (menu.getCodigo() % 10 == 0) {
                    System.out.printf("%d. %s\n", menu.getCodigo(), menu.getDescricao());
                    System.out.println("Por favor, escolha uma das opções abaixo:");
                    System.out.println("1. Cadastro de clientes");
                    System.out.println("2. Listagem");
                    System.out.println("3. Exclusão");
                    System.out.println("4. Gerar sinistro");
                    System.out.println("5. Transferir seguro");
                    System.out.println("6. Calcular receita da seguradora");
                    System.out.println("0. Sair");
                }
            }
            System.out.print("Digite a opção desejada: ");
            opcao = scanner.nextInt();
            menuOperacao = MenuOperacoes.fromCodigo(opcao);

            switch (menuOperacao) {
                case SAIR:
                    System.out.println("Saindo do programa...");
                    break;
                case CADASTROS:
                    tratarCadastro(scanner);
                    break;
                case LISTAR:
                    tratarListagem(scanner);
                    break;
                case EXCLUIR:
                    tratarExclusao(scanner);
                    break;
                case GERAR_SINISTRO:
                    tratarGerarSinistro(scanner);
                    break;
                case TRANSFERIR_SEGURO:
                    tratarTransferirSeguro(scanner);
                    break;
                case CALCULAR_RECEITA_SEGURADORA:
                    tratarCalcularReceita(scanner);
                    break;
                 default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (menuOperacao != MenuOperacoes.SAIR);

        scanner.close(); }

    public void tratarCadastro(Scanner scanner) {
    	boolean continuar = true;
        while (continuar) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Cadastrar Cliente PF/PJ");
            System.out.println("2 - Cadastrar Veículo");
            System.out.println("3 - Cadastrar Seguradora");
            System.out.println("4 - Voltar");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarClientePF_PJ(scanner);
                    break;
                case 2:
                    cadastrarVeiculo(scanner);
                    break;
                case 3:
                    cadastrarSeguradora(scanner);
                    break;
                case 4:
                    continuar = false;
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
            }
        }
        public void cadastrarClientePF_PJ(Scanner scanner) {

        System.out.print("Informe o tipo de cliente (PF ou PJ): ");
        String tipoCliente = scanner.next();
        scanner.nextLine();

        System.out.print("Informe o nome do cliente: ");
        String nomeCliente = scanner.nextLine();

        if (!Validacao.validaNome(nomeCliente)) {
            System.out.println("Nome inválido. Tente novamente.");
            return;
        }
        System.out.print("Informe o endereço do cliente: ");
        String enderecoCliente = scanner.nextLine();

        Cliente cliente;

        if (tipoCliente.equalsIgnoreCase("PF")) {
            System.out.print("Informe o CPF: ");
            String cpf = scanner.next();


            if (Validacao.validarCPF(cpf) == true) {
                System.out.println("CPF válido!");
            } else {
                System.out.println("CPF inválido!");
            }
            System.out.print("Informe a data de nascimento (AAAA-MM-DD): ");
            String dataNascimento = scanner.next();
            LocalDate dataNasc = LocalDate.parse(dataNascimento);
            System.out.print("Informe a data da primeira licença (AAAA-MM-DD): ");
            String dataLicenca = scanner.next();
            LocalDate dataLic = LocalDate.parse(dataLicenca);
            System.out.print("Informe o nível de educação: ");
            String educacao = scanner.next();
            System.out.print("Informe o gênero: ");
            String genero = scanner.next();
            System.out.print("Informe a classe econômica: ");
            String classeEconomica = scanner.next();

            cliente = new ClientePF(nomeCliente, enderecoCliente, new ArrayList<Veiculo>(), dataNasc, educacao, genero, classeEconomica, cpf, dataLic);

						seguradora.cadastrarCliente(cliente);

        } else if (tipoCliente.equalsIgnoreCase("PJ")) {
            System.out.print("Informe o CNPJ: ");
            String cnpj = scanner.next();
            if (Validacao.validarCNPJ(cnpj)) {
                System.out.println("CNPJ válido!");
            } else {
                System.out.println("CNPJ inválido!");
            }
            System.out.print("Informe a data de fundação (AAAA-MM-DD): ");
            String dataFundacao = scanner.next();
            LocalDate dataFund = LocalDate.parse(dataFundacao);
            System.out.print("Informe a quantidade de funcionários: ");
            int qtdeFuncionarios = scanner.nextInt();

            cliente = new ClientePJ(nomeCliente, enderecoCliente, new ArrayList<Veiculo>(), cnpj, dataFund, qtdeFuncionarios);

						seguradora.cadastrarCliente(cliente);
        } else {
            System.out.println("Tipo de cliente inválido.");
            return;
        }

        // Para ClientePF
        if (cliente instanceof ClientePF) {
            double valorSeguro = ((ClientePF) cliente).calcularValorSeguro();
            double score = ((ClientePF) cliente).calculaScore();
            System.out.printf("Valor do seguro: %.2f\n", valorSeguro);
            System.out.printf("Score: %.2f\n", score);
            clientes.add(cliente);
        }
        // Para ClientePJ
        else if (cliente instanceof ClientePJ) {
            double valorSeguro = ((ClientePJ) cliente).calcularValorSeguro();
            double score = ((ClientePJ) cliente).calculaScore();
            System.out.printf("Valor do seguro: %.2f\n", valorSeguro);
            System.out.printf("Score: %.2f\n", score);
            clientes.add(cliente);
        }
        }
        public void cadastrarVeiculo(Scanner scanner) {

        System.out.print("Informe o nome do cliente: ");
        String nomeCliente = scanner.next();

        Cliente cliente = seguradora.buscarClientePorNome(nomeCliente);

        if (cliente != null) {
        	int qtdeVeiculos;
        	System.out.print("Informe a quantidade de veículos: ");
        	qtdeVeiculos = scanner.nextInt();

        	for (int i = 0; i < qtdeVeiculos; i++) {
        	   System.out.println("Cadastro do veículo " + (i + 1) + ":");
        	   System.out.print("Informe a marca do veículo: ");
        	   String marca = scanner.next();
        	   System.out.print("Informe o modelo do veículo: ");
        	   String modelo = scanner.next();
        	   System.out.print("Informe a placa do veículo: ");
        	   String placa = scanner.next();
        	   System.out.print("Informe o ano do veículo (AAAA): ");
        	   int ano = scanner.nextInt();

        	   Veiculo veiculo = new Veiculo(marca, modelo, placa, ano);
        	   cliente.getListaVeiculos().add(veiculo);
        	        }
        	    } else {
        	        System.out.println("Cliente não encontrado.");
        	    }
        }

        public void cadastrarSeguradora(Scanner scanner) {
            System.out.println("Cadastro da seguradora:");
            System.out.print("Informe o nome da seguradora: ");
            String nome = scanner.next();
            scanner.nextLine();

            if (seguradora != null && seguradora.getNome().equalsIgnoreCase(nome)) {
                System.out.println("Seguradora já cadastrada!");
                return;
            }

            System.out.print("Informe o telefone da seguradora: ");
            String telefone = scanner.next();
            System.out.print("Informe o email da seguradora: ");
            String email = scanner.next();
            System.out.print("Informe o endereço da seguradora: ");
            String endereco = scanner.next();

            seguradora = new Seguradora(nome, telefone, email, endereco);
            System.out.println("Seguradora cadastrada com sucesso!");
        }



        public void tratarListagem(Scanner scanner) {
        	 boolean continuar = true;
        	    while (continuar) {
        	        System.out.println("Escolha uma opção:");
        	        System.out.println("1 - Listar Cliente (PF/PJ) por Seguradora");
        	        System.out.println("2 - Listar Sinistros por Seguradora");
        	        System.out.println("3 - Listar Sinistro por Cliente");
        	        System.out.println("4 - Listar Veiculo por Cliente");
        	        System.out.println("5 - Listar Veiculo por Seguradora");
        	        System.out.println("6 - Voltar");
        	        System.out.print("Opção: ");
        	        int opcao = scanner.nextInt();
        	        scanner.nextLine(); // Consome o caractere de nova linha (enter)

        	        switch (opcao) {
        	            case 1:
        	                System.out.print("Informe o tipo de cliente (PF ou PJ): ");
        	                String tipoCliente = scanner.next();
        	                seguradora.listarClientes(tipoCliente);
        	                break;
        	            case 2:
        	                seguradora.listarSinistrosPorSeguradora();
        	                break;
        	            case 3:
        	                System.out.print("Informe o nome do cliente: ");
        	                String nomeCliente = scanner.next();
        	                seguradora.listarSinistrosPorCliente(nomeCliente);
        	                break;
        	            case 4:
        	                System.out.print("Informe o nome do cliente: ");
        	                nomeCliente = scanner.next();
        	                seguradora.listarVeiculosPorCliente(nomeCliente);
        	                break;
        	            case 5:
        	                seguradora.listarVeiculosPorSeguradora();
        	                break;
        	            case 6:
        	                continuar = false;
        	                System.out.println("Retornando ao menu principal...");
        	                break;
        	            default:
        	                System.out.println("Opção inválida!");
        	                break;
        	        }
        	    }
        }
        public void tratarExclusao(Scanner scanner) {
        	boolean continuar = true;
            while (continuar) {
                System.out.println("Escolha uma opção:");
                System.out.println("1 - Excluir Cliente");
                System.out.println("2 - Excluir Veículo");
                System.out.println("3 - Excluir Sinistro");
                System.out.println("4 - Voltar");
                System.out.print("Opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consome o caractere de nova linha (enter)

                switch (opcao) {
                    case 1:
                        System.out.print("Informe o nome do cliente a ser removido: ");
                        String nomeCliente = scanner.next();
                        if (seguradora.removerCliente(nomeCliente)) {
                            System.out.println("Cliente removido com sucesso!");
                        } else {
                            System.out.println("Cliente não encontrado!");
                        }
                        break;
                    case 2:
                        System.out.print("Informe o nome do cliente: ");
                        nomeCliente = scanner.next();
                        System.out.print("Informe a placa do veículo a ser removido: ");
                        String placaVeiculo = scanner.next();
                        if (seguradora.removerVeiculo(nomeCliente, placaVeiculo)) {
                            System.out.println("Veículo removido com sucesso!");
                        } else {
                            System.out.println("Veículo não encontrado!");
                        }
                        break;
                    case 3:
                        System.out.print("Informe o ID do sinistro a ser removido: ");
                        int sinistroId = scanner.nextInt();
                        if (seguradora.removerSinistro(sinistroId)) {
                            System.out.println("Sinistro removido com sucesso!");
                        } else {
                            System.out.println("Sinistro não encontrado!");
                        }
                        break;
                    case 4:
                        continuar = false;
                        System.out.println("Retornando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            }
        }

        public void tratarGerarSinistro(Scanner scanner) {

        	 System.out.print("Informe o nome do cliente: ");
        	 String nomeCliente = scanner.next();

        	 Cliente cliente = seguradora.buscarClientePorNome(nomeCliente);

        	    if (cliente == null) {
        	    	 System.out.println("Cliente não encontrado!");
        	         return;
        	}

        	    System.out.print("Informe a placa do veículo: ");
        	    String placa = scanner.next();
        	    Veiculo veiculo = cliente.buscarVeiculo(placa);
        	    if (veiculo == null) {
        	        System.out.println("Veículo não encontrado!");
        	        return;
        	    }
        	    System.out.print("Informe o ID do sinistro: ");
        	    String idStr = scanner.next();
        	    int id = Integer.parseInt(idStr);
        	    System.out.print("Informe a data do sinistro: ");
        	    String dataSinistro = scanner.next();
        	    System.out.print("Informe o endereco do sinistro: ");
        	    String enderecoSinistro = scanner.next();


        	    Sinistro sinistro = new Sinistro(id, dataSinistro, enderecoSinistro, seguradora, veiculo, cliente);
        	    if (seguradora.gerarSinistro(sinistro)) {
        	        System.out.println("Sinistro gerado com sucesso!");
        	    } else {
        	        System.out.println("Erro ao gerar sinistro!");
        	    }
        }



        public void tratarTransferirSeguro(Scanner scanner) {
            System.out.print("Informe o nome do cliente atual: ");
            String nomeClienteAtual = scanner.next();
            System.out.print("Informe o nome do cliente para transferir o seguro: ");
            String nomeClienteNovo = scanner.next();

            Cliente clienteOrigem = seguradora.buscarClientePorNome(nomeClienteAtual);
            Cliente clienteDestino = seguradora.buscarClientePorNome(nomeClienteNovo);

            if (clienteOrigem != null && clienteDestino != null) {
                if (seguradora.transferirSeguro(clienteOrigem, clienteDestino)) {
                    System.out.println("Seguro transferido com sucesso!");
                } else {
                    System.out.println("Erro na transferência do seguro!");
                }
            } else {
                System.out.println("Um ou ambos os clientes não foram encontrados!");
            }
        }


        public void tratarCalcularReceita(Scanner scanner) {
            System.out.println("A receita total da seguradora é: " + seguradora.calcularReceita());
        }
    }
