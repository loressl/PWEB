package Socket.testes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) {
		System.out.println("conexão cliente...");
		try {
			
			Socket conexao = new Socket("127.0.0.1", 1234);
			
			Scanner scanner = new Scanner(conexao.getInputStream());
			
			DataInputStream fluxoEntrada = new DataInputStream(conexao.getInputStream());
			DataOutputStream fluxoSaida = new DataOutputStream(conexao.getOutputStream());
			
			char[] cs= {'B', 'A'};
			int[]  t= {1,2};

			fluxoSaida.writeUTF("Oi, eu sou o cliente!");
			fluxoSaida.flush();
			
			for(int i=0; i< t.length; i++) {
				fluxoSaida.writeUTF(String.valueOf(t[i]));
				fluxoSaida.flush();
			}
			
			String entrada = fluxoEntrada.readUTF();
			System.out.println("Servidor: " + scanner.nextLine());

			
			fluxoSaida.close();
			fluxoEntrada.close();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
