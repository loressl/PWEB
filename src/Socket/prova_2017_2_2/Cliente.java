package Socket.prova_2017_2_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		try {
			System.out.println("Iniciando cliente...");
			Socket cliente = new Socket("127.0.0.1", 1234);
			
			DataInputStream in = new DataInputStream(cliente.getInputStream());
			DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
			
			byte[] teste= {'A','1','E','5','T','7','W','8','G'};
			byte[] teste2= {3,5,4,2,7};
			int op =4;
			
			out.write(op);
			out.flush();
			out.write(teste2.length);
			out.flush();
			out.write(teste2);
			out.flush();
			
			int newLength = in.read();
			byte[] newvet = new byte[newLength];
			in.read(newvet,0, newLength);
			if(op==1) {
				for(int i =0; i< newvet.length; i++) {
					System.out.print((char)newvet[i]);
					System.out.print(" ,");
				}				
			}else {
				for(int i =0; i< newvet.length; i++) {
					System.out.print(newvet[i]);
					System.out.print(" ,");
				}
			}
			
			out.close();
			cliente.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("\nEncerrando conexão do cliente...");
		
	}
}
