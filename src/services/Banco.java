package services;

import java.util.ArrayList;
import java.util.Scanner;
import models.Conta;

public class Banco {

	static Scanner sc = new Scanner(System.in);
	static ArrayList<Conta> contas = new ArrayList<>();
	
	public static void saldoInsuficiente() {
		System.out.println("Saldo insuficiente.");
	}
	
	public static void solicitarValorPositivo(){
		System.out.println("Não é possível depositar este valor\nDigite um valor positivo.");
	}

	
	public static int conferirID(int id) { 
		int i = 0;
		
		for(i = 0; i<contas.size();i++) {
			if(contas.get(i).getNumConta() == id) {
				return i;
			}
		}
		System.out.printf("Conta não encontrada\nO ID:%d não pertence a nenhuma conta.\n\n", id);
		return - 1;
	}
	
	public static void criar() {
		String nome;
		
		do {
		    System.out.println("Para começar, insira seu nome: ");
		    nome = sc.nextLine().trim();

		    if (nome.isEmpty() || !nome.matches(".*[a-zA-Zà-úÀ-Ú]+.*")) {
		        System.out.println("Nome inválido. Digite um nome que contenha letras.");
		    }
		} while (nome.isEmpty() || !nome.matches(".*[a-zA-Zà-úÀ-Ú]+.*"));
		
		System.out.println("\nAgora vamos criar um ID aleatorio para sua conta.");
		contas.add(new Conta(nome));
		System.out.println("Conta criada com sucesso");
		System.out.println(contas.get(contas.size() - 1).status());
	}
	
	public static void depositar() {
		int id = 0;
		float valor = 0.f;
		boolean entradaInvalida = false;
		System.out.println("Qual o ID da sua conta?");
		id = sc.nextInt();
		sc.nextLine();
		int i = conferirID(id);
		
		if (i == -1) 
			return;
		
		do {
	        try {
	            System.out.println("Qual o valor que você deseja depositar?");
	            valor = Float.parseFloat(sc.nextLine().replace(",", "."));

	            if (valor <= 0) {
	            	solicitarValorPositivo();
	                entradaInvalida = true;
	            } else {
	            	entradaInvalida = false;
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Valor inválido. Digite apenas números (use ponto ou vírgula para centavos).");
	            entradaInvalida = true;
	        }
	    } while (entradaInvalida);
		
		contas.get(i).adicionarSaldo(valor);
		System.out.println("Deposito realizado\n" + contas.get(i).status());
	}
	
	
	public static void saque() {
		int id = 0;
		float valor = 0.f;
		boolean entradaInvalida = false;
		System.out.println("Qual o ID da sua conta?");
		id = sc.nextInt();
		sc.nextLine();
		
		int i = conferirID(id);
		if (i == -1) 
			return;
		
		do {
	        try {
				System.out.println("Qual o valor que você deseja sacar?");
	            valor = Float.parseFloat(sc.nextLine().replace(",", "."));

	            if (valor <= 0) {
	            	solicitarValorPositivo();
	                entradaInvalida = true;
	            }else {
	            	entradaInvalida = false;
	            }
	        }catch (NumberFormatException e) {
	            System.out.println("Valor inválido. Digite apenas números (use ponto ou vírgula para centavos).");
	            entradaInvalida = true;
	        }
		}while (entradaInvalida);
	
		contas.get(i).setSaque((int) valor);
		System.out.println("Saque realizado com sucesso.\n");

	}
	
	
	public static void checarSaldo() {
		int id = 0;
		System.out.println("Vamos checar o seu saldo.");
		System.out.println("Qual o ID da sua conta?");
		id = sc.nextInt();
		sc.nextLine();
		int i = conferirID(id);
		if (i == -1)
			return;

		System.out.printf("O saldo de %s é de R$:%.2f \n", contas.get(i).getTitular(), contas.get(i).getSaldo());
	}
	
	public static void status(){
		int id = 0;
		System.out.println("Para mostrar o status da conta, digite o ID dela: ");
		id = sc.nextInt();
		sc.nextLine();
		int i = conferirID(id);
		if (i == -1)
			return;
		System.out.println(contas.get(i).status());	
	}
	
	
	public static void transferir() {
		
		int id = 0;
		float valor = 0.f;
		int idDestino = 0;
		boolean entradaInvalida = false;

		System.out.println("Antes da transferencia, o banco precisa de algumas informações.");
		System.out.println("Qual a conta de origem? Digite o ID dela: ");
		id = sc.nextInt();
		sc.nextLine();
		int i = conferirID(id);
		if (i == -1)
			return;
		
		System.out.println("Qual a conta de destino? Digite o ID dela: ");
		idDestino = sc.nextInt();
		sc.nextLine();
		int j = conferirID(idDestino);
		if (j == -1)
			return;
		
		if(contas.get(i).getNumConta() == contas.get(j).getNumConta()){
			System.out.println("Não é possível fazer uma transferencia para si mesmo, use o depósito.\n");
			return;
		}
		
		do {
	        try {
				System.out.println("Qual valor você deseja transferir? ");
	            valor = Float.parseFloat(sc.nextLine().replace(",", "."));

	            if (valor <= 0) {
	            	solicitarValorPositivo();
	                entradaInvalida = true;
	            }else {
	            	entradaInvalida = false;
	            }
	        }catch (NumberFormatException e) {
	            System.out.println("Valor inválido. Digite apenas números (use ponto ou vírgula para centavos).");
	            entradaInvalida = true;
	        }
		}while (entradaInvalida);
		
		if(valor>contas.get(i).getSaldo()) {
			saldoInsuficiente();
		}else{	
			contas.get(i).transferencia(valor);
			contas.get(j).adicionarSaldo(valor);
			System.out.println("Transferencia realizada com sucesso.\n");
		}
	}
}
	

	

