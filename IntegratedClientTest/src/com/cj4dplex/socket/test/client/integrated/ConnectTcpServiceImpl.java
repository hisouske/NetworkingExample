package com.cj4dplex.socket.test.client.integrated;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.cj4dplex.socket.test.client.controller.ClientController;
import com.cj4dplex.socket.test.client.interf.ConnectClient;

public class ConnectTcpServiceImpl implements ConnectClient {
	private Socket socket = null;
	private Thread thread = null;

	@Override
	public void init(int port) {
		try {
			this.socket = new Socket();
			socket.connect(new InetSocketAddress("localhost", port));

		} catch (IOException e1) {
			System.out.println("TCP Server Connection Failed");
			try {
				Thread.sleep(5000);
				socket.close();
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void Receive() {
		final Runnable receive = new Runnable() {
			@Override
			public void run() {

				try {
					final BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(socket.getInputStream()));
					while (!Thread.currentThread().isInterrupted()) {
						final String message = bufferedReader.readLine();
						System.out.println("Server :" + message);

						new ClientController().messageView(Color.black, 11, message);

					}

				} catch (IOException e) {
					try {
						Thread.sleep(500);
						Thread.interrupted();
						System.exit(0);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
			}
		};
		this.thread = new Thread(receive);
		this.thread.setDaemon(true);
		thread.setName("Tcp");
		thread.start();
	}

	@Override
	public void Send(String msg) {
		try {
			System.out.println(msg);
			final PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
			printWriter.println(msg);
			printWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
