package Socket.ex1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
	public static void main(String[] args) throws IOException {
		
		//só diz que vai usar a porta, fica na escuta 
		ServerSocket serverSocket = new ServerSocket(1234); 
		System.out.println("Porta 1234 aberta");

		while(true) {
			//bloqueia a execução do programa até que o cliente solicite a conexão
			//e isso aconetece qdo o cliente abre um socket
			Socket socket = serverSocket.accept();
			String resposta= "Tranquilo!!";
			
			//filtro para leitura do input stream do cliente
			Scanner scanner = new Scanner(socket.getInputStream());
	                    //imprime o que pegou
			String textoMaiusculo = scanner.nextLine();
			System.out.println(textoMaiusculo.toUpperCase());
			
		}
		
	}

}
