package chat_caelum;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
	
	public static void main(String[] args) {
		new Servidor(1234).executa();
	}
	
	private int porta;
	private List<PrintWriter> clientes;
	
	public Servidor(int porta) {
		this.porta = porta;
		this.clientes = new ArrayList<>();
	}
	
	public void executa() {
		
		try {
			ServerSocket servidor = new ServerSocket(this.porta);
			System.out.println("Porta 1234 aberta");
			
			while(true) {
				//aceita um cliente
				Socket cliente = servidor.accept();
				System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());
				
				//adiciona saida do cliente à lista
				PrintWriter escrita = new PrintWriter(cliente.getOutputStream());
				this.clientes.add(escrita);
				
				//cria tratador de cliente numa nova thread
				TrataCliente tc = new TrataCliente(cliente.getInputStream(), this);
				new Thread(tc).start();
				
				escrita.close();
				cliente.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void distribuiMensagem(String msg) {
		//envia msg para todo mundo
		for(PrintWriter cliente: this.clientes) {
			cliente.println(msg);
		}
	}
}
