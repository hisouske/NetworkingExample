package com.cj4dplex.test.udpclient;

import com.cj4dplex.test.udpcontroller.Controller;

public class UdpClientMain {

	public static void main(String[] args) {
		UdpClient udpclient = new UdpClient("localhost", 6000);
		Controller controller = new Controller(udpclient);
	}

}
