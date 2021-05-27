package com.cj4dplex.test.ufunction;

import java.net.DatagramPacket;
import java.util.ArrayList;

import com.cj4dplex.test.udpserver.UdpResource;
import com.cj4dplex.test.udpserver.UdpServer;

public class InClient {

	public InClient(DatagramPacket receivePacket) {

		boolean flag = true;

		// if (UdpResource.getInstance().getClientList().contains(newClient)) {
		// System.out.println("### 중복된 IP & Port ###");
		// flag = false;
		// } else {
		// UdpResource.getInstance().getClientList().add(newClient);
		// }

		for (ClientVO client : UdpResource.getInstance().getClientList()) {
			if (client.getPort() == receivePacket.getPort() && receivePacket.getAddress() == client.getAddress()) {
				System.out.println("### 중복된 IP & Port ###");
				flag = false;
				break;
			}

		}
		if (flag) {
			ClientVO newClient = new ClientVO();
			newClient.setClient(receivePacket.getAddress(), receivePacket.getPort());

			UdpResource.getInstance().getClientList().add(newClient);

			System.out.println(UdpResource.getInstance().getClientList().size());

			UpdateClientList.getInstance().listUpdate();
		}

	}

}
