package Socket.ex1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket cliente = new Socket("127.0.0.1", 1234);
		String texto = "Tudo ok?";
		// obtem o fluxo de bits-stream- do cliente
		OutputStream outputStream = cliente.getOutputStream();
		// pega o stream
		DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
		// envia o dado pelo stream obtido do cliente
		dataOutputStream.writeUTF(texto);
		dataOutputStream.flush(); // send the message
		dataOutputStream.close();
		
		
	}
}
