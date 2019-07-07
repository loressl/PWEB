package Socket.cliente_servidor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Cliente {
	
	public void go() {
		try {
			System.out.println("Iniciando cliente.");
			System.out.println("Iniciando conexão com o servidor.");
			
			Socket socket = new Socket("127.0.0.1", 1234);
			System.out.println("Conexão estabelecida.");

			InputStream input = socket.getInputStream();
			OutputStream output = socket.getOutputStream();
			
			BufferedReader ler = new BufferedReader(new InputStreamReader(input));
			PrintStream escrita = new PrintStream(output);
			
			Scanner teclado = new Scanner(System.in);
			
			while(true) {
				
				System.out.print("Digite uma mensagem cliente: ");
				String mensagem = teclado.nextLine();
				escrita.println(mensagem);
				
				if("FIM".equals(mensagem))
					break;
				
				mensagem = ler.readLine();
				System.out.println("Mensagem recebida do servidor: "+ mensagem);
			}
			
			System.out.println("Encerrando conexão.");
			
			ler.close();
			escrita.close();
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Cliente().go();
	}
}
