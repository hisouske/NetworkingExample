package com.cj4dplex.test.ufunction;

import java.net.DatagramPacket;

import com.cj4dplex.test.udpserver.UdpResource;

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
