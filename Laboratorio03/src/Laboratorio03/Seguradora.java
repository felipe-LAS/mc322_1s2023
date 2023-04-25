package Laboratorio03;

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

    public String toString() {
        return "\nNome: " + this.getNome() +
                "\nTelefone: " + this.getTelefone() +
        		"\nEmail: " + this.getEmail() +
        		"\nEndereco: " + this.getEndereco();
    }
}
