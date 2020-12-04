package Q2_TCP_Calculator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPCalculatorServer {

	public static void main(String[] args) {
		try{
			int serverPort = 7896; // the server port
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while(true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
			}
		} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}

	}

}

class Connection extends Thread {
	String sVetor[] = new String[3];
	String resposta = null;
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	public Connection (Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new DataInputStream( clientSocket.getInputStream());
			out =new DataOutputStream( clientSocket.getOutputStream());
			this.start();
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
	public void run(){
		try {			                 				// an echo server
			String data = in.readUTF();	                // read a line of data from the stream
			sVetor = data.split(" ");
			resposta = calculator(sVetor);
			System.out.println("Mensagem recebida no servidor: " + sVetor[0] + sVetor[1] + sVetor[2] + " = " + resposta);
			out.writeUTF(resposta); // manda de volta a mensagem para o cliente
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
		

	}
	public String calculator(String array[]) {
		float num01 = Float.parseFloat(array[0]);
		float num02 = Float.parseFloat(array[2]);
		float f_Resposta = 0;
		String op = array[1];
		String s_Resposta = null;

		switch (op) {
			case "+":
				f_Resposta = num01 + num02;
				break;
			case "-":
				f_Resposta = num01 - num02;
				break;
			case "*":
				f_Resposta = num01 * num02;
				break;
			case "/":
				f_Resposta = num01 / num02;
				break;
			default:
				System.out.println("Operando Inválido");
		}
		
		s_Resposta = String.valueOf(f_Resposta);
		
		return s_Resposta;
	}
}
