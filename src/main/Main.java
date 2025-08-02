package main;
import java.util.Locale;
import java.util.Scanner;
import services.Banco;
import models.Conta;

public class Main {
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		byte escolha;
		boolean continuar = true;
		boolean sair;
		
		System.out.println("\n\n====== Seja muito bem vindo ao Banco Java ======\n\n=============");
		do {
			System.out.println("O que deseja fazer? ");
			System.out.println("1 - Criar Conta");
			System.out.println("2 - Depositar");
			System.out.println("3 - Sacar");
			System.out.println("4 - Transferir");
			System.out.println("5 - Consultar saldo");
			System.out.println("6 - Checar dados da conta");
			System.out.println("7 - Sair\n=============");
			
			String entrada = sc.nextLine(); 
		    try {
		        escolha = Byte.parseByte(entrada);
		    } catch (NumberFormatException e) {
		        System.out.println("Entrada inválida. Digite um número de 1 a 7\n");
		        continue; 
		    }
		    
		switch(escolha) {
		
		case 1:
			Banco.criar();
			break;
		case 2:
			Banco.depositar();
			break;
		case 3:
			Banco.saque();
			break;
		case 4:
			Banco.transferir();
			break;
		case 5:
			Banco.checarSaldo();
			break;
		case 6:
			Banco.status();
			break;
		case 7:
			System.out.println("Deseja realmente sair? (S ou N)");
			sair = sc.nextLine().toUpperCase(Locale.ROOT).equals("S");
			if(sair) {
				System.out.println("Saindo...");
				continuar = false;
			}	
			break;
		default:
			System.out.println("Valor invalido, digite outro valor\n");
		}
		
		}while(continuar);
		
		sc.close();
	}

}
