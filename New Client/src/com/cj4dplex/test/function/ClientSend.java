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
			boolean check1 = false;
			boolean check2 = false;
			boolean check3 = false;
			boolean check4 = false;
			boolean check5 = false;
			
			check1 = socket.isBound();
			check2 = socket.isClosed();
			check3 = socket.isConnected();
			check4 = socket.isInputShutdown();
			check5 = socket.isOutputShutdown();
			System.out.println("!!! 1T ="+check1+" 2F ="+check2+" 3T = "+check3+" 4F = "+check4+" 5F = "+check5);
			
			
			bt = tcpMsg.getBytes("utf-8");
			System.out.println("@@Client TcpSend tcpMsg = "+tcpMsg);
			
			for(int i =0; i<bt.length; i++) {
				System.out.print("@@Client TcpSend bt = "+bt[i]+ " ");
			}
			outputStream = socket.getOutputStream();
			check1 = socket.isBound();
			check2 = socket.isClosed();
			check3 = socket.isConnected();
			check4 = socket.isInputShutdown();
			check5 = socket.isOutputShutdown();
			System.out.println("@@@ 1T ="+check1+" 2F ="+check2+" 3T = "+check3+" 4F = "+check4+" 5F = "+check5);
			
			outputStream.write(bt);
			
			check1 = socket.isBound();
			check2 = socket.isClosed();
			check3 = socket.isConnected();
			check4 = socket.isInputShutdown();
			check5 = socket.isOutputShutdown();
			System.out.println("### 1T ="+check1+" 2F ="+check2+" 3T = "+check3+" 4F = "+check4+" 5F = "+check5);
			System.out.println("@@Client TcpSend = "+outputStream.toString());
			outputStream.flush();
		} catch (IOException e) {
			//작업 이력 있으므로 생략
			try {
				outputStream.close();
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}
}
