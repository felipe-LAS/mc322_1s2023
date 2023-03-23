package Laboratorio02;

public class Main {

    public static void main(String[] args) {
   
                 
    Cliente cliente = new Cliente("Felipe Santos", "16695443700", "02/10/2002", 20,  "Rua RM, 123");
    if (cliente.validarCPF()) {
    System.out.println("CPF válido!");
     } else {
    System.out.println("CPF inválido!");
    return;
    }
    System.out.println(cliente.toString());

    Seguradora seguradora = new Seguradora("Seguradora autocar", "2522-2129","seguradoraautocar@gmail.com", "Rua GO, 426");
    System.out.println("\nDados da seguradora:");
    System.out.println(seguradora.getNome() + ", telefone: " + seguradora.getTelefone() + ", email: " + seguradora.getEmail() + ", endereço: " + seguradora.getEndereco());

    Sinistro sinistro = new Sinistro("10/01/2023", "Rua Dr JP, 256");
    System.out.println("\nDados do sinistro:");
    System.out.println("ID: " + sinistro.getId() + ", data: " + sinistro.getData() + ", endereço: " + sinistro.getEndereco());

    Veiculo veiculo = new Veiculo("BRS1987", "Mclaren", "Artura");
    System.out.println("\nDados do veículo:");
    System.out.println(veiculo.getPlaca() + ", " + veiculo.getMarca() + ", " + veiculo.getModelo());
               }
           }
       