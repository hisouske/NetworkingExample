package com.cj4dplex.test.function;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.Socket;

public class ClientSend {
	private static String serverIP = "localhost";
	private static byte[] bt = null;

	private static OutputStream outputStream = null;

	private static PrintWriter printWriter = null;

	public static final void UdpSend(String udpMsg, int port, DatagramSocket ds) {
		try {
			bt = udpMsg.getBytes("UTF-8");
			DatagramPacket sendPacket = new DatagramPacket(bt, bt.length, new InetSocketAddress(serverIP, port));
			ds.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// // *이슈 = ClientSend error 서버 종료시에도 클라이언트 send 메서드 실행
	// public static final void TcpSend(String tcpMsg, Socket socket) {
	// try {
	//
	// bt = tcpMsg.getBytes("utf-8");
	// System.out.println("@@Client TcpSend tcpMsg = " + tcpMsg);
	// outputStream = socket.getOutputStream();
	// outputStream.write(bt);
	//
	// // PrintWriter pw = new PrintWriter(tcpMsg);
	// // pw.flush();
	//
	// System.out.println("@@Client TcpSend = " + outputStream.toString());
	// outputStream.flush();
	// } catch (IOException e) {
	// try {
	// outputStream.close();
	// socket.close();
	// System.exit(0);
	// } catch (IOException e1) {
	// e1.printStackTrace();
	// }
	// e.printStackTrace();
	// }
	//
	// }
	public static final void TcpSend(String tcpMsg, Socket socket) {
		try {

			// bt = tcpMsg.getBytes("utf-8");
			System.out.println("@@Client TcpSend tcpMsg = " + tcpMsg);
			printWriter = new PrintWriter(socket.getOutputStream());
			printWriter.println(tcpMsg);

			// PrintWriter pw = new PrintWriter(tcpMsg);
			// pw.flush();

			// System.out.println("@@Client TcpSend = " + outputStream.toString());
			printWriter.flush();
		} catch (IOException e) {

			printWriter.close();
			// socket.close();
			System.exit(0);

			e.printStackTrace();
		}

	}
}
