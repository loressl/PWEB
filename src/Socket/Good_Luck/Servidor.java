package Socket.Good_Luck;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
		int porta = 1234;
		
		try {
			System.out.println("Iniciando servidor...");
			ServerSocket servidor = new ServerSocket(porta);
			while(true) {
				System.out.println("Aguardando conexão...");
				Socket cliente = servidor.accept();
				Thread thread = new Thread(new SolicitacaoDeMensagens(cliente));
				thread.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
