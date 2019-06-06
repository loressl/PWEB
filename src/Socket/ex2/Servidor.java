package Socket.ex2;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
	public static void main(String[] args) {

		try {
			ServerSocket servidor = new ServerSocket(1234);
			System.out.println("Porta 1234 aberta");

			Socket cliente = servidor.accept();
			System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());// IMPRIME
																											// O IP
																											// DO
																											// CLIENTE
			Scanner ler = new Scanner(cliente.getInputStream());
			while (ler.hasNextLine()) {
				System.out.println(ler.nextLine());
			}

			ler.close();
			servidor.close();
			cliente.close();

		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	}
}
