package com.cj4dplex.socket.test.integrated.socket.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.cj4dplex.socket.test.integrated.init.IntegratedSocketInit;
import com.cj4dplex.socket.test.integrated.socket.client.ClientSocket;
import com.cj4dplex.socket.test.integrated.socket.service.ServerSocketService;

public class ServerSocketTcpServiceImpl implements ServerSocketService {
	private ServerSocket serverSocket = null;
	private Thread thread = null;

	@Override
	public void socketOpen(int port) {
		try {
			this.serverSocket = new ServerSocket(port);
			this.thread = new Thread(this.makeRunnable());
			thread.setDaemon(true);
			thread.setName("TCP Server Socket Thread");
			thread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private final Runnable makeRunnable() {
		return new Runnable() {
			@Override
			public void run() {

				while (!Thread.currentThread().isInterrupted()) {
					try {
						final Socket socket = serverSocket.accept();
						final ClientSocket clientSocket = new ClientSocket();
						clientSocket.init(socket);
						clientSocket.performOn();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		};

	}

	@Override
	public void socketClose() {
		if (null == this.serverSocket) {
			return;
		}
		final List<ClientSocket> clientSockets = IntegratedSocketInit.getInstance().getClientSockets();

		if (0 < clientSockets.size()) {
			for (int i = 0; i < clientSockets.size(); i++) {
				final ClientSocket clientSocket = clientSockets.get(i);
				clientSocket.performOff();
			}
		}

		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		do {
			thread.interrupt();
		} while (null != thread && thread.isAlive());
		;

	}

}
