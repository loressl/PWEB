package chat_caelum;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	
	public static void main(String[] args) {
		new Cliente("127.0.0.1", 1234).executa();
	}
	
	private String host;
	private int porta;
	
	public Cliente(String host, int porta) {
		this.host = host;
		this.porta = porta;
	}
	
	public void executa() {
		try {
			Socket cliente = new Socket(this.host, this.porta);
			System.out.println("O cliente se conectou ao servidor!");
			
			//thread para receber mensagens do servidor
			Recebedor recebedor = new Recebedor(cliente.getInputStream());
			new Thread(recebedor).start();
			
			//lê msgs do teclado e manda pro servidor
			Scanner teclado = new Scanner(System.in);
			PrintWriter escrita = new PrintWriter(cliente.getOutputStream());
			while(teclado.hasNextLine()) {
				escrita.println(teclado.nextLine());
			}
			
			escrita.close();
			teclado.close();
			cliente.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
