package laboratorio05;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientePJ extends Cliente {
    private final String cnpj;
    private LocalDate dataFundacao;
    private List<Frota> listaFrota;
    private int quantidadeFuncionarios;

    public ClientePJ(String nome, String endereco, String telefone, String email, String cnpj, LocalDate dataFundacao, int quantidadeFuncionarios) {
        super(nome, endereco, telefone, email);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.quantidadeFuncionarios = quantidadeFuncionarios;
        this.listaFrota = new ArrayList<>();
    }

    public String getCnpj() {
        return cnpj;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public List<Frota> getListaFrota() {
        return listaFrota;
    }
    
    public int getQuantidadeFuncionarios() {
        return quantidadeFuncionarios;
    }

    public void setQuantidadeFuncionarios(int quantidadeFuncionarios) {
        this.quantidadeFuncionarios = quantidadeFuncionarios;
    }

    public boolean cadastrarFrota(Frota frota) {
        if (!this.listaFrota.contains(frota)) {
            return this.listaFrota.add(frota);
        }
        return false;
    }

    public boolean removerFrota(Frota frota) {
        if (this.listaFrota.contains(frota)) {
            return this.listaFrota.remove(frota);
        }
        return false;
    }

    public List<Veiculo> getVeiculosPorFrota(String frotaCode) {
        for (Frota frota : this.listaFrota) {
            if (frota.getCode().equalsIgnoreCase(frotaCode)) {
                return frota.getListaVeiculos();
            }
        }
        return null; // Retorna null se a frota não for encontrada
    }
    
    public void adicionarFrotas(List<Frota> frotas) {
        for (Frota frota : frotas) {
            this.cadastrarFrota(frota);
        }
    }

    public void adicionarVeiculoAFrota(Frota frota, Veiculo veiculo) {
        if (this.listaFrota.contains(frota)) {
            frota.addVeiculo(veiculo);
        }
    }

    public void removerVeiculoDaFrota(Frota frota, Veiculo veiculo) {
        if (this.listaFrota.contains(frota)) {
            frota.removeVeiculo(veiculo);
        }
    }
    
    @Override
    public String toString() {
        return super.toString() +
            ", cnpj=" + cnpj +
            ", dataFundacao=" + dataFundacao +
            ", listaFrota=" + listaFrota +
            ", quantidadeFuncionarios=" + quantidadeFuncionarios +
        '}';
    }

}

