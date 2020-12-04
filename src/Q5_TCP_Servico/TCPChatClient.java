package Q5_TCP_Servico;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPChatClient {

	public static void main(String[] args) {
		// arguments supply message and hostname
		String ServerAddr = "127.0.0.1";
		Socket s = null;
		Scanner scan = new Scanner(System.in);
		String mensagem;
		try{
			int serverPort = 7896;
			
			s = new Socket("127.0.0.1", serverPort);
			DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out =new DataOutputStream( s.getOutputStream());
			
			while (true) {
				System.out.println("Para acessar o recurso calculadora, siga o modelo: calculator,{num1}[espaço]{operando}[espaço]{num2}");
				System.out.println("Exemplo: calculator,2 * -5");
				System.out.println("Digite a Mensagem:");
				mensagem = scan.nextLine();
				
				out.writeUTF(mensagem);      	// UTF is a string encoding see Sn. 4.4
				String data = in.readUTF();	    // read a line of data from the stream
				System.out.println("Received: "+ data) ; 
				
			}
		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}

	}

}
