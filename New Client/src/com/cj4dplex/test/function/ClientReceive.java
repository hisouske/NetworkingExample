package com.cj4dplex.test.function;

import java.io.IOException;
import java.io.InputStream;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
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
				int size = 0;
				try {
					while (-1 != (size = inputStream.read(bt))) {
						// size = inputStream.read(bt);
						String output = new String(bt, 0, size, "UTF-8");
//						if (output.equals("#exitServer#")) {
//							System.out.println("exitServer");
//							inputStream.close();
//							break;
//						}
						System.out.println("*clientReceive output");
						System.out.println(output);
						textArea.append(output + "\n");
					}
				} catch (IOException e) {
					try {
						System.out.println("서버종료되었습니다");
						textArea.append("서버종료되었습니다" + "\n");
						inputStream.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
			}
		};
	}
}
