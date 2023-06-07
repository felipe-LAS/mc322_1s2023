package laboratorio05;

import java.time.LocalDate;

public class SeguroPF extends Seguro {
    private Veiculo veiculo;
    private ClientePF cliente;

    private static final double VALOR_BASE = 10.0;
    private static final double FATOR_IDADE_MENOS30 = 1.25;
    private static final double FATOR_IDADE_30_TO_60 = 1.0;
    private static final double FATOR_IDADE_MAIS60 = 1.5;

    public SeguroPF(int id, LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, Veiculo veiculo, ClientePF cliente) {
        super(id, dataInicio, dataFim, seguradora, cliente);
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    public void inicializar() {
        this.setValorMensal(this.calcularValor());
    }
    
	public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public ClientePF getCliente() {
        return cliente;
    }

    public void setCliente(ClientePF cliente) {
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
    	if (cliente == null) {
            throw new NullPointerException("cliente is null");
        }
        int quantidadeVeiculos = cliente.getListaVeiculos().size();
        int quantidadeSinistrosCliente = cliente.getSinistros().size();
        int idadeCliente = LocalDate.now().getYear() - cliente.getDataNasc().getYear();
        
        double fatorIdade = FATOR_IDADE_30_TO_60; 
        if (idadeCliente < 30) {
            fatorIdade = FATOR_IDADE_MENOS30;
        } else if (idadeCliente > 60) {
            fatorIdade = FATOR_IDADE_MAIS60;
        }

        int quantidadeSinistrosCondutor = 0;
        for (Condutor condutor : this.getListaCondutores()) {
            quantidadeSinistrosCondutor += condutor.getListaSinistros().size();
        }

        double valorMensal = VALOR_BASE * fatorIdade * (1 + 1 / (double) (quantidadeVeiculos + 2))
                * (2 + quantidadeSinistrosCliente / 10.0)
                * (5 + quantidadeSinistrosCondutor / 10.0);

        return valorMensal;
    }
    
	@Override
	public void gerarSinistro(LocalDate data, String endereco, Condutor condutor) {
	    Sinistro sinistro = new Sinistro(this.getListaSinistros().size() + 1, data, endereco, condutor, this, this.getCliente());
	    this.getListaSinistros().add(sinistro);
	    condutor.getListaSinistros().add(sinistro);
	    this.getCliente().getSinistros().add(sinistro); // adiciona sinistro à lista do cliente
	}
	
	@Override
	public String toString() {
	    return "SeguroPF {" +
	            "id=" + getId() +
	            ", data de início=" + getDataInicio() +
	            ", data de término=" + getDataFim() +
	            ", valor mensal=" + getValorMensal() +
	            ", cliente=" + getCliente() +
	            ", veículo=" + getVeiculo() +
	            '}';
	}

}
