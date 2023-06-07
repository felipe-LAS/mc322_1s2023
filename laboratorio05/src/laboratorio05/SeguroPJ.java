package laboratorio05;

import java.time.LocalDate;

public class SeguroPJ extends Seguro {
    private Frota frota;
    private ClientePJ cliente;

    private static final double VALOR_BASE = 10.0;

    public SeguroPJ(int id, LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, Frota frota, ClientePJ cliente) {
        super(id, dataInicio, dataFim, seguradora, cliente);
        this.frota = frota;
        this.cliente = cliente;
    }
    
    public void inicializar() {
        this.setValorMensal(this.calcularValor());
    }

    public Frota getFrota() {
        return frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    public ClientePJ getCliente() {
        return cliente;
    }

    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
    }

    @Override
    public void autorizarCondutor(Condutor condutor) {
        this.getListaCondutores().add(condutor);
    }

    @Override
    public void desautorizarCondutor(Condutor condutor) {
        this.getListaCondutores().remove(condutor);
    }

    @Override
    public double calcularValor() {
        int quantidadeVeiculos = frota.getListaVeiculos().size();
        int quantidadeFunc = cliente.getQuantidadeFuncionarios();
        int anosPosFundacao = LocalDate.now().getYear() - cliente.getDataFundacao().getYear();
        int quantidadeSinistrosCliente = cliente.getSinistros().size();

        int quantidadeSinistrosCondutor = 0;
        for (Condutor condutor : this.getListaCondutores()) {
            quantidadeSinistrosCondutor += condutor.getListaSinistros().size();
        }

        double valorMensal = VALOR_BASE * (10 + (double) quantidadeFunc / 10)
                * (1 + 1 / (double) (quantidadeVeiculos + 2))
                * (1 + 1 / (double) (anosPosFundacao + 2))
                * (2 + (double) quantidadeSinistrosCliente / 10)
                * (5 + (double) quantidadeSinistrosCondutor / 10);

        this.setValorMensal(valorMensal);
        return valorMensal;
    }

    @Override
    public void gerarSinistro(LocalDate data, String endereco, Condutor condutor) {
        Sinistro sinistro = new Sinistro(this.getListaSinistros().size() + 1, data, endereco, condutor, this, this.getCliente());
        this.getListaSinistros().add(sinistro);
        condutor.getListaSinistros().add(sinistro);
        this.getCliente().getSinistros().add(sinistro);
    }
    
    @Override
    public String toString() {
        return "SeguroPJ {" +
                "id=" + getId() +
                ", data de início=" + getDataInicio() +
                ", data de término=" + getDataFim() +
                ", valor mensal=" + getValorMensal() +
                ", cliente=" + getCliente() +
                ", frota=" + getFrota() +
                '}';
    }
}
