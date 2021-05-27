package com.cj4dplex.test.udpFunc;

import java.io.BufferedReader;
import java.io.IOException;
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

	public static final Runnable TcpReceive(Socket socket, JTextArea textarea) {
		return new Runnable() {
			@Override
			public void run() {
				String msg = null;
				InputStreamReader inputStreamReader = null;
				BufferedReader bufferedReader = null;
				PrintWriter printWriter = null;

				try {
					bufferedReader = new BufferedReader(inputStreamReader);
					inputStreamReader = new InputStreamReader(socket.getInputStream());
					printWriter = new PrintWriter(socket.getOutputStream());
					while ((msg = bufferedReader.readLine()) != null) {
						textarea.append(msg + "\n");
						System.out.println(msg);
					}
				} catch (IOException e) {
					try {
						inputStreamReader.close();
						bufferedReader.close();
						printWriter.close();
						socket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

			}
		};
	}
}