package com.cj4dplex.test.udpfunction;

import java.net.DatagramPacket;

import com.cj4dplex.test.udpserver.UdpResource;

public class UdpOutClient {
	public UdpOutClient(DatagramPacket outClient) {

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
