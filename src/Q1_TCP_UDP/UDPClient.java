package Q1_TCP_UDP;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class UDPClient {

	public static void main(String[] args) {
		// args give message contents and destination hostname
		DatagramSocket aSocket = null;
		String addr = "127.0.0.1";
		String data;
		Scanner scan = new Scanner(System.in);
		try {
			System.out.println("Digite a mensagem:");
			data = scan.nextLine();
			aSocket = new DatagramSocket();    
			byte [] m = data.getBytes();
			InetAddress aHost = InetAddress.getByName(addr);
			int serverPort = 6789;		                                                 
			DatagramPacket request =
			 	new DatagramPacket(m,  data.length(), aHost, serverPort);
			aSocket.send(request);			                        
			byte[] buffer = new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);	
			aSocket.receive(reply);
			System.out.println("Reply: " + new String(reply.getData()));	
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e){System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}

	}

}
