# PWEB

http://tu.nuclear868.net/Java/Prentice.Hall.Core.Java.Volume.I.Fundamentals.8th.Edition.Sep.2007.pdf


#CLIENTE ENVIANDO PARA O SERVIDOR E SERVIDOR RETORNANDO

public class Servidor {
	public static void main(String[] args) throws IOException {
		System.out.println("Iniciando servidor.");
		
		ServerSocket servidorServerSocket = new ServerSocket(1234);
		
		System.out.println("Aguardando conexão...");
		
		Socket cliente = servidorServerSocket.accept();
		
		System.out.println("Conexão estabelecida.");
		
		BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
		String mensagem = entrada.readLine();
		System.out.println(mensagem);
		
		DataOutputStream saida = new DataOutputStream(cliente.getOutputStream());
		saida.writeUTF("Mensagem recebida.");
		
		cliente.close();
	}
}







public class Cliente1 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket cliente = new Socket("127.0.0.1", 1234);
		System.out.println("Cliente conectado!");
		
		BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
		PrintStream saida = new PrintStream(cliente.getOutputStream());
		
		saida.println("Servidor, meu ip é "+ cliente.getInetAddress());
		
		Scanner lerServidor = new Scanner(cliente.getInputStream());
		
		System.out.println(lerServidor.nextLine());
		
		entrada.close();
		saida.close();
		cliente.close();

	}

}
