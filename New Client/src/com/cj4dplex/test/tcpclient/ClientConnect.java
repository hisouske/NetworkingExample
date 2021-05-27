package com.cj4dplex.test.tcpclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.cj4dplex.test.clientif.ClientInterface;
import com.cj4dplex.test.tcontroller.Controller;

public class ClientConnect {
	private Socket socket = null;
	private ClientService clientservice = null;

	public ClientConnect() throws IOException {

		try {
			if (null == socket) {
				socket = new Socket();
				socket.connect(new InetSocketAddress("localhost", 8889));
				clientservice = new ClientService(socket);
				Controller controller = new Controller(clientservice);
			}

		} catch (ConnectException ex) {
			System.out.println("Connection Failed");

		}

	}

}
