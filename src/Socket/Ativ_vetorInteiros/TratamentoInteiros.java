package Socket.Ativ_vetorInteiros;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class TratamentoInteiros implements Runnable{

	private Socket cliente;
	
	public TratamentoInteiros(Socket socket) {
		this.cliente = socket;
	}
	
	private byte[] sort(byte[] vetor) {
		
		for(int i= vetor.length; i>=1; i--) {
			for(int j=1; j<i; j++) {
				if(vetor[j-1] >vetor[j]) {
					byte aux = vetor[j];
					vetor[j] = vetor[j-1];
					vetor[j-1]= aux;
				}
			}
		}
		return vetor;
	}
	
	private byte[] impares(byte[] vetor) {
		byte[] novo = new byte[vetor.length];
		int contador = 0;
		
		for(int i=0; i< vetor.length; i++) {
			if(vetor[i]%2!=0)
				novo[contador++]= vetor[i];
		}
		
		byte[] trim = new byte[contador];
		System.arraycopy(novo, 0, trim, 0, contador);
		return trim;
	}
	
	private byte[] setModulo(byte[] vetor) {
		for(int i=0; i< vetor.length; i++) {
			if(vetor[i]<0)
				vetor[i]= (byte) (vetor[i]*(-1));
		}
		
		return vetor;
	}
	
	@Override 
	public void run() {
		try {
			DataInputStream leitura= new DataInputStream(cliente.getInputStream());
			PrintStream escrita = new PrintStream(cliente.getOutputStream());
			
			byte op = leitura.readByte();
			int qtd = leitura.read();
			byte[] vetor= new byte[qtd];
			leitura.read(vetor, 0, qtd);
			
			byte[] resultado = null;
			
			switch (op) {
			case 1:
				resultado = this.sort(vetor);
				escrita.write(resultado.length);
				escrita.flush();
				escrita.write(resultado);
				escrita.flush();
				break;
			case 2:
				resultado = this.sort(this.impares(vetor));
				escrita.write(resultado.length);
				escrita.flush();
				escrita.write(resultado);
				escrita.flush();
				break;
			case 3:
				escrita.write(this.impares(vetor).length);
				escrita.flush();
				break;
			case 4:
				resultado = this.setModulo(vetor);
				escrita.write(resultado.length);
				escrita.flush();
				escrita.write(resultado);
				escrita.flush();
				break;
			default:
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

}
