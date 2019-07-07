package Socket.Ativ_vetorInteiros;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Cliente {
	public static void main(String[] args) {
		try {
			
			System.out.println("Cliente.....");
			Socket cliente = new Socket("127.0.0.1", 1234);
			
			DataInputStream leitura= new DataInputStream(cliente.getInputStream());
			PrintStream escrita = new PrintStream(cliente.getOutputStream());
			
			byte[] vetor= {2,5,8,3,12,9,1};
			int op=4;
			
			escrita.write(op);
			escrita.flush();
			escrita.write(vetor.length);
			escrita.flush();
			escrita.write(vetor);
			escrita.flush();
	
			if(op!=3) {
				int length= leitura.read();
				byte[] recebido= new byte[length];
				leitura.read(recebido,0,length);
				for(int i=0; i<recebido.length; i++) {
					System.out.print(recebido[i]);
					System.out.print(",");
				}
			}else {
				System.out.println("qtd> "+ leitura.read());
			}
			
			escrita.close();
			leitura.close();
			cliente.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
