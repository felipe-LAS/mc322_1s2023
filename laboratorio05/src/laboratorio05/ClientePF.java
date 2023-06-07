package laboratorio05;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class ClientePF extends Cliente {
    private final String cpf;
    private String genero;
    private String educacao;
    private LocalDate dataNasc;
    private List<Veiculo> listaVeiculos;

    public ClientePF(String nome, String endereco, String telefone, String email, String cpf, String genero, String educacao, LocalDate dataNasc) {
        super(nome, endereco, telefone, email);
        this.cpf = cpf;
        this.genero = genero;
        this.educacao = educacao;
        this.dataNasc = dataNasc;
        this.listaVeiculos = new ArrayList<>();
    }

    public String getCpf() {
        return cpf;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEducacao() {
        return educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public boolean cadastrarVeiculo(Veiculo veiculo) {
        return this.listaVeiculos.add(veiculo);
    }

    public boolean removerVeiculo(Veiculo veiculo) {
        return this.listaVeiculos.remove(veiculo);
    }

    @Override
    public String toString() {
        return 
                super.toString() +
                ", \ncpf: " + cpf +
                ", \ngenero: " + genero +
                ", \neducacao: " + educacao +
                ", \ndataNasc: " + dataNasc +
                ", \nlistaVeiculos: " + listaVeiculos +
                '}';
    }
}

