package com.cj4dplex.test.function;

import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.Socket;

import com.cj4dplex.test.tcpserver.ServerResource;

public class Send {
	private static String serverIP = "localhost";
	private static byte[] bt = null;

	public static final void UdpSend(String udpMsg, int port, DatagramSocket ds) {
		try {
			bt = udpMsg.getBytes("UTF-8");
			DatagramPacket sendPacket = new DatagramPacket(bt, bt.length, new InetSocketAddress(serverIP, port));
			ds.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	
//	public static final void TcpSend(String Msg,int port) {
//	
//	try {
//		MulticastSocket ms = new MulticastSocket();
//		InetAddress ia = InetAddress.getByName(serverIP);
//		DatagramPacket dp = new DatagramPacket(Msg.getBytes(), Msg.getBytes().length, ia, port);
//		// 공용 ip로 데이터 보내기
//		ms.send(dp);
//		System.out.println("보내기 완료");
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	}
	public static final void TcpSend(String tcpMsg, Socket socket) {
		OutputStream outputStream = null;
		try {
			outputStream = socket.getOutputStream();
			System.out.println("@@Server TcpSend >>" + tcpMsg);
			bt = tcpMsg.getBytes("UTF-8");
			outputStream.write(bt);
			outputStream.flush();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
