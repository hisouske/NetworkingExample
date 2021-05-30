package com.cj4dplex.test.udpclient;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Scanner;

import javax.swing.JTextArea;

import com.cj4dplex.test.function.ClientReceive;
import com.cj4dplex.test.function.ClientSend;

public class UdpClient {

	private DatagramSocket socket = null;
	private int bufferSize = 1024;
	private DatagramPacket datagramPacket = new DatagramPacket(new byte[bufferSize], bufferSize);
	private JTextArea textarea = null;
	private int port = 0;
	public Thread thread = null;

	public UdpClient(String serverIP, int port) {
		try {
			this.port = port;
			socket = new DatagramSocket();
			msgSend("입장");
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void msgSend(String text) {
		ClientSend.UdpSend(text, port, socket);
	}

	public void receiveReady(JTextArea textArea) {
		this.textarea = textArea;
		thread = new Thread(ClientReceive.UdpReceive(socket, datagramPacket, textarea));
		thread.start();
	}

}