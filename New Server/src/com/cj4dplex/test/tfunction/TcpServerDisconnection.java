package com.cj4dplex.test.tfunction;

import java.io.IOException;

import com.cj4dplex.test.tcpserver.ServerResource;

public class TcpServerDisconnection {
	public TcpServerDisconnection() {

		for (Integer i : ServerResource.getInstance().getClientList().keySet()) {
			try {
				ServerResource.getInstance().getClientList().get(i).close();
			} catch (IOException e) {
			}
		}
	}
}
