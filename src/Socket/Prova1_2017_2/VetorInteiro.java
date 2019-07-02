package Socket.Prova1_2017_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class VetorInteiro implements Runnable{
	
	private Socket cliente;
	
	
	public VetorInteiro(Socket cliente) {
		this.cliente = cliente;
	}
	
	public byte[] pares(byte[] vetor){
		byte[] pares = new byte[vetor.length];
		int cont =0;
		
		for(int i=0; i< vetor.length; i++) {
			if(vetor[i]%2 == 0)
				pares[cont++]=vetor[i];
		}
		
		byte[] trim = new byte[cont];
		System.arraycopy(pares, 0, trim, 0, cont);
		return trim;
		
	}
	
	public byte[] impares(byte[] vetor){
		byte[] impares = new byte[vetor.length];
		int cont =0;
		
		for(int i=0; i< vetor.length; i++) {
			if(vetor[i]%2 != 0) {
				impares[cont++]=vetor[i];
			}
		}
		
		byte[] trim = new byte[cont];
		System.arraycopy(impares, 0, trim, 0, cont);
		
		return trim;
	}
	
	public byte[] opcao1(byte[] vetor){
		byte[] todos = new byte[vetor.length]; 
		int cont =0;
		byte[] pares = this.pares(vetor);
		byte[] impares = this.impares(vetor);
		
		try {
			
			for(int i=0; i < 2; i++) {
				if(i==0) {
					for(int j=0; j< pares.length; j++) {
						todos[cont++] = pares[j];
					}
				}else {
					for(int j=0; j< impares.length; j++) {
						todos[cont++] = impares[j];
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return todos;
	}
	
	public byte[] opcao2(byte[] vetor) {
		
		for(int i = vetor.length ; i>= 1; i--) {
			for(int j =1; j<i ; j++) {
				if(vetor[j-1] > vetor[j]) {
					byte aux = vetor[j];
					vetor[j] = vetor[j-1];
					vetor[j-1]= aux;
				}
			}
		}
		
		return vetor;
	}

	@Override
	public void run() {

		try {
			InputStream in = cliente.getInputStream();
			OutputStream out = cliente.getOutputStream();
			
			DataInputStream entrada = new DataInputStream(in);
			DataOutputStream saida = new DataOutputStream(out);
			
			byte opcao = entrada.readByte();//ler primeira op��o
			int lengt = entrada.read();//ler o tamanho do vetor
			byte[] vet = new byte[lengt];
			entrada.read(vet,0,lengt);//passa o vetor do fluxo de entra para o vet
			
			byte[] resultado = null;
			
			switch (opcao) {
			case 1:
				resultado = this.opcao1(vet);
				saida.write(resultado.length);
				saida.flush();
				saida.write(resultado);
				saida.flush();
				break;
			case 2:
				resultado = this.opcao2(this.impares(vet));
				saida.write(resultado.length);
				saida.flush();
				saida.write(resultado);
				saida.flush();
				break;
			case 3:
				saida.write(this.impares(vet).length);
				saida.flush();
				break;
			case 4:
				break;
			default:
				break;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
