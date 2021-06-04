package com.cj4dplex.test.udpserver;

import com.cj4dplex.test.udpcontroller.Controller;

public class UdpServerMain {

	public static void main(String[] args) {

		UdpServer udpServer = new UdpServer(6000);
		new Controller(udpServer);

		// }
	}

}
