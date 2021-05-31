package com.cj4dplex.test.udpclient;

import com.cj4dplex.test.udpcontroller.Controller;

public class UdpClientMain {

	public static void main(String[] args) {
		//udpcontroller, udpserver 패키지 대문자 변경확인 20210531
		UdpClient udpclient = new UdpClient("localhost", 6000);
		Controller controller = new Controller(udpclient);
	}

}
