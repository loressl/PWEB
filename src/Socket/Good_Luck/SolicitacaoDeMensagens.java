package Socket.Good_Luck;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Random;

public class SolicitacaoDeMensagens implements Runnable{

	private Socket cliente;
	String[] mensagens= {"Hoje � seu dia de sorte!", "Viva cada dia como se fosse o �ltimo.", "Voc� � uma pessoa iluminada."
			, "Coisas grandes est�o para acontecer!", "Preste aten��o aos sinais."};
	
	public SolicitacaoDeMensagens(Socket cliente) {
		this.cliente = cliente;
	}
	
	private String boaSorte() {
		Random random = new Random();
		int posicao = random.nextInt(mensagens.length);
		return mensagens[posicao];
	}
	
	@Override
	public void run() {
		try {
			DataInputStream in = new DataInputStream(this.cliente.getInputStream());
			DataOutputStream out = new DataOutputStream(this.cliente.getOutputStream());
			
			String msg = this.boaSorte();
			
			out.writeUTF(msg);
			out.flush();
			
			System.out.println(in.readUTF());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
