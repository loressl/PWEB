package Socket.Prova1_2017_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	public static void main(String[] args) throws UnknownHostException, IOException {
		try {
			System.out.println("Abrindo conexão do cliente...");
			Socket socket = new Socket("127.0.0.1", 1234);

			DataInputStream entrada = new DataInputStream(socket.getInputStream());
			DataOutputStream saida = new DataOutputStream(socket.getOutputStream());

			byte[] inteiros = { 1, 5,-6, 2, 3 };//passa os numeros em byte
			byte[] inteiros2= {7,9,0,13, -10};
			int op = 4;

			saida.write(op);
			saida.flush();
			saida.write(inteiros.length);
			saida.flush();
			saida.write(inteiros);
			saida.flush();
			saida.write(inteiros2.length);
			saida.flush();
			saida.write(inteiros2);
			saida.flush();
			if (op < 3) {
				int newLength = entrada.read();
				byte[] newvet = new byte[newLength];
				entrada.read(newvet, 0, newLength);
				for (int i = 0; i < newvet.length; i++) {
					System.out.print(newvet[i]);
					System.out.print(",");
				}
			} else {
				int qtd = entrada.read();
				System.out.println("qtd > " + qtd);
			}

			saida.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Encerrando conexão do cliente...");
	}
}
