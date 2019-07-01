package Socket.cliente_servidor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;



public class Servidor {
	
	public void go() {
		try {

			System.out.println("Iniciando conexão");
			ServerSocket serverSocket = new ServerSocket(1234);
			System.out.println("Aguardando conexão.");
			
			Socket socket = serverSocket.accept();
			System.out.println("Conexão estabelecida");
			
			InputStream input = socket.getInputStream();
			OutputStream output = socket.getOutputStream();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(input));
			PrintStream escrita = new PrintStream(socket.getOutputStream());
			
			Scanner teclado = new Scanner(System.in);
			
			while (true) {
				String mensagem = in.readLine();
				
				System.out.println("Mensagem recebida do cliente["+
								socket.getInetAddress().getHostName() + "]: "
								+ mensagem);
				
				
				if("FIM".equals(mensagem)) {
					break;
				}
				
				System.out.print("Digite uma mensagem servidor: ");
				String enviar = teclado.nextLine();
				escrita.println(enviar);
			}
			
			System.out.println("Encerrando conexão.");
			
			in.close();
			escrita.close();
			socket.close();
			
			System.out.println("Encerrando servidor.");
			serverSocket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Servidor().go();
	}
}
