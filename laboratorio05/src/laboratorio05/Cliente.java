package laboratorio05;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
	protected static int nextId = 1; // Campo estático que gera um novo ID para cada cliente
	protected int id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private List<Veiculo> listaVeiculos;
    private double valorSeguro;
    private List<Sinistro> sinistros;
    private List<Seguro> seguros;

    public Cliente(String nome, String endereco, String telefone, String email) {
    	this.id = nextId++; // Atribui um ID único para cada cliente
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.listaVeiculos = new ArrayList<>();
        this.sinistros = new ArrayList<>();
        this.seguros = new ArrayList<>();
    }
    
    public int getId() {
        return this.id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }
    
    public List<Seguro> getSeguros() {
        return seguros;
    }

    public void setSeguros(List<Seguro> seguros) {
        this.seguros = seguros;
    }
    
    public double getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }
    
    public void adicionarSinistro(Sinistro sinistro) {
        this.sinistros.add(sinistro);
    }

    public List<Sinistro> getSinistros() {
        return sinistros;
    }
    
    
    public Veiculo buscarVeiculo(String placa) {
        List<Veiculo> listaVeiculos = this.getListaVeiculos();
        for (Veiculo veiculo : listaVeiculos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                return veiculo;
            }
        }
        return null; // Retorna null caso o veículo não seja encontrado
    }
    
    public List<Condutor> getCondutores() {
        List<Condutor> condutores = new ArrayList<>();
        for (Seguro seguro : this.seguros) {
            condutores.addAll(seguro.getListaCondutores());
        }
        return condutores;
    }
    
    @Override
    
    public String toString() {
        return 
        		"nome: " + nome  +
                ", \nendereco: " + endereco +
                ", \ntelefone: " + telefone +
                ", \nemail: " + email +
                ", \nlistaVeiculos: " + listaVeiculos +
                '}';
    }
}
