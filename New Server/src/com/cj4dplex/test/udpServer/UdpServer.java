package com.cj4dplex.test.udpserver;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import javax.swing.JTextArea;

import com.cj4dplex.test.function.ServerReceive;

public class UdpServer {

	private DatagramSocket socket = null;
	private int bufferSize = 1024;
	private DatagramPacket datagramPacket = new DatagramPacket(new byte[bufferSize], bufferSize);
	public Thread thread = null;

	public UdpServer(int port) {

		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void receiveReady(JTextArea textarea) {
		thread = new Thread(ServerReceive.UdpReceive(socket, datagramPacket,textarea));
		thread.start();

	}

}
