package com.cj4dplex.socket.test.client.interf;

import java.net.Socket;

public interface ConnectClient {

	public void init(int port);

	public void Receive();

	public void Send(String msg);
}
