package laboratorio05;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeParseException;


public class AppMain {
    List<Cliente> clientes = new ArrayList<>();
    List<Veiculo> veiculos = new ArrayList<>();
    List<Seguradora> seguradoras = new ArrayList<>();
    List<Sinistro> sinistros = new ArrayList<>();
    List<Frota> frotas = new ArrayList<>();
    List<Seguro> seguros = new ArrayList<>();
    List<Condutor> condutores = new ArrayList<>();
    Seguradora seguradora;

    public static void main(String[] args) {
        AppMain main = new AppMain();
        main.instanciarObjetos();
        main.run();
    }

    public void instanciarObjetos() {
        // Criando clientes
        ClientePF clientePF = new ClientePF("Felipe", "Rua RM, 456", "22998784520", "felipelamimsantos@gmail.com", "166.954.437-00", "Masculino", "Ensino Superior", LocalDate.of(2002, 10, 02));
        ClientePJ clientePJ = new ClientePJ("Empresa Country", "Avenida GN, 276", "22998784520", "nfcc@gmail.com", "05.316.035/0001-58", LocalDate.of(1999, 10, 20), 99);

        // Adicionando veículos aos clientes
        Veiculo veiculo1 = new Veiculo("Ferrari", "GT", "AFF-2134", 2020);
        Veiculo veiculo2 = new Veiculo("Bugatti", "Veron", "DEF-5987", 2000);

        clientePF.getListaVeiculos().add(veiculo1);
        clientePJ.getListaVeiculos().add(veiculo2);

        // Criando uma frota e adicionando os veículos do clientePJ à frota
        Frota frota = new Frota("frota1");
        frota.setListaVeiculos(clientePJ.getListaVeiculos());

        // Instanciando uma seguradora
        seguradora = new Seguradora("cnpj", "Seguradora SafeCar", "(11)2451-2890", "AvenidaBrasil", "safecar@gmail.com");
        seguradoras.add(seguradora);

        // Criar os objetos necessários para o construtor de SeguroPF e SeguroPJ
        int id = 1; // valor do id
        LocalDate dataInicio = LocalDate.of(2023, 1, 1); // data de início
        LocalDate dataFim = LocalDate.of(2024, 1, 1); // data de fim

     // Adicionando os objetos às listas
        clientes.add(clientePF);
        clientes.add(clientePJ);
        veiculos.add(veiculo1);
        veiculos.add(veiculo2);
        seguradoras.add(seguradora);

        // Adicionar os clientes à seguradora e listar seguros
        seguradora.cadastrarCliente(clientePF);
        seguradora.cadastrarCliente(clientePJ);
        seguradora.listarSeguros();
        
        // instanciar SeguroPF e SeguroPJ
        SeguroPF seguroPF = new SeguroPF(id, dataInicio, dataFim, seguradora, veiculo1, clientePF);
        seguroPF.inicializar();
        SeguroPJ seguroPJ = new SeguroPJ(id, dataInicio, dataFim, seguradora, frota, clientePJ);
        seguroPJ.inicializar();

        // Para clientePF
        double valorSeguroPF = seguroPF.getValorMensal();
        System.out.printf("Valor do seguro (ClientePF): %.2f\n", valorSeguroPF);

        // Para clientePJ
        double valorSeguroPJ = seguroPJ.getValorMensal();
        System.out.printf("Valor do seguro (ClientePJ): %.2f\n", valorSeguroPJ);

        // Adicionando seguros na seguradora
        seguradora.gerarSeguro(clientePF, seguroPF);
        seguradora.gerarSeguro(clientePJ, seguroPJ);
        
        //Instanciando um condutorPF
        
        Condutor condutorPF = new Condutor("03410795618", "Carlos", "22-999999999", "Rua Nobrega", "carlos@gmail.com", LocalDate.of(1985, 2, 20));
        seguroPF.adicionarCondutor(condutorPF);
        
      //Instanciando um condutorPJ
        
        Condutor condutorPJ = new Condutor("03510795619", "Carla", "22-999999900", "Rua Augusto", "carla@gmail.com", LocalDate.of(1989, 8, 20));
        seguroPJ.adicionarCondutor(condutorPJ);

        // Gerar sinistros 
        seguroPF.gerarSinistro(LocalDate.of(2020, 10, 12), "Rua Alberto Braune", condutorPF); 
        seguroPJ.gerarSinistro(LocalDate.of(2021, 10, 12), "Rua Mariano", condutorPJ); 
        
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        MenuOperacoes menuOperacao;
        int opcao;

        do {
            System.out.println("\n==== Menu Principal ====");
            for (MenuOperacoes menu : MenuOperacoes.values()) {
                if (menu.getCodigo() < 10) {
                    System.out.printf("%d. %s\n", menu.getCodigo(), menu.getDescricao());
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
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (menuOperacao != MenuOperacoes.SAIR);

        scanner.close(); 
    }
    
    public void tratarCadastro(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("Escolha uma opção:");
            for (MenuOperacoes menu : MenuOperacoes.values()) {
                if (menu.getCodigo() >= 10 && menu.getCodigo() < 20) {
                    System.out.printf("%d. %s\n", menu.getCodigo(), menu.getDescricao());
                }
            }
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            MenuOperacoes menuOperacao = MenuOperacoes.fromCodigo(opcao);

            switch (menuOperacao) {
                case CADASTRAR_CLIENTE:
                    cadastrarClientePF_PJ(scanner);
                    break;
                case CADASTRAR_VEICULO:
                    cadastrarVeiculo(scanner);
                    break;
                case CADASTRAR_SEGURADORA:
                    cadastrarSeguradora(scanner);
                    break;
                case CADASTRAR_SEGURO_PF:
                    cadastrarSeguroPF(scanner);
                    break;
                case CADASTRAR_SEGURO_PJ:
                    cadastrarSeguroPJ(scanner);
                    break;
                case CADASTRAR_FROTA:
                    cadastrarFrota(scanner);
                    break;
                case CADASTRAR_CONDUTOR:
                    cadastrarCondutor(scanner);
                    break;
                case VOLTAR_CADASTRAR:
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
        System.out.print("Informe o telefone: ");
        String telefone = scanner.next();
        scanner.nextLine();
        System.out.print("Informe o email: ");
        String email = scanner.nextLine();

        Cliente cliente;

        if (tipoCliente.equalsIgnoreCase("PF")) {
        	
            System.out.print("Informe o CPF: ");
            String cpf = scanner.next();

            if (!Validacao.validarCPF(cpf)) {
                System.out.println("CPF inválido!");
                return;
            }
            System.out.println("CPF válido!");
            
            System.out.print("Informe a data de nascimento (AAAA-MM-DD): ");
            String dataNascimento = scanner.next();
            
            LocalDate dataNasc;
            try {
                dataNasc = LocalDate.parse(dataNascimento);
            } catch (DateTimeParseException e) {
                System.out.println("Data de nascimento inválida. Tente novamente.");
                return;
            }
            System.out.print("Informe o nível de educação: ");
            String educacao = scanner.next();
            scanner.nextLine();
            System.out.print("Informe o gênero: ");
            String genero = scanner.next();

            cliente = new ClientePF(nomeCliente, enderecoCliente, telefone, email, cpf, genero, educacao, dataNasc);

        } else if (tipoCliente.equalsIgnoreCase("PJ")) {
            System.out.print("Informe o CNPJ: ");
            String cnpj = scanner.next();
            
            if (!Validacao.validarCNPJ(cnpj)) {
                System.out.println("CNPJ inválido!");
                return;
            }
            System.out.println("CNPJ válido!");
            System.out.print("Informe a data de fundação (AAAA-MM-DD): ");
            String dataFundacao = scanner.next();
            
            LocalDate dataFund;
            try {
                dataFund = LocalDate.parse(dataFundacao);
            } catch (DateTimeParseException e) {
                System.out.println("Data de fundação inválida. Tente novamente.");
                return;
            }
            System.out.print("Informe a quantidade de funcionários: ");
            int qtdeFuncionarios = scanner.nextInt();

            cliente = new ClientePJ(nomeCliente, enderecoCliente, telefone, email, cnpj, dataFund, qtdeFuncionarios);

        } else {
            System.out.println("Tipo de cliente inválido.");
            return;
        }
        
        seguradora.cadastrarCliente(cliente);
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso! O ID do cliente é: " + cliente.getId());

        }
        public void cadastrarVeiculo(Scanner scanner) {

        System.out.print("Informe o id do cliente: ");
        int idCliente = scanner.nextInt();

        Cliente cliente = seguradora.buscarCliente(idCliente);

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
            System.out.println("Cadastro da seguradora");
            System.out.println("Digite o CNPJ da seguradora: ");
            String cnpj = scanner.next();
            scanner.nextLine(); 

            System.out.println("Informe o nome da seguradora: ");
            String nome = scanner.nextLine();

            // Verifica se a seguradora já existe
            for (Seguradora seguradora : seguradoras) {
                if (seguradora.getNome().equalsIgnoreCase(nome)) {
                    System.out.println("Seguradora já cadastrada!");
                    return;
                }
            }

            System.out.println("Informe o telefone da seguradora: ");
            String telefone = scanner.next();

            System.out.println("Informe o email da seguradora: ");
            String email = scanner.next();
            scanner.nextLine();

            System.out.println("Informe o endereço da seguradora: ");
            String endereco = scanner.nextLine();

            Seguradora seguradora = new Seguradora(cnpj, nome, telefone, email, endereco);
            seguradoras.add(seguradora);  // Adiciona a nova seguradora à lista de seguradoras

            System.out.println("Seguradora cadastrada com sucesso!");
        }


        public void cadastrarSeguroPF(Scanner scanner) {
            System.out.print("Digite o ID do seguro: ");
            int id = scanner.nextInt();
            System.out.print("Digite a data de inicio (yyyy-mm-dd): ");
            LocalDate dataInicio = LocalDate.parse(scanner.next());
            System.out.print("Digite a data de fim (yyyy-mm-dd): ");
            LocalDate dataFim = LocalDate.parse(scanner.next());
            
            // Busca o cliente pelo ID
            System.out.println("Digite o ID do cliente: ");
            int clienteId = scanner.nextInt();
            ClientePF clientePF = null;
            for (Cliente c : clientes) {
                if (c.getId() == clienteId && c instanceof ClientePF) {
                    clientePF = (ClientePF) c;
                    break;
                }
            }
            if (clientePF == null) {
                System.out.println("ClientePF não encontrado!");
                return;
            }
            
         // Busca a seguradora pelo nome
            System.out.println("Digite o nome da seguradora: ");
            String seguradoraNome = scanner.next();
            Seguradora seguradora = null;
            for (Seguradora s : seguradoras) {
                if (s.getNome().equals(seguradoraNome)) {
                    seguradora = s;
                    break;
                }
            }
            if (seguradora == null) {
                System.out.println("Seguradora não encontrada!");
                return;
            }
            
         // Busca o veículo pela placa
            System.out.print("Digite a placa do veículo: ");
            String veiculoPlaca = scanner.next();
            Veiculo veiculo = null;
            for (Veiculo v : veiculos) {
                if (v.getPlaca().equals(veiculoPlaca)) {
                    veiculo = v;
                    break;
                }
            }
            
            if (veiculo == null) {
                System.out.println("Veículo não encontrado!");
                return;
            }

            SeguroPF seguroPF = new SeguroPF(id, dataInicio, dataFim, seguradora, veiculo, clientePF);
            seguros.add(seguroPF);
        }

        public void cadastrarSeguroPJ(Scanner scanner) {
            System.out.print("Digite o ID do seguro: ");
            int id = scanner.nextInt();
            System.out.print("Digite a data de inicio (aaaa-mm-dd): ");
            LocalDate dataInicio = LocalDate.parse(scanner.next());
            System.out.print("Digite a data de fim (aaaa-mm-dd): ");
            LocalDate dataFim = LocalDate.parse(scanner.next());
            
            // Busca o cliente pelo ID
            System.out.print("Digite o ID do cliente: ");
            int clienteId = scanner.nextInt();
            ClientePJ clientePJ = null;
            for (Cliente c : clientes) {
                if (c.getId() == clienteId && c instanceof ClientePJ) {
                    clientePJ = (ClientePJ) c;
                    break;
                }
            }
            if (clientePJ == null) {
                System.out.println("ClientePJ não encontrado!");
                return;
            }
            
            // Busca a seguradora pelo nome
            System.out.println("Digite o nome da seguradora: ");
            String seguradoraNome = scanner.next();
            Seguradora seguradora = null;
            for (Seguradora s : seguradoras) {
                if (s.getNome().equals(seguradoraNome)) {
                    seguradora = s;
                    break;
                }
            }
            if (seguradora == null) {
                System.out.println("Seguradora não encontrada!");
                return;
            }
            
         // Busca a frota pelo code
            System.out.print("Digite o code da frota: ");
            String codefrota = scanner.next();
            Frota frota = null;
            for (Frota f : frotas) {
                if (f.getCode().equals(codefrota)) {
                    frota = f;
                    break;
                }
            }
            
            if (frota == null) {
                System.out.println("Veículo não encontrado!");
                return;
            }

            SeguroPJ seguroPJ = new SeguroPJ(id, dataInicio, dataFim, seguradora, frota, clientePJ);
            seguros.add(seguroPJ);
        }

        public void cadastrarFrota(Scanner scanner) {
            System.out.print("Digite o código da frota: ");
            String code = scanner.next();

            Frota frota = new Frota(code);
            frotas.add(frota);
        }

        public void cadastrarCondutor(Scanner scanner) {
        	
            System.out.print("Digite o CPF do condutor: ");
            String cpf = scanner.next();
            if (Validacao.validarCPF(cpf)) {
                System.out.println("CPF válido!");
            } else {
                System.out.println("CPF inválido!");
            }
            System.out.print("Digite o nome do condutor: ");
            String nome = scanner.next();
            scanner.nextLine();
            System.out.print("Digite o telefone do condutor: ");
            String telefone = scanner.nextLine();
            System.out.print("Digite o endereço do condutor: ");
            String endereco = scanner.nextLine();
            System.out.print("Digite o e-mail do condutor: ");
            String email = scanner.next();
            scanner.nextLine();
            System.out.print("Digite a data de nascimento do condutor (yyyy-mm-dd): ");
            LocalDate dataNasc = LocalDate.parse(scanner.next());

            Condutor condutor = new Condutor(cpf, nome, telefone, endereco, email, dataNasc);
            condutores.add(condutor);
            
            System.out.print("Digite o ID do seguro ao qual o condutor será associado: ");
            int seguroId = scanner.nextInt();
            scanner.nextLine();
            
            seguradora.adicionarCondutorASeguro(seguroId, condutor);
        	    }

        public void tratarListagem(Scanner scanner) {
            boolean continuar = true;
            while (continuar) {
                System.out.println("Escolha uma opção:");
                // Gera dinamicamente as opções de menu
                for (MenuOperacoes op : MenuOperacoes.values()) {
                    if (op.getCodigo() >= 21 && op.getCodigo() <= 29) {
                        System.out.println(op.getCodigo() % 10 + " - " + op.getDescricao());
                    }
                }
                System.out.print("Opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consome o caractere de nova linha (enter)

                // Transforma a opção para corresponder ao código no enum
                opcao += 20;

                // Verifica a opção
                MenuOperacoes opcaoMenu = MenuOperacoes.fromCodigo(opcao);
                if (opcaoMenu == null) {
                    System.out.println("Opção inválida!");
                    continue;
                }

                switch (opcaoMenu) {
                case LISTAR_CLIENTE_PF:
                    seguradora.listarClientesPF();
                    break;
                case LISTAR_CLIENTE_PJ:
                    seguradora.listarClientesPJ();
                    break;
                case LISTAR_VEICULO_CLIENTE_PF:
                    seguradora.listarVeiculosClientesPF();
                    break;
                case LISTAR_FROTAS_CLIENTE_PJ:
                    seguradora.listarFrotasClientesPJ();
                    break;
                case LISTAR_VEICULO_SEGURADORA:
                    seguradora.listarVeiculosPorSeguradora();
                    break;
                case LISTAR_SEGUROS_PF:
                    seguradora.listarSegurosPF();
                    break;
                case LISTAR_SEGUROS_PJ:
                    seguradora.listarSegurosPJ();
                    break;
                case LISTAR_CONDUTORES:
                    seguradora.listarCondutores();
                    break;
                case VOLTAR_LISTAR:
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
               Arrays.stream(MenuOperacoes.values())
                 .filter(opcao -> opcao.ordinal() >= MenuOperacoes.EXCLUIR_CLIENTE.ordinal() && opcao.ordinal() <= MenuOperacoes.VOLTAR_EXCLUIR.ordinal())
                 .forEach(opcao -> System.out.println(opcao.getCodigo() + " - " + opcao.getDescricao()));
               System.out.print("Opção: ");
               int opcao = scanner.nextInt();
               scanner.nextLine(); // Consome o caractere de nova linha (enter)

                MenuOperacoes opcaoEscolhida = Arrays.stream(MenuOperacoes.values())
                    .filter(op -> op.getCodigo() == opcao)
                    .findFirst()
                    .orElse(MenuOperacoes.VOLTAR_EXCLUIR);

                switch (opcaoEscolhida) {
                    case EXCLUIR_CLIENTE:
                        System.out.print("Informe o id do cliente a ser removido: ");
                        int idCliente = scanner.nextInt();
                        scanner.nextLine();
                        if (seguradora.removerCliente(idCliente)) {
                            System.out.println("Cliente removido com sucesso!");
                        } else {
                            System.out.println("Cliente não encontrado!");
                        }
                        break;
                    case EXCLUIR_VEICULO:
                        System.out.print("Informe a placa do veículo a ser removido: ");
                        String placaVeiculo = scanner.nextLine();
                        if (seguradora.removerVeiculo(placaVeiculo)) {
                            System.out.println("Veículo removido com sucesso!");
                        } else {
                            System.out.println("Veículo não encontrado!");
                        }
                        break;
                    case EXCLUIR_SINISTRO:
                        System.out.print("Informe o id do sinistro a ser removido: ");
                        int idSinistro = scanner.nextInt();
                        if (seguradora.removerSinistro(idSinistro)) {
                            System.out.println("Sinistro removido com sucesso!");
                        } else {
                            System.out.println("Sinistro não encontrado!");
                        }
                        break;
                    case EXCLUIR_SEGURO_PF:
                        System.out.print("Informe o id do SeguroPF a ser removido: ");
                        int idSeguroPF = scanner.nextInt();
                        if (seguradora.removerSeguroPF(idSeguroPF)) {
                            System.out.println("SeguroPF removido com sucesso!");
                        } else {
                            System.out.println("SeguroPF não encontrado!");
                        }
                        break;
                    case EXCLUIR_SEGURO_PJ:
                        System.out.print("Informe o id do SeguroPJ a ser removido: ");
                        int idSeguroPJ = scanner.nextInt();
                        if (seguradora.removerSeguroPJ(idSeguroPJ)) {
                            System.out.println("SeguroPJ removido com sucesso!");
                        } else {
                            System.out.println("SeguroPJ não encontrado!");
                        }
                        break;
                    case EXCLUIR_FROTA:
                        System.out.print("Informe o id da frota a ser removida: ");
                        String codeFrota = scanner.nextLine();
                        if (seguradora.removerFrota(codeFrota)) {
                            System.out.println("Frota removida com sucesso!");
                        } else {
                            System.out.println("Frota não encontrada!");
                        }
                        break;
                    case EXCLUIR_CONDUTOR:
                        System.out.print("Informe o id do condutor a ser removido: ");
                        String cpfCondutor = scanner.nextLine();
                        if (seguradora.removerCondutor(cpfCondutor)) {
                            System.out.println("Condutor removido com sucesso!");
                        } else {
                            System.out.println("Condutor não encontrado!");
                        }
                        break;
                    case VOLTAR_EXCLUIR:
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
            System.out.println("Digite a opção desejada: ");
            System.out.println("1 - Criar sinistro");
            System.out.println("2 - Listar sinistros existentes");
            
            while(!scanner.hasNextInt()){
                System.out.println("Por favor, insira um número inteiro válido");
                scanner.next();
            }
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Informe o ID do cliente: ");
                    int idCliente = scanner.nextInt();
                    Cliente cliente = seguradora.buscarCliente(idCliente);

                    if (cliente == null) {
                        System.out.println("Cliente não encontrado!");
                        return;
                    }

                    System.out.print("Informe o CPF do condutor: ");
                    String cpfCondutor = scanner.next();
                    Condutor condutor = seguradora.buscarCondutor(cpfCondutor);

                    if (condutor == null) {
                        System.out.println("Condutor não encontrado!");
                        return;
                    }

                    System.out.print("Informe a data do sinistro (formato yyyy-mm-dd): ");
                    String dataStr = scanner.next();
                    LocalDate data = LocalDate.parse(dataStr);

                    System.out.print("Informe o endereço do sinistro: ");
                    String endereco = scanner.nextLine();

                    List<SeguroPF> segurosPFList = null;
                    List<SeguroPJ> segurosPJList = null;

                    if (cliente instanceof ClientePF) {
                        segurosPFList = seguradora.getSegurosPF((ClientePF) cliente);
                    } else if (cliente instanceof ClientePJ) {
                        segurosPJList = seguradora.getSegurosPJ((ClientePJ) cliente);
                    }

                    if ((segurosPFList == null || segurosPFList.isEmpty()) && (segurosPJList == null || segurosPJList.isEmpty())) {
                        System.out.println("Nenhum seguro associado a este cliente!");
                        return;
                    }

                    if (segurosPFList != null && !segurosPFList.isEmpty()) {
                        for (SeguroPF seguroPF : segurosPFList) {
                            seguroPF.gerarSinistro(data, endereco, condutor);
                        }
                    }

                    if (segurosPJList != null && !segurosPJList.isEmpty()) {
                        for (SeguroPJ seguroPJ : segurosPJList) {
                            seguroPJ.gerarSinistro(data, endereco, condutor);
                        }
                    }

                    System.out.println("Sinistros gerados com sucesso!");

                    break;               
                    
                case 2:
                    System.out.print("Informe o ID do cliente: ");
                    idCliente = scanner.nextInt();
                    cliente = seguradora.buscarCliente(idCliente);

                    if (cliente == null) {
                        System.out.println("Cliente não encontrado!");
                        return;
                    }

                    List<Sinistro> sinistros = seguradora.getSinistrosPorCliente(cliente);

                    if (sinistros.isEmpty()) {
                        System.out.println("Nenhum sinistro encontrado para este cliente!");
                        return;
                    }

                    System.out.println("Sinistros:");
                    for (Sinistro sinistro : sinistros) {
                        System.out.println("ID: " + sinistro.getId() + " Endereço: " + sinistro.getEndereco() + " Data: " + sinistro.getData());
                    }

                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }


        public void tratarCalcularReceita(Scanner scanner) {
            System.out.println("A receita total da seguradora é: " + seguradora.calcularReceita());
        }
    }


