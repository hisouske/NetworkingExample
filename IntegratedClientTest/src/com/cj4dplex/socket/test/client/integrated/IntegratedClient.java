package com.cj4dplex.socket.test.client.integrated;

import com.cj4dplex.socket.test.client.connect.ClientResouce;
import com.cj4dplex.socket.test.client.interf.ConnectClient;

public class IntegratedClient {
	private ConnectClient UdpClient = ClientResouce.getInstance().getUdp();
	private ConnectClient TcpClient = ClientResouce.getInstance().getTcp();

//	public IntegratedClient() {
//
//	}

}
