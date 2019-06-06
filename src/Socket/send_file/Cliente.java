package Socket.send_file;

import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) {
		
		String file = "digitar o caminho do arquivo com o nome dele";
		
		try {
			Socket cliente = new Socket("127.0.0.1", 1234);
			System.out.println("Cliente conectado! Enviando arquivo....");
			
			ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
			
			FileInputStream arquivo = new FileInputStream(file);
			
			byte[] buf = new byte[4096];
			
			while (true) {
				int len = arquivo.read(buf);
				if(len == -1)
					break;
				out.write(buf, 0, len);
				
			}
			
			out.close();
			cliente.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	
	}

}
