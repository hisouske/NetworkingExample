package com.cj4dplex.test.udpFunc;

import java.net.InetAddress;

import com.cj4dplex.test.server.ServerResource;

public class ClientVO {
	private InetAddress address;
	private int port;


	public void setClient(InetAddress address, int port) {
		this.address = address;
		this.port = port;
	}

	
	public InetAddress getAddress() {
		return address;
	}

	public int getPort() {
		return port;
	}

}
