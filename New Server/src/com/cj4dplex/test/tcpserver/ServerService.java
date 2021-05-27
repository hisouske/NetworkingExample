package com.cj4dplex.test.tcpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JTextArea;

import com.cj4dplex.test.function.ServerReceive;
import com.cj4dplex.test.serverif.ServerInterface;

public class ServerService {
	private int clientNum;
	private Socket socket = null;

	private JTextArea textArea = null;
	private JTextArea userArea = null;

	private Thread thread = null;
	// private int timeout = 3000;
	private InputStream inputStream = null;

	public ServerService(int cNum) throws IOException {
		this.clientNum = cNum;
		this.socket = ServerResource.getInstance().getClientList().get(clientNum);
		// socket.setSoTimeout(timeout);
		// 지정 된 시간만큼 기다렸다가 Exception으로 빠짐

		inputStream = socket.getInputStream();

	}

	public void receiveReady(JTextArea textArea) {
		this.textArea = textArea;
		thread = new Thread(ServerReceive.TcpReceive(inputStream, textArea));
		thread.start();

	}

}
