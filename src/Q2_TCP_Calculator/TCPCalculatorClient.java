package Q2_TCP_Calculator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPCalculatorClient {

	public static void main(String[] args) {
		// arguments supply message and hostname
		Socket s = null;
		Scanner scan = new Scanner(System.in);
		String mensagem;
		try{
			int serverPort = 7896;
			System.out.println("Digite a operação(MODELO: {N1}[espaço]{operando}[espaço]{N2):");
			mensagem = scan.nextLine();
			s = new Socket("127.0.0.1", serverPort);    
			DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out =new DataOutputStream( s.getOutputStream());
			out.writeUTF(mensagem);      	// UTF is a string encoding see Sn. 4.4
			String data = in.readUTF();	    // read a line of data from the stream
			System.out.println("Resultado: "+ data) ; 
		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}

	}

}
