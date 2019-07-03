package Socket.prova_2017_2_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class CaracteresDigitos implements Runnable{

	private Socket cliente;
	
	public CaracteresDigitos(Socket cliente) {
		this.cliente = cliente;
	}
	
	public byte[] getNumeros(byte[] caracteres) {
		byte[] novo = new byte[caracteres.length];
		int cont =0;
		
		for(int i=0; i<caracteres.length ; i++) {
			if(caracteres[i]>= 48 && caracteres[i]<=57)
				novo[cont++] = caracteres[i];
		}
		
		byte[] trim = new byte[cont];
		System.arraycopy(novo, 0, trim, 0, cont);
		
		return trim;
	}
	
	public byte[] getLetras(byte[] caracteres) {
		byte[] novo = new byte[caracteres.length];
		int cont=0;
		
		for(int i=0; i<caracteres.length; i++) {
			if((caracteres[i]>=65 && caracteres[i]<=90) || (caracteres[i]>=97 && caracteres[i]<=122)) {
				novo[cont++]=caracteres[i];
			}
		}
		
		byte[] trim = new byte[cont];
		System.arraycopy(novo, 0, trim, 0, cont);
		
		return trim;
	}
	
	public byte[] numerosInvertidos(byte[] numeros) {
		
		for(int i=numeros.length ; i>=1; i--) {
			for(int j = 1; j<i ; j++) {
				if(numeros[j-1]< numeros[j]) {
					byte aux = numeros[j];
					numeros[j] = numeros[j-1];
					numeros[j-1] = aux;
				}
			}
		}
		
		return numeros;
	}
	
	public byte[] sort(byte[] numeros) {
		
		for(int i=numeros.length ; i>=1; i--) {
			for(int j = 1; j<i ; j++) {
				if(numeros[j-1] > numeros[j]) {
					byte aux = numeros[j];
					numeros[j] = numeros[j-1];
					numeros[j-1] = aux;
				}
			}
		}
		
		return numeros;
	}
	
	public byte[] opcaoA(byte[] caracteres) {
		byte[] novo= new byte[caracteres.length];
		byte[] numeros= this.numerosInvertidos(this.getNumeros(caracteres));
		byte[] letras = this.getLetras(caracteres);
		int cont=0;
		
		for(int i=0; i< 2 ; i++) {
			if(i==0) {
				for(int j=0; j< letras.length; j++) {
					novo[cont++]= letras[j];
				}
			}else {
				for(int j=0; j< numeros.length; j++) {
					novo[cont++]= numeros[j];
				}
			}
		}
		
		return novo;
	}
	
	public byte[] pares(byte[] inteiros) {
		byte[] novo = new byte[inteiros.length];
		int cont=0;

		for(int i=0; i< inteiros.length; i++) {
			if(inteiros[i]%2==0)
				novo[cont++]= inteiros[i];
		}
		
		byte[] trim = new byte[cont];
		System.arraycopy(novo, 0, trim, 0, cont);
		
		return trim;
	}
	
	public int fatorial(byte numero) {
		int fatorial =1;
		int teste = (int)numero;
		
		if(teste==0 || teste ==1)
			return 1;
		
		for(int i=teste; i>0; i--)
			fatorial *=i;
		
		return fatorial;
	}
	
	public byte[] opcaoC(byte[] numeros) {
		for(int i=0; i<numeros.length;i++) {
			numeros[i]= (byte) this.fatorial(numeros[i]);
		}
		
		return numeros;
	}
	
	@Override
	public void run() {
		
		try {
			DataInputStream in = new DataInputStream(cliente.getInputStream());
			DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
			
			byte opcao = in.readByte();
			int legnt = in.read();
			byte[] teste = new byte[legnt];
			in.read(teste, 0, legnt);
//			char[] caracteres = new char[legnt];
//			
//			for(int i=0; i< teste.length;i++) {
//				caracteres[i] = (char) teste[i];//converte byte para char
//			}
			
			byte[] resultado = null;
			
			switch (opcao) {
			case 1:
				resultado = this.opcaoA(teste);
				out.write(resultado.length);
				out.flush();
				out.write(resultado);
				out.flush();
				break;
			case 2:
				resultado = this.sort(this.pares(teste));
				out.write(resultado.length);
				out.flush();
				out.write(resultado);
				out.flush();
				break;
			case 3:
				resultado = this.pares(teste);
				out.write(resultado.length);
				out.flush();
				out.write(resultado);
				out.flush();
				break;
			case 4:
				resultado = this.opcaoC(teste);
				out.write(resultado.length);
				out.flush();
				out.write(resultado);
				out.flush();
				break;
			default:
				break;
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
