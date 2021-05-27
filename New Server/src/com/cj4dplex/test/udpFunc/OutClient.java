package com.cj4dplex.test.udpFunc;

import java.net.DatagramPacket;

import com.cj4dplex.test.udpServer.UdpResource;

public class OutClient {
	public OutClient(DatagramPacket outClient) {

		for (ClientVO client : UdpResource.getInstance().getClientList()) {
			if (client.getPort() == outClient.getPort() && outClient.getAddress() == client.getAddress()) {

				UdpResource.getInstance().getClientList().remove(client);
				System.out.println("### List 에서 제거 ###");

				UpdateClientList.getInstance().listUpdate();
				break;
			}

		}

	}
}
