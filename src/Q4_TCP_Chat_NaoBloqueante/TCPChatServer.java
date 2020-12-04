package Q4_TCP_Chat_NaoBloqueante;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPChatServer {
	
	private static ArrayList<Connection> listConnection;

	public static void main(String[] args) {
		
		try{
			int serverPort = 7896; // the server port
			
			listConnection = new ArrayList<Connection>();
			System.out.println("Servido Iniciado");
			
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while( true ) {
				
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
				System.out.println("Cliente " + c.getIdThread() + " inicializado");
				c.start();
				listConnection.add(c);
				System.out.println("Threads: " + listConnection.size());
				
			}
		} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}

	}

}

class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	private boolean running = false;
	private static int contador = 0;
	private int idThread = 0;
	public Connection (Socket aClientSocket) {
		try {
			//System.out.println("Criou uma nova conexão");
			clientSocket = aClientSocket;
			in = new DataInputStream( clientSocket.getInputStream());
			out =new DataOutputStream( clientSocket.getOutputStream());
			contador++;
			this.idThread = contador;
			
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
	
	public int getIdThread() {
		return idThread;
	}

	public void run(){
		this.running = true;
		try {			                 // an echo server
			
			while(true) {
				//System.out.println("Startou a conexão");
				String data = in.readUTF();	                  // read a line of data from the stream
				System.out.println("Cliente " + this.idThread + " : " + data);
				
				out.writeUTF("Mensagem recebida no servidor: " + data); // manda de volta a mensagem para o cliente
			}
			
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
		

	}
}

