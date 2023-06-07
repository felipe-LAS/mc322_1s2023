package laboratorio05;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frota {
	private String code;
    private List<Veiculo> listaVeiculos;

    public Frota(String code) {
        this.code = code;
        this.listaVeiculos = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public boolean addVeiculo(Veiculo veiculo) {
        if (!this.listaVeiculos.contains(veiculo)) {
            return this.listaVeiculos.add(veiculo);
        }
        return false;
    }

    public boolean removeVeiculo(Veiculo veiculo) {
        if (this.listaVeiculos.contains(veiculo)) {
            return this.listaVeiculos.remove(veiculo);
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Frota {" +
                "código=" + code +
                ", veículos=" + listaVeiculos.stream().map(Veiculo::toString).collect(Collectors.joining(", ")) +
                "}";
    }
}