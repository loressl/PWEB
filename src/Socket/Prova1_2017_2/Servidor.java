package Socket.Prova1_2017_2;


import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) {
		
		try {
			
			System.out.println("Iniciando servidor....");
			ServerSocket socket = new ServerSocket(1234);
			
			while(true) {
				System.out.println("Aguardando cliente...");
				Socket cliente = socket.accept();
				Thread thread = new Thread(new VetorInteiro(cliente));
				thread.start();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println("Encerrando conexão do servidor...");
	}
}
