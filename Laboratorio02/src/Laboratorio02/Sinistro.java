package Laboratorio02;
import java.util.Random;

public class Sinistro {
	    private int id;
	    private String data;
	    private String endereco;

	    public Sinistro(String data, String endereco) {
	        this.id = gerarIdUnico();
	        this.data = data;
	        this.endereco = endereco;
	    }

	    public int getId() {
	        return id;
	    }

	    public String getData() {
	        return data;
	    }

	    public void setData(String data) {
	        this.data = data;
	    }

	    public String getEndereco() {
	        return endereco;
	    }

	    public void setEndereco(String endereco) {
	        this.endereco = endereco;
	    }

	    private int gerarIdUnico() {
	    	Random random = new Random();
	        return random.nextInt(Integer.MAX_VALUE);
	    }
	}


    

