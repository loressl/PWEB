package Socket.ex2;

import java.io.PrintStream;
import java.net.Socket;

public class Cliente {
	public static void main(String[] args) {
		
		try {
			Socket cliente = new Socket("127.0.0.1", 1234);
			System.out.println("Cliente se conectou ao servidor!");
			
			PrintStream escreve = new PrintStream(cliente.getOutputStream(), true);
			escreve.println("Cliente está conectado!!");
			
			escreve.close();
			cliente.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	}
}
