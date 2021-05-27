package udpClient;

import com.cj4dplex.test.udpController.Controller;

public class UdpClientMain {

	public static void main(String[] args) {
		UdpClient udpclient = new UdpClient("localhost", 6000);
		Controller controller = new Controller(udpclient);
	}

}
