package Laboratorio04;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    private String nome;
    private String endereco;
    private List<Veiculo> listaVeiculos;
    private double valorSeguro;
    private List<Sinistro> sinistros;

    public Cliente(String nome, String endereco, List<Veiculo> listaVeiculos) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = listaVeiculos;
        this.sinistros = new ArrayList<>();
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

    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
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
    
    public abstract double calculaScore();
    
    public Veiculo buscarVeiculo(String placa) {
        List<Veiculo> listaVeiculos = this.getListaVeiculos();
        for (Veiculo veiculo : listaVeiculos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                return veiculo;
            }
        }
        return null; // Retorna null caso o veículo não seja encontrado
    }
    @Override
    
    public String toString() {
        return 
                "nome: " + nome  +
                ", \nendereco: " + endereco +
                ", \nlistaVeiculos: " + listaVeiculos +
                '}';
    }
    
}
