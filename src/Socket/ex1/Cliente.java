package Socket.ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		String meg= "Meu texto";
		
		Socket socket = new Socket("127.0.0.1", 1234);
		System.out.println("O cliente se conectou ao servidor");		
		
		//o true liga uma função no método printwriter chamada de flush
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
		
		printWriter.println(meg);
		
	}
}
