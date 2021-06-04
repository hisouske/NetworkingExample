package com.cj4dplex.socket.test.client.connect;

import com.cj4dplex.socket.test.client.integrated.ConnectTcpServiceImpl;
import com.cj4dplex.socket.test.client.integrated.ConnectUdpServiceImpl;
import com.cj4dplex.socket.test.client.interf.ConnectClient;

public class ClientResouce {
	private volatile static ClientResouce object = null;

	public static ClientResouce getInstance() {
		if (object == null) {
			synchronized (ClientResouce.class) {
				if (object == null) {
					object = new ClientResouce();
				}
			}
		}

		return object;
	}

	private final ConnectClient tcp = new ConnectTcpServiceImpl();
	private final ConnectClient udp = new ConnectUdpServiceImpl();

	public ConnectClient getTcp() {
		return tcp;
	}

	public ConnectClient getUdp() {
		return udp;
	}

}
