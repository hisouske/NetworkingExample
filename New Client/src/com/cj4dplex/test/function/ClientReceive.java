package com.cj4dplex.test.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import javax.swing.JTextArea;

public class ClientReceive {

	public static final Runnable UdpReceive(DatagramSocket socket, DatagramPacket receivePacket, JTextArea textarea) {

		return new Runnable() {
			private String message = null;

			@Override
			public void run() {

				while (true) {
					try {
						socket.receive(receivePacket);
						message = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
						textarea.append(message + "\n");
						System.out.println(message);
					} catch (IOException e) {
						socket.close();
						e.printStackTrace();
					}
				}

			}
		};
	}

	public static final Runnable TcpReceive(InputStream inputStream, JTextArea textArea) {
		return new Runnable() {
			@Override
			public void run() {
				byte[] bt = new byte[256];
				int size = -1;
				try {
					while ((size = inputStream.read()) != -1) {

						size = inputStream.read(bt);
						String output = new String(bt, 0, size, "UTF-8");
						System.out.println(output);
						textArea.append(output+"\n");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
	}
}
