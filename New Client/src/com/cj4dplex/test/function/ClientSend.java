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
	private static String decode = "";
	private 	static OutputStream outputStream = null;

	public static final void UdpSend(String udpMsg, int port, DatagramSocket ds) {
		try {
			bt = udpMsg.getBytes("UTF-8");
			DatagramPacket sendPacket = new DatagramPacket(bt, bt.length, new InetSocketAddress(serverIP, port));
			ds.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// *이슈 = ClientSend error 서버 종료시에도 클라이언트 send 메서드 실행
	public static final void TcpSend(String tcpMsg, Socket socket) {
		try {
			//3hand에서 파이프에 문제 체크
			bt = tcpMsg.getBytes("utf-8");
			System.out.println("@@Client TcpSend tcpMsg = "+tcpMsg);
			
			for(int i =0; i<bt.length; i++) {
				System.out.print("@@Client TcpSend bt = "+bt[i]+ " ");
			}
			outputStream = socket.getOutputStream();
			
			outputStream.write(bt);

			System.out.println("@@Client TcpSend = "+outputStream.toString());
			outputStream.flush();
		} catch (IOException e) {
			try {
				outputStream.close();
				socket.close();
				System.exit(0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}
}
