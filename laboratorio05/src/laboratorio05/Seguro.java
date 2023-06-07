package laboratorio05;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Seguro {
    private final int id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Seguradora seguradora;
    private List<Sinistro> listaSinistros;
    private List<Condutor> listaCondutores;
    private double valorMensal;
    private Cliente cliente;

    public Seguro(int id, LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, Cliente cliente) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        this.listaSinistros = new ArrayList<>();
        this.listaCondutores = new ArrayList<>();
        this.valorMensal = 0.0; //Inicializado como 0
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }
    
    public Cliente getCliente() {
    	return this.cliente;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public List<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public List<Condutor> getListaCondutores() {
        return listaCondutores;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setListaSinistros(List<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }
    
    protected void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }
    
    public void adicionarCondutor(Condutor condutor) {
        this.listaCondutores.add(condutor);
    }

    public abstract void desautorizarCondutor(Condutor condutor);

    public abstract void autorizarCondutor(Condutor condutor);

    public abstract double calcularValor();

    public abstract void gerarSinistro(LocalDate data, String endereco, Condutor condutor);
    
    }

