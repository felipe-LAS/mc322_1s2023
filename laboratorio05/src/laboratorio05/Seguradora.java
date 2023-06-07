package laboratorio05;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Seguradora {
    private final String cnpj;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private List<Cliente> listaClientes;
    private List<Seguro> listaSeguros;
    private List<SeguroPF> listaSegurosPF;
    private List<SeguroPJ> listaSegurosPJ;
    private List<Veiculo> veiculos;
    private List<Sinistro> sinistros;
    private List<Frota> frotas;
    private List<SeguroPF> segurosPF;
    private List<SeguroPJ> segurosPJ;
    private List<Condutor> condutores;


    public Seguradora(String cnpj, String nome, String telefone, String endereco, String email) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.listaClientes = new ArrayList<>();
        this.listaSeguros = new ArrayList<>();
        this.veiculos = new ArrayList<>();
        this.sinistros = new ArrayList<>();
        this.frotas = new ArrayList<>();
        this.segurosPF = new ArrayList<>();
        this.segurosPJ = new ArrayList<>();
        this.condutores = new ArrayList<>();
    }

    public String getCnpj() {
        return cnpj;
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

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public List<Seguro> getListaSeguros() {
        return listaSeguros;
    }
    
    public List<SeguroPF> getSegurosPF(Cliente cliente) {
        return segurosPF.stream().filter(seguro -> seguro.getCliente().equals(cliente)).collect(Collectors.toList());
    }

    public List<SeguroPJ> getSegurosPJ(Cliente cliente) {
        return segurosPJ.stream().filter(seguro -> seguro.getCliente().equals(cliente)).collect(Collectors.toList());
    }

    public boolean gerarSeguro(Cliente cliente, Seguro seguro) {
        if (!listaClientes.contains(cliente)) {
            return false;
        }
        listaSeguros.add(seguro);
        return true;
    }

    public boolean cancelarSeguro(Seguro seguro) {
    	return listaSeguros.remove(seguro);
        }

    public boolean cadastrarCliente(Cliente cliente) {
        if (listaClientes.contains(cliente)) {
            return false;
        }
        listaClientes.add(cliente);
        return true;
    }

    public List<Seguro> getSegurosPorCliente(Cliente cliente) {
        return listaSeguros.stream()
                .filter(seguro -> seguro.getCliente().equals(cliente))
                .collect(Collectors.toList());
    }

    public List<Sinistro> getSinistrosPorCliente(Cliente cliente) {
        List<Sinistro> sinistros = new ArrayList<>();
        for (Seguro seguro : getSegurosPorCliente(cliente)) {
            sinistros.addAll(seguro.getListaSinistros());
        }
        return sinistros;
    }

    public double calcularReceita() {
        return listaSeguros.stream()
                .mapToDouble(Seguro::getValorMensal)
                .sum();
    }
    
    public void listarClientesPF() {
        listaClientes.stream()
            .filter(cliente -> cliente instanceof ClientePF)
            .forEach(System.out::println);
    }

    public void listarClientesPJ() {
        listaClientes.stream()
            .filter(cliente -> cliente instanceof ClientePJ)
            .forEach(System.out::println);
    }
    
    public void listarSeguros() {
        for (Seguro seguro : this.listaSeguros) {
            System.out.println(seguro.toString());
        }
    }
    
    public Cliente buscarCliente(int id) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }
    
    public void listarVeiculosClientesPF() {
        listaSeguros.stream()
            .filter(seguro -> seguro instanceof SeguroPF)
            .map(seguro -> ((SeguroPF) seguro).getVeiculo())
            .forEach(System.out::println);
    }


    public void listarFrotasClientesPJ() {
        listaSeguros.stream()
            .filter(seguro -> seguro instanceof SeguroPJ)
            .map(seguro -> ((SeguroPJ) seguro).getFrota())
            .forEach(System.out::println);
    }


    public void listarCondutores() {
        System.out.println("Listando seguros: ");
        for (Seguro seguro : listaSeguros) {
            System.out.println(seguro);
            List<Condutor> condutores = seguro.getListaCondutores();
            if (condutores.isEmpty()) {
                System.out.println("Sem condutores para este seguro.");
            } else {
                System.out.println("Condutores para o seguro:");
                for (Condutor condutor : condutores) {
                    System.out.println(condutor);
                }
            }
        }
    }


    public void listarVeiculosPorSeguradora() {
        List<Veiculo> veiculos = new ArrayList<>();

        listaSeguros.forEach(seguro -> {
            if (seguro instanceof SeguroPF) {
                veiculos.add(((SeguroPF) seguro).getVeiculo());
            } else if (seguro instanceof SeguroPJ) {
                veiculos.addAll(((SeguroPJ) seguro).getFrota().getListaVeiculos());
            }
        });

        veiculos.forEach(System.out::println);
    }
    
    public Condutor buscarCondutor(String cpf) {
        for (Condutor condutor : this.condutores) {
            if (condutor.getCpf().equals(cpf)) {
                return condutor;
            }
        }
        return null; 
    }
    
    public void adicionarCondutorASeguro(int idSeguro, Condutor condutor) {
        for (Seguro seguro : this.listaSeguros) {
            if (seguro.getId() == idSeguro) {
                seguro.adicionarCondutor(condutor);
                break;
            }
        }
    }

    public void listarSegurosPF() {
        listaSeguros.stream()
            .filter(seguro -> seguro instanceof SeguroPF)
            .forEach(System.out::println);
    }

    public void listarSegurosPJ() {
        listaSeguros.stream()
            .filter(seguro -> seguro instanceof SeguroPJ)
            .forEach(System.out::println);
    }
    
    public boolean removerCliente(int id) {
        return listaClientes.removeIf(cliente -> cliente.getId() == id);
    }

    public boolean removerVeiculo(String placa) {
        return veiculos.removeIf(veiculo -> veiculo.getPlaca().equalsIgnoreCase(placa));
    }

    public boolean removerSinistro(int id) {
        return sinistros.removeIf(sinistro -> sinistro.getId() == id);
    }
    
    public boolean removerSeguro(int id) {
        return listaSeguros.removeIf(seguro -> seguro.getId() == id);
    }
    
    public boolean removerSeguroPF(int id) {
        for (SeguroPF seguro : listaSegurosPF) {
            if (seguro.getId() == id) {
                return listaSegurosPF.remove(seguro);
            }
        }
        return false; // Retorna false se o seguro não for encontrado
    }

    public boolean removerSeguroPJ(int id) {
        for (SeguroPJ seguro : listaSegurosPJ) {
            if (seguro.getId() == id) {
                return listaSegurosPJ.remove(seguro);
            }
        }
        return false; // Retorna false se o seguro não for encontrado
    }
    
    public boolean removerFrota(String code) {
        return frotas.removeIf(frota -> frota.getCode().equals(code));
    }

    public boolean removerCondutor(String cpf) {
        return condutores.removeIf(condutor -> condutor.getCpf().equals(cpf));
    }
    
    
    @Override
    public String toString() {
        String listaSegurosStr = listaSeguros.stream()
                .map(Seguro::toString)
                .collect(Collectors.joining(", "));
        return "Seguradora{" +
                "cnpj='" + cnpj + '\'' +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", email='" + email + '\'' +
                ", seguros=[" + listaSegurosStr + "]" +
                '}';
    }
}

