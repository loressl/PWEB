package Socket.testes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) {
		System.out.println("conexão servidor...");
		try {
			
			ServerSocket servidor = new ServerSocket(1234);
			
			Socket conexao = servidor.accept();
			
			DataInputStream fluxoEntrada = new DataInputStream(conexao.getInputStream());
			DataOutputStream fluxoSaida = new DataOutputStream(conexao.getOutputStream());
			
			String entrada = fluxoEntrada.readUTF();
			System.out.println("Cliente : " + entrada);
			
			String lengt= fluxoEntrada.readUTF();
			
			while(lengt != null) {
				System.out.println(lengt);
				lengt = fluxoEntrada.readUTF();
			}
		
			fluxoSaida.writeUTF("Oi, eu sou o servidor!");
			fluxoSaida.flush();

			
			fluxoSaida.close();
			fluxoEntrada.close();
			conexao.close();
			servidor.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
