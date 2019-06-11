package Socket.ex1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("Iniciando cliente.");
		System.out.println("Iniciando conexão com o servidor.");
		
		Socket socket = new Socket("127.0.0.1", 1234);
		
		System.out.println("Conexão estabelecida.");
		
		InputStream input = socket.getInputStream();
		OutputStream output = socket.getOutputStream();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(input));
		PrintStream out = new PrintStream(output);
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("Digite uma mensagem: ");
			String mensagem = scanner.nextLine();
			out.println(mensagem);
			
			if("FIM".equals(mensagem)) {
				break;
			}
			
			mensagem = in.readLine();
			System.out.println("Mensagem recebida do servidor: " + mensagem);
		}
		
		System.out.println("Encerrando conexão.");
		in.close();
		out.close();
		socket.close();

	}
	
	
//	public static void main(String[] args) throws UnknownHostException, IOException {
//		
//		Socket cliente = new Socket("127.0.0.1", 1234);
//		String texto = "Tudo ok?";
//		// obtem o fluxo de bits-stream- do cliente
//		OutputStream outputStream = cliente.getOutputStream();
//		// pega o stream
//		DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
//		// envia o dado pelo stream obtido do cliente
//		dataOutputStream.writeUTF(texto);
//		dataOutputStream.flush(); // send the message
//		dataOutputStream.close();
//		
//		
//	}
}
