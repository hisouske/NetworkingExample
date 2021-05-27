package com.cj4dplex.test.function;

import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientSend {
	private static String serverIP = "localhost";
	private static byte[] bt = null;
	static String decode = "";
	
	public static final void UdpSend(String udpMsg, int port, DatagramSocket ds) {
		try {
			bt = udpMsg.getBytes("UTF-8");
			DatagramPacket sendPacket = new DatagramPacket(bt, bt.length, new InetSocketAddress(serverIP, port));
			ds.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static final void TcpSend(String tcpMsg, OutputStream outputStream) {
		try {
			
			
			bt = tcpMsg.getBytes("utf-8");
			System.out.println("@@Client TcpSend tcpMsg = "+tcpMsg);
			
			for(int i =0; i<bt.length; i++) {
				System.out.print("@@Client TcpSend bt = "+bt[i]+ " ");
			}
			//decode = new String(bt, "UFT-8");
		
			//System.out.println("@@decode = "+decode);
			
			outputStream.write(bt);
			System.out.println("@@Client TcpSend = "+outputStream.toString());
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
