package Socket.ex1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {


	public static void main(String[] args) throws IOException{
		System.out.println("Iniciando servidor.");
		
		ServerSocket server = new ServerSocket(1234);
		
		System.out.println("Aguardando conexão...");
		
		Socket socket = server.accept();
		
		System.out.println("Conexão estabelecida.");
		
		InputStream input= socket.getInputStream();
		OutputStream output= socket.getOutputStream();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(input));
		PrintStream out = new PrintStream(output);
		
		while(true) {
			String mString = in.readLine();
			
			System.out.println("Mensagem recebida do cliente: "+ socket.getInetAddress().getHostAddress() + " "+ mString);
			
			if("FIM".equals(mString)) {
				break;
			}
			
			out.println(mString);
		}
		
		System.out.println("Encerrando conexão.");
		in.close();
		out.close();
		socket.close();
		
		System.out.println("Encerrando servidor.");
		
		server.close();
	}

//	public static void main(String[] args) throws IOException {
//		
//		//só diz que vai usar a porta, fica na escuta 
//		ServerSocket serverSocket = new ServerSocket(1234); 
//		System.out.println("Porta 1234 aberta");
//
//		while(true) {
//			//bloqueia a execução do programa até que o cliente solicite a conexão
//			//e isso aconetece qdo o cliente abre um socket
//			Socket socket = serverSocket.accept();
//			String resposta= "Tranquilo!!";
//			
//			//filtro para leitura do input stream do cliente
//			Scanner scanner = new Scanner(socket.getInputStream());
//	                    //imprime o que pegou
//			String textoMaiusculo = scanner.nextLine();
//			System.out.println(textoMaiusculo.toUpperCase());
//			
//		}
//		
//	}

}
