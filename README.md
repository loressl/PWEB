# PWEB

//CLIENTE
public class Cliente {

    public static void main(String[] args) throws IOException {
       Socket cliente =new Socket("127.0.0.1",1234);
       String texto = "deu certo";
       //obtem o fluxo de bits-stream- do cliente
       OutputStream outputStream = cliente.getOutputStream();
       //pega o stream 
       DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
       //envia o dado pelo stream obtido do cliente
       dataOutputStream.writeUTF(texto);
       dataOutputStream.flush(); // send the message
       dataOutputStream.close(); 
    }
    
}

//SERVIDOR
public class Servidor {
    public static void main(String[] args) throws IOException{
        //só diz que vai usar a porta, fica na escuta
		ServerSocket serverSocket = new ServerSocket(1234);
		System.out.println("Porta 1234 aberta");
		
		while(true) {
			//bloqueia a execução do programa até que o cliente solicite a conexão
			//e isso aconetece qdo o cliente abre um socket
			Socket socket = serverSocket.accept();
			
			//filtro para leitura do input stream do cliente
			Scanner scanner = new Scanner(socket.getInputStream());
                        //imprime o que pegou
			System.out.println(scanner.nextLine());			
		}
		
    }
}
