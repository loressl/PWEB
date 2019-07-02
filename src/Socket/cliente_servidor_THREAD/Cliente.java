package Socket.cliente_servidor_THREAD;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Cliente implements Runnable {

	private Socket socket;

	private BufferedReader ler;
	private PrintStream escrever;

	private boolean inicializado;
	private boolean executando;

	private Thread thread;

	public Cliente(String endereco, int porta) throws Exception {
		this.inicializado = false;
		this.executando = false;
		
		this.open(endereco, porta);
	}

	private void open(String endereco, int porta) throws Exception {
		try {
			this.socket = new Socket(endereco, porta);
			this.ler = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.escrever = new PrintStream(socket.getOutputStream());
			this.inicializado = true;
		} catch (Exception e) {
			System.out.println(e);
			close();
			throw e;
		}
	}

	private void close() {
		if (this.ler != null) {
			try {
				this.ler.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		if (this.escrever != null) {
			try {
				this.escrever.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		if (this.socket != null) {
			try {
				this.socket.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		this.ler = null;
		this.escrever = null;
		this.socket = null;
		this.inicializado = false;
		this.executando = false;
		this.thread = null;
	}

	public void start() {
		if (!inicializado || executando) {
			return;
		}
		executando = true;
		thread = new Thread(this);
		thread.start();
	}

	public void stop() throws Exception {
		executando = false;
		if (thread != null) {
			thread.join();
		}
	}

	public boolean isExecutando() {
		return executando;
	}

	public void send(String mensagem) {
		escrever.println(mensagem);
	}


	public static void main(String[] args) {
		try {
			System.out.println("Iniciando cliente.");
			System.out.println("Iniciando conexão com o servidor.");

			Cliente socket = new Cliente("127.0.0.1", 1234);
			System.out.println("Conexão estabelecida.");
			
			socket.start();

			Scanner teclado = new Scanner(System.in);

			while (true) {

				System.out.print("Digite uma mensagem cliente: ");
				String mensagem = teclado.nextLine();

				if(!socket.isExecutando())
					break;
				
				socket.send(mensagem);
				
				if ("FIM".equals(mensagem))
					break;

			}

			System.out.println("Encerrando conexão.");

			socket.stop();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (this.executando) {
			try {
				this.socket.setSoTimeout(2500);
				String mensagem = ler.readLine();
				if (mensagem == null) {
					break;
				}
				System.out.println("Mensagem enviada pelo servidor: " + mensagem);

			} catch (SocketTimeoutException e) {
				// TODO: handle exception
			} catch (Exception e) {
				System.out.println(e);
				break;
			}
		}
		
		close();

	}
}
