package Socket.cliente_servidor_THREAD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Atendente implements Runnable {

	private Socket socket;

	private BufferedReader ler;
	private PrintStream escrever;

	private boolean inicializado;
	private boolean executando;

	private Thread thread;

	public Atendente(Socket socket) throws IOException {
		this.socket = socket;

		this.inicializado = false;
		this.executando = false;

		open();
	}

	private void open() throws IOException {
		try {

			this.ler = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.escrever = new PrintStream(socket.getOutputStream());
			this.inicializado = true;
		} catch (Exception e) {
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

		try {
			this.socket.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		this.ler = null;
		this.escrever = null;
		this.socket = null;

		this.inicializado = false;
		this.executando = false;

		this.thread = null;
	}

	public void start() {
		if (!this.inicializado || this.executando)
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

		while (this.executando) {
			try {
				this.socket.setSoTimeout(2500);
				String mensagem= ler.readLine();
				System.out.println(
						"Mensagem recebida do cliente[" + socket.getInetAddress().getHostName() + "]: " + mensagem);

				if ("FIM".equals(mensagem)) {
					break;
				}

				escrever.println(mensagem);
				
			} catch (SocketTimeoutException e) {
				// ignorar
			} catch (Exception e) {
				System.out.println(e);
				break;
			} 

		}

		System.out.println("Encerrando conexão.");
		close();
	}

}
