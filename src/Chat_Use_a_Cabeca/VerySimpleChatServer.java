package Chat_Use_a_Cabeca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class VerySimpleChatServer {
	ArrayList clientOutputStreams;
	
	public class ClientHandler implements Runnable {
		BufferedReader reader;
		Socket socket;
		
		public ClientHandler(Socket clientSocket) {
			try {
				socket = clientSocket;
				InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
				reader = new BufferedReader(inputStreamReader);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			String message;
			try {
				while((message = reader.readLine()) != null) {
					System.out.println("read " + message);
					tellEveryone(message);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	public static void main(String[] args) {
		new VerySimpleChatServer().go();
	}
	
	public void go() {
		clientOutputStreams = new ArrayList<>();
		try {
			ServerSocket serverSocket = new ServerSocket(1234);
			
			while(true) {
				Socket cliente = serverSocket.accept();
				PrintWriter writer = new PrintWriter(cliente.getOutputStream());
				clientOutputStreams.add(writer);
				Thread thread  = new Thread(new ClientHandler(cliente));
				thread.start();
				System.out.println("got a connection");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void tellEveryone(String message) {
		Iterator<?> iterator = clientOutputStreams.iterator();
		while(iterator.hasNext()) {
			try {
				PrintWriter writer = (PrintWriter) iterator.next();
				writer.println(message);
				writer.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
