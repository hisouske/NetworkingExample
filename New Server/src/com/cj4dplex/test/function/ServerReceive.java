package com.cj4dplex.test.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import javax.swing.JTextArea;

import com.cj4dplex.test.tcpserver.ServerResource;
import com.cj4dplex.test.udpserver.UdpResource;
import com.cj4dplex.test.ufunction.ClientVO;
import com.cj4dplex.test.ufunction.InClient;
import com.cj4dplex.test.ufunction.OutClient;

public class ServerReceive {

	public static final Runnable UdpReceive(DatagramSocket socket, DatagramPacket receivePacket, JTextArea textarea) {
		return new Runnable() {
			private String message = null;
			private OutClient outClient = null;
			InClient inClient = null;

			@Override
			public void run() {
				while (true) {
					try {
						socket.receive(receivePacket);
						message = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");

						if (message.equals("#exit#")) {
							outClient = new OutClient(receivePacket);
						} else {

							inClient = new InClient(receivePacket);
							textarea.append(message + "\n");
							System.out.println("@@ServerReceive" + message);

							for (ClientVO client : UdpResource.getInstance().getClientList()) {
								Send.UdpSend(message, client.getPort(), socket);
							}

						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		};
	}

	public static final Runnable TcpReceive(InputStream inputStream, JTextArea textarea) {
		return new Runnable() {
			@Override
			public void run() {
				int size = -1;
				OutputStream outputStream = null;
				try {
					while ((size = inputStream.read()) != -1) {
						byte[] bt = new byte[256];

						size = inputStream.read(bt);
						String output = new String(bt, 0, size, "UTF-8");
						System.out.println(output);
						textarea.append(output+"\n");
						for (Integer i : ServerResource.getInstance().getClientList().keySet()) {

							outputStream = ServerResource.getInstance().getClientList().get(i).getOutputStream();
							Send.TcpSend(output, outputStream);
						}

					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
	}
}
