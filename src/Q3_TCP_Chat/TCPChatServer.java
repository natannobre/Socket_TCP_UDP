package Q3_TCP_Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPChatServer {

	public static void main(String[] args) {
		try{
			int serverPort = 7896; // the server port
			int counter = 0;
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while(true) {
				
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
				
			}
		} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}

	}

}

class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	public Connection (Socket aClientSocket) {
		try {
			//System.out.println("Criou uma nova conexão");
			clientSocket = aClientSocket;
			in = new DataInputStream( clientSocket.getInputStream());
			out =new DataOutputStream( clientSocket.getOutputStream());
			this.start();
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
	public void run(){
		try {			                 // an echo server
			//System.out.println("Startou a conexão");
			String data = in.readUTF();	                  // read a line of data from the stream
			System.out.println("Mensagem recebida no servidor: " + data);
			
			out.writeUTF(data); // manda de volta a mensagem para o cliente
			
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
		

	}
}

