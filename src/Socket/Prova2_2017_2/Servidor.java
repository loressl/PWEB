package Socket.Prova2_2017_2;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) throws IOException {
		
		try {
			System.out.println("Iniciando servidor...");
			ServerSocket servidor = new ServerSocket(1234);
			
			while(true) {
				System.out.println("Aguardando conexão...");
				Socket cliente = servidor.accept(); 
				Thread thread = new Thread(new CaracteresDigitos(cliente));
				thread.start();	
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println("Encerrando conexão do servidor...");
	}
}
