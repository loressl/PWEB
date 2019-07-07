package Socket.Ativ_vetorInteiros;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) {
		try {
			System.out.println("Servidor....");
			ServerSocket servidor = new ServerSocket(1234);
			while(true) {
				System.out.println("Esperando cliente....");
				Socket cliente= servidor.accept();
				Thread thread = new Thread(new TratamentoInteiros(cliente));
				thread.start();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Encerrando servidor..");
	}
}
