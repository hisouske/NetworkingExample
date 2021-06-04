package com.cj4dplex.socket.test.client.integrated;

import java.awt.Color;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

import com.cj4dplex.socket.test.client.interf.ConnectClient;

public class ConnectUdpServiceImpl implements ConnectClient {
	private DatagramSocket datagramSocket = null;
	private DatagramPacket datagramPacket = null;
	private int port = 0;
	private String address = "localhost";
	private Thread thread = null;
	@Override
	public void init(int port) {
		try {
			this.port = port;
			this.datagramSocket = new DatagramSocket();
		} catch (SocketException e) {
			System.out.println("UDP Server Connection Failed");
			e.printStackTrace();
		}
	}

	@Override
	public void Send(String msg) {
		try {
			System.out.println(msg);
			byte[] bt = msg.getBytes();
			datagramPacket = new DatagramPacket(bt, bt.length, new InetSocketAddress(address, port));
			datagramSocket.send(datagramPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Receive() {
		final Runnable receive = new Runnable() {
			@Override
			public void run() {
				final StringBuffer stringBuffer = new StringBuffer();
				while (!Thread.currentThread().isInterrupted()) {
					final byte[] oneByte = new byte[1024];
					final DatagramPacket datagramPacket = new DatagramPacket(oneByte, 0, oneByte.length);

					stringBuffer.setLength(0);
					try {
						datagramSocket.receive(datagramPacket);
						do {
							String getData = new String(datagramPacket.getData());
							stringBuffer.append(getData);
							System.out.println(stringBuffer);

						} while ("\\n".equals(new String(datagramPacket.getData())));

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		};
		this.thread = new Thread(receive, "UDP Client Receive Thread");
		this.thread.setDaemon(true);
		thread.start();
	}

}
