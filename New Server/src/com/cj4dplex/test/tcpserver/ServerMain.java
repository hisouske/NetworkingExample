package com.cj4dplex.test.tcpserver;

import com.cj4dplex.test.sconnect.ServerConnect;

public class ServerMain {

	public static void main(String[] args) {

		ServerConnect serverConnect = new ServerConnect();
		serverConnect.start();
	}

}
