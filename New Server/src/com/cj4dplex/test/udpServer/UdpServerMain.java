package com.cj4dplex.test.udpserver;



import com.cj4dplex.test.ucontroller.Controller;

public class UdpServerMain {

	public static void main(String[] args) {

		UdpServer udpServer = new UdpServer(6000);
		Controller controller = new Controller(udpServer);
		
		
//		}
	}

}
