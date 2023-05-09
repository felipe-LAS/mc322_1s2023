package Laboratorio04;

import java.util.ArrayList;
import java.util.List;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private List<Sinistro> listaSinistros;
    private List<Cliente> listaClientes;

    public Seguradora(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSinistros = new ArrayList<>();
        this.listaClientes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public void setListaSinistros(List<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public boolean cadastrarCliente(Cliente cliente) {
        // Verifica se o cliente já está cadastrado na lista de clientes
        if (!listaClientes.contains(cliente)) {
            // Adiciona o cliente na lista de clientes
            listaClientes.add(cliente);
            return true;
        }
        return false;
    }

    public Cliente buscarClientePorNome(String nome) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                return cliente;
            }
        }
        return null;
    }

    public boolean removerCliente(String nome) {
        // Percorre a lista de clientes
        for (Cliente cliente : listaClientes) {
            // Verifica se o nome do cliente é igual ao nome passado como parâmetro
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                // Remove o cliente da lista de clientes
                listaClientes.remove(cliente);
                return true;
            }
        }
        return false;
    }

    public void listarClientes(String tipoCliente) {
        // Percorre a lista de clientes
        for (Cliente cliente : listaClientes) {
            // Verifica se o tipo de cliente passado como parâmetro é igual ao tipo do cliente na lista de clientes
            if (tipoCliente.equalsIgnoreCase("PF") && cliente instanceof ClientePF) {
                // Imprime o cliente
                System.out.println(cliente);
            } else if (tipoCliente.equalsIgnoreCase("PJ") && cliente instanceof ClientePJ) {
                // Imprime o cliente
                System.out.println(cliente);
            }
        }
    }

    public boolean gerarSinistro(Sinistro sinistro) {
        // Verifica se o sinistro não é nulo
        if (sinistro != null) {
            // Verifica se o cliente do sinistro está cadastrado na lista de clientes da seguradora
            if (listaClientes.contains(sinistro.getCliente()) && listaClientes.get(listaClientes.indexOf(sinistro.getCliente())).getListaVeiculos().contains(sinistro.getVeiculo())) {
                // Adiciona o sinistro na lista de sinistros
                listaSinistros.add(sinistro);
                return true;
            }
        }
        return false;
    }

    public boolean visualizarSinistro(String nomeCliente) {
        // Percorre a lista de sinistros
        for (Sinistro sinistro : listaSinistros) {
            // Verifica se o nome do cliente do sinistro é igual ao nome passado como parâmetro
            if (sinistro.getCliente().getNome().equalsIgnoreCase(nomeCliente)) {
                // Imprime o sinistro
                System.out.println(sinistro);
                return true;
            }
        }
        return false;
    }

    public void listarSinistros() {
        // Percorre a lista de sinistros
        for (Sinistro sinistro : listaSinistros) {
            // Imprime o sinistro
            System.out.println("------------");
            System.out.println(sinistro);
        }
    }

    public void listarSinistrosPorSeguradora() {
        System.out.println("Sinistros por seguradora:");
        for (Sinistro sinistro : listaSinistros) {
            System.out.println(sinistro.toString());
        }
    }

    public void listarSinistrosPorCliente(String nomeCliente) {
        System.out.println("Sinistros por cliente:");
        for (Sinistro sinistro : listaSinistros) {
            if (sinistro.getCliente().getNome().equalsIgnoreCase(nomeCliente)) {
                System.out.println(sinistro.toString());
            }
        }
    }

    public void listarVeiculosPorCliente(String nomeCliente) {
        System.out.println("Veículos por cliente:");
        for (Cliente cliente : listaClientes) {
            if (cliente.getNome().equalsIgnoreCase(nomeCliente)) {
                for (Veiculo veiculo : cliente.getListaVeiculos()) {
                    System.out.println(veiculo.toString());
                }
            }
        }
    }

    public void listarVeiculosPorSeguradora() {
        System.out.println("Veículos por seguradora:");
        for (Cliente cliente : listaClientes) {
            for (Veiculo veiculo : cliente.getListaVeiculos()) {
                System.out.println(veiculo.toString());
            }
        }
    }

    public boolean removerVeiculo(String nomeCliente, String placaVeiculo) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getNome().equalsIgnoreCase(nomeCliente)) {
                List<Veiculo> veiculos = cliente.getListaVeiculos();
                for (Veiculo veiculo : veiculos) {
                    if (veiculo.getPlaca().equalsIgnoreCase(placaVeiculo)) {
                        veiculos.remove(veiculo);
                        return true;
                    }
                }
                return false; // Veículo não encontrado
            }
        }
        return false; // Cliente não encontrado
    }

    public boolean removerSinistro(int sinistroId) {
        for (Sinistro sinistro : listaSinistros) {
            if (sinistro.getId() == sinistroId) {
                listaSinistros.remove(sinistro);
                return true;
            }
        }
        return false; // Sinistro não encontrado
    }

    private double calculaScore(Cliente cliente) {
        double score = 0.0;
        double VALOR_BASE = 1000.0;

        if (cliente instanceof ClientePF) {
            ClientePF clientePF = (ClientePF) cliente;
            double FATOR_IDADE = 1.0; 
            int quantidadeCarros = clientePF.getListaVeiculos().size();
            score = VALOR_BASE * FATOR_IDADE * quantidadeCarros;
        } else if (cliente instanceof ClientePJ) {
            ClientePJ clientePJ = (ClientePJ) cliente;
            int quantidadeFunc = clientePJ.getQtdeFuncionarios();
            int quantidadeCarros = clientePJ.getListaVeiculos().size();
            score = VALOR_BASE * (1 + (double) quantidadeFunc / 100) * quantidadeCarros;
        }

        return score;
    }

    public double calcularPrecoSeguroCliente(Cliente cliente) {
        double quantidade_de_sinistros = 0;

        for (Sinistro sinistro : listaSinistros) {
            if (sinistro.getCliente().equals(cliente)) {
                quantidade_de_sinistros++;
            }
        }

        return calculaScore(cliente) * (1 + quantidade_de_sinistros);
    }

    public double calcularReceita() {
        double receitaTotal = 0.0;

        for (Cliente cliente : listaClientes) {
            receitaTotal += calcularPrecoSeguroCliente(cliente);
        }

        return receitaTotal;
    }

    // Método para transferir seguro de um cliente para outro
    public boolean transferirSeguro(Cliente clienteOrigem, Cliente clienteDestino) {
        if (listaClientes.contains(clienteOrigem) && listaClientes.contains(clienteDestino)) {
            // Transfere a lista de veículos segurados
            clienteDestino.setListaVeiculos(clienteOrigem.getListaVeiculos());

            // Atualiza o valor do seguro do cliente
            double novoValorSeguro = calcularPrecoSeguroCliente(clienteDestino);
            clienteDestino.setValorSeguro(novoValorSeguro);

            // Limpa a lista de veículos segurados do cliente origem
            clienteOrigem.setListaVeiculos(new ArrayList<>());

            // Atualiza o valor do seguro do cliente origem
            clienteOrigem.setValorSeguro(0.0);

            return true;
        }
        return false;
    }
    public String toString() {
        return "\nNome: " + this.getNome() +
                "\nTelefone: " + this.getTelefone() +
        		"\nEmail: " + this.getEmail() +
        		"\nEndereco: " + this.getEndereco();
    }
}
