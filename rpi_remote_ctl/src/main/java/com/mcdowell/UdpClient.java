///////////////////////////////////////////////////
//        UPD Bidirectional Communication Client //
//        Dev: Jeffrey J. McDowell               //
//        Ver: 1.0                               //
///////////////////////////////////////////////////

package com.mcdowell;
import java.io.IOException; 
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress; 
import java.util.Scanner; 

public class UdpClient{
	
	String destIP;
	int destPort;
	InetAddress ip;
	byte[] buffer;
	DatagramSocket dgSocket;
	DatagramPacket dpSend;
	enum MachineState {START, READY, STANDBY, STATUS, LAUNCH, RELOAD, QUIT};

	UdpClient(){
		buffer = null;
		try{ip = InetAddress.getByName(destIP);
			dgSocket = new DatagramSocket();
		}catch(Exception e){
			System.out.println(e.fillInStackTrace());
		}
	}
	UdpClient(String destIP, int destPort){
		this.destIP = destIP;
		this.destPort = destPort;
		buffer = null;
		try{ip = InetAddress.getByName(destIP);
			dgSocket = new DatagramSocket();
		}catch(Exception e){
			System.out.println(e.fillInStackTrace());
		}
	}

	//Class Methods
	public void connect() throws Exception{
		Scanner input = new Scanner(System.in);
		while (true){ 
			String sendCMD = input.nextLine(); 			
			buffer = sendCMD.getBytes(); //string to byte array
		
			dpSend = new DatagramPacket(buffer, buffer.length, ip, destPort); 		
			dgSocket.send(dpSend);
			
			if (sendCMD.equals("q")) // exit on 'q' input by user
				break; 
		}
		input.close();		
	}


	public static void main(String args[]) throws IOException 
	{ 

		//Instantiate GUI and get user input for destIP and destPort

		UdpClient client = new UdpClient("192.168.0.10", 4001);
		try{
			client.connect();
		}catch(Exception e){
			System.out.println(e.fillInStackTrace());
		}
		
		 
	} 
} 
