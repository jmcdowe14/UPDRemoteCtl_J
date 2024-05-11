///////////////////////////////////////////////////
//        UPD Bidirectional Communication Server //
//        Dev: Jeffrey J. McDowell               //
//        Ver: 1.0                               //
///////////////////////////////////////////////////

package com.mcdowel;
import java.io.IOException; 
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
// import java.net.InetAddress; 
import java.net.SocketException; 

public class UdpServer{
	
	int LPort = 4001;
	int BufferSize = 65535;
	DatagramSocket dgSocket;
	DatagramPacket dpReceive;
	byte[] receive;

	UdpServer(){
		try{dgSocket = new DatagramSocket(LPort); 
		}catch(SocketException e){
			System.out.println(e.fillInStackTrace());
		}		
		receive = new byte[BufferSize];
		dpReceive = null;
	}	
	UdpServer(int LPort, int BufferSize){
		this.LPort = LPort;
		this.BufferSize = BufferSize;
		try{dgSocket = new DatagramSocket(LPort); 
		}catch(SocketException e){
			System.out.println(e.fillInStackTrace());
		}		
		receive = new byte[BufferSize];
		dpReceive = null; 
	}

	//Class Methods
	public StringBuilder data(byte[] a){ 
		if (a == null) 
			return null; 
		StringBuilder ret = new StringBuilder(); 
		int i = 0; 
		while (a[i] != 0) 
		{ 
			ret.append((char) a[i]); 
			i++; 
		} 
		return ret; 
	}

	public void connect(){
		while (true){ 
            dpReceive = new DatagramPacket(receive, receive.length);
			try{
				dgSocket.receive(dpReceive);
				System.out.println("Client: " + data(receive));			
				if (data(receive).toString().equals("q")) 
				{ 
					System.out.println("Closing Connection..."); 
					break; 
				} 			
				receive = new byte[65535]; 
			}catch(IOException e){
				System.out.println(e.fillInStackTrace());
			} 			
		} 
	}


	public static void main(String[] args) throws IOException{ 

		UdpServer server = new UdpServer();
		server.connect();
	} 
	
	 
} 

