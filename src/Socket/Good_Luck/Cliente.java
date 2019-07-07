package Socket.Good_Luck;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Cliente {
	public static void main(String[] args) {
		try {
			Socket cliente = new Socket("127.0.0.1", 1234);
			
			DataOutputStream escrever = new DataOutputStream(cliente.getOutputStream());
			DataInputStream ler = new DataInputStream(cliente.getInputStream());
			
			String msg = ler.readUTF();
			System.out.println("Mensagem do dia: " + msg);
			
			escrever.writeUTF("Obrigada servidor!");
			escrever.flush();
			
			ler.close();
			cliente.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
