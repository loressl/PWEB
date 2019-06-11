package Socket.send_file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) {
		
		String file= "C:/Users/maqui/OneDrive/Documentos/ANÁLISE E DESENVOLVIMENTO DE SISTEMAS/4º SEMESTRE/PWEB/Arquivoenviadopelocliente.txt";
		
		try {
			
			ServerSocket servidor = new ServerSocket(1234);
			System.out.println("Porta 1234");
			
			Socket cliente = servidor.accept();
			
			ObjectInputStream input = new ObjectInputStream(cliente.getInputStream());
			
			FileOutputStream arquivo = new FileOutputStream(file);
			
			byte[] buf = new byte[4096];
			
			while (true) {
				int len = input.read(buf);
				if(len == -1)
					break;
				arquivo.write(buf, 0, len);
				
			}
			
			input.close();
			servidor.close();
			cliente.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		System.out.println("Arquivo recebido com sucesso!");
	}
}
