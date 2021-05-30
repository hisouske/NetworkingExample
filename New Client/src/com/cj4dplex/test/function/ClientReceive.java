package com.cj4dplex.test.function;

import java.io.IOException;
import java.io.InputStream;

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

	public static final Runnable TcpReceive(Socket socket, JTextArea textArea) {
		return new Runnable() {
			@Override
			public void run() {
				byte[] bt = new byte[256];
				int size = 0;
				InputStream inputStream = null;

				try {
					inputStream = socket.getInputStream();
					while (-1 != (size = inputStream.read(bt))) {
						// size = inputStream.read(bt);

						System.out.println(inputStream);

						String output = new String(bt, 0, size, "UTF-8");
						// if (output.equals("#exitServer#")) {
						// System.out.println("exitServer");
						// inputStream.close();
						// break;
						// }
						System.out.println("*clientReceive output");
						if(output.equals("exit()")) {
							break;
						}
						System.out.println(output);
						textArea.append(output + "\n");
					}
					System.out.println("*************종료***********");
					textArea.append("서버종료되었습니다" + "\n");
					inputStream.close();
					socket.close();
					System.exit(0);
				} catch (IOException e) {
					System.out.println("*ClientReceive IOException check 1");
					System.exit(0);
					try {
						System.out.println("서버종료되었습니다");
						textArea.append("서버종료되었습니다" + "\n");
						inputStream.close();
						socket.close();
						System.exit(0);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
			}
		};
	}
}
