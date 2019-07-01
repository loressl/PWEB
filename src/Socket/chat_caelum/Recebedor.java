package Socket.chat_caelum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Recebedor implements Runnable{

	private InputStream servidor;
	
	public Recebedor(InputStream servidor) {
		this.servidor = servidor;
	}
	
	@Override
	public void run() {
		
		try {
			InputStreamReader in = new InputStreamReader(servidor);
			BufferedReader ler = new BufferedReader(in);
			String message = ler.readLine();
			
			while(message != null) {
				System.out.println(message);
				message = ler.readLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
