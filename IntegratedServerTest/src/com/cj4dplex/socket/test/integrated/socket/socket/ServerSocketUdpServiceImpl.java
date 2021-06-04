package com.cj4dplex.socket.test.integrated.socket.socket;

import java.net.DatagramSocket;
import java.net.SocketException;

import com.cj4dplex.socket.test.integrated.socket.client.ClientSocket;
import com.cj4dplex.socket.test.integrated.socket.service.ServerSocketService;

public class ServerSocketUdpServiceImpl implements ServerSocketService {

	private DatagramSocket datagramSocket = null;

	@Override
	public void socketOpen(int port) {
			try {
				datagramSocket = new DatagramSocket(port);
				final ClientSocket clientSocket = new ClientSocket();
				clientSocket.init(datagramSocket);
				clientSocket.performOn();
			} catch (SocketException e) {
				e.printStackTrace();
			}
	}


	@Override
	public void socketClose() {
		if (null == this.datagramSocket) {
			return;
		}
		datagramSocket.close();
	}

}
