package com.cj4dplex.socket.test.integrated.init;

import java.util.ArrayList;
import java.util.List;

import com.cj4dplex.socket.test.integrated.socket.client.ClientSocket;
import com.cj4dplex.socket.test.integrated.socket.service.ServerSocketService;
import com.cj4dplex.socket.test.integrated.socket.socket.ServerSocketTcpServiceImpl;
import com.cj4dplex.socket.test.integrated.socket.socket.ServerSocketUdpServiceImpl;

public class IntegratedSocketInit {

	private volatile static IntegratedSocketInit object = null;

	public static IntegratedSocketInit getInstance() {
		if (object == null) {
			synchronized (IntegratedSocketInit.class) {
				if (object == null) {
					object = new IntegratedSocketInit();
				}
			}
		}
		return object;
	}

	private final List<ClientSocket> clientSockets = new ArrayList<ClientSocket>();
	private final ServerSocketService tcp = new ServerSocketTcpServiceImpl();
	private final ServerSocketService udp = new ServerSocketUdpServiceImpl();

	public ServerSocketService getTcp() {
		return tcp;
	}

	public ServerSocketService getUdp() {
		return udp;
	}

	public List<ClientSocket> getClientSockets() {
		return clientSockets;
	}

}
