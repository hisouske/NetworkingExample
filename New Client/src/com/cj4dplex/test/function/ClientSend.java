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
			bt = tcpMsg.getBytes("UTF-8");
			System.out.println("@@Client TcpSend = "+bt);
			outputStream.write(bt);
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
