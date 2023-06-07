package laboratorio05;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Condutor {
    private final String cpf;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private LocalDate dataNasc;
    private List<Sinistro> listaSinistros;

    public Condutor(String cpf, String nome, String telefone, String endereco, String email, LocalDate dataNasc) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dataNasc = dataNasc;
        this.listaSinistros = new ArrayList<>();
    }

    public String getCpf() {
        return cpf;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public List<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public void adicionarSinistro(Sinistro sinistro) {
        this.listaSinistros.add(sinistro);
    }

@Override
public String toString() {
    return "Condutor{" +
            "cpf='" + cpf + '\'' +
            ", nome='" + nome + '\'' +
            ", telefone='" + telefone + '\'' +
            ", endereco='" + endereco + '\'' +
            ", email='" + email + '\'' +
            ", dataNasc=" + dataNasc +
            ", listaSinistros=" + listaSinistros +
            '}';
}
}

