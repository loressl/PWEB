package Socket.cliente_servidor_THREAD;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Servidor implements Runnable{
	
	private ServerSocket servidor;
	
	private List<Atendente> atendentes;
	
	private boolean inicializado;
	private boolean executando;
	
	private Thread thread;
	
	public Servidor(int porta) throws IOException {
		this.atendentes = new ArrayList<>();
		
		this.inicializado = false;
		this.executando = false;
		
		open(porta);
	}
	
	private void open(int porta) throws IOException {
		this.servidor = new ServerSocket(porta);
		this.inicializado = true;
	}
	
	private void close() {
		
		for(Atendente atendente : this.atendentes) {
			try {
				atendente.stop();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		try {
			this.servidor.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		this.servidor = null;
		this.inicializado = false;
		this.executando = false;
		this.thread = null;
	}
	
	public void start() {
		if(!this.inicializado || this.executando)
			return;
		
		this.executando = true;
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	public void stop() throws InterruptedException {
		this.executando = false;
		if(this.thread != null)
			this.thread.join();
	}
	
	@Override
	public void run() {
		System.out.println("Aguardando conexão.");
		while(this.executando) {
			try {
				this.servidor.setSoTimeout(2500);
				Socket socket = this.servidor.accept();
				System.out.println("Conexão estabelecida");
				
				Atendente atendente = new Atendente(socket);
				atendente.start();
				
				atendentes.add(atendente);
				
			} catch (SocketTimeoutException e) {
		
			}catch (Exception e) {
				System.out.println(e);
				break;
			}
		}
		
		close();
		
	}
	
	public static void main(String[] args) throws IOException {
		try {
			System.out.println("Iniciando conexão");
			Servidor servidor = new Servidor(1234);
			servidor.start();
			
			System.out.println("Pressionse ENTER para encerrar o servidor.");
			new Scanner(System.in).nextLine();
			
			System.out.println("Encerrando servidor.");
			servidor.stop();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
