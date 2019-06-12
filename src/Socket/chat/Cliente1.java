package Socket.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente1 {

	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket cliente = new Socket("127.0.0.1", 1234);
		System.out.println("Cliente 1 conectado!");

		BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
		PrintStream saida = new PrintStream(cliente.getOutputStream());

		saida.println("Servidor, aqui que é cliente 1 e meu ip é " + cliente.getInetAddress());

		Scanner lerServidor = new Scanner(cliente.getInputStream());

		System.out.println(lerServidor.nextLine());

		entrada.close();
		saida.close();
		cliente.close();

	}

}
