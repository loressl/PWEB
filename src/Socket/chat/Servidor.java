package Socket.chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) throws IOException {

		System.out.println("Iniciando servidor.");
		//criando um socket q fica escutando a porta 
		ServerSocket servidorServerSocket = new ServerSocket(1234);

		System.out.println("Aguardando conexão com o cliente...");
		//loop principal
		while(true) {
			
			Socket cliente = servidorServerSocket.accept();
			System.out.println("Conexão estabelecida.");
			
			BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			String mensagem = entrada.readLine();
			System.out.println(mensagem);
			
			DataOutputStream saida = new DataOutputStream(cliente.getOutputStream());
			saida.writeUTF("Servidor: Mensagem recebida cliente " + cliente.getInetAddress() + ".");
			
			entrada.close();
			saida.close();
			cliente.close();
		}


	}
}
