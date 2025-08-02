package models;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import services.Banco;

public class Conta {

	private String titular;
	private int id;
	private float saldo;
	
	private static Set<Integer> numerosUsados = new HashSet<>();

    private int gerarNumeroUnico() {
        Random r = new Random();
        int x;

        do {
            x = r.nextInt(900) + 100;
        } while (numerosUsados.contains(x));

        numerosUsados.add(x);
        return x;
    }
	
	//Contrutor	
	public Conta(String nome) {
		this.titular = nome;
		this.id = gerarNumeroUnico();
		this.saldo = 0.f;
	}
	
	//getters e setters
	
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public int getNumConta() {
		return id;
	}
	public void setNumConta(int numConta) {
		this.id = numConta;
	}
	public float getSaldo() {
		return saldo;
	}
	public void adicionarSaldo(float saldo) {
		this.saldo += saldo;
	}
	
	public void setSaque(int valSaque){
		if((int) valSaque>this.getSaldo()) {
			Banco.saldoInsuficiente();
		}else {
			this.saldo -= valSaque;	
		}
		
	}
	
	public void transferencia(float transferencia) {
		if(transferencia>this.getSaldo()) {
			Banco.saldoInsuficiente();
		}else {
			this.saldo -= transferencia;
		}
	}
	
	public String status() {
		String status ="Segue abaixo, os dados da sua conta:\nTitular da conta: "+ getTitular() + "\nID: "+ getNumConta() +"\nSaldo R$: " + getSaldo();
		return status;
	}
}
