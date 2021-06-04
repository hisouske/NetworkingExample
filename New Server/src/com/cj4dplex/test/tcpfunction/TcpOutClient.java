package com.cj4dplex.test.tcpfunction;

import java.io.IOException;

import com.cj4dplex.test.tcpserver.ServerResource;

public class TcpOutClient {
	public TcpOutClient(int outClientNum) {

	
try {
	ServerResource.getInstance().getClientList().get(outClientNum).close();
	ServerResource.getInstance().getClientList().remove(outClientNum);
	
} catch (IOException e) {
	e.printStackTrace();
}


	}
}
