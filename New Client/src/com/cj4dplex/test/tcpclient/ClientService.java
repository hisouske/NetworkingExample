package com.cj4dplex.test.tcpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JTextArea;

import com.cj4dplex.test.clientif.ClientInterface;
import com.cj4dplex.test.function.ClientReceive;
import com.cj4dplex.test.function.ClientSend;

public class ClientService {
	private PrintWriter printWriter = null;
	private Socket socket = null;
//	private OutputStream outputStream = null;
//	private InputStream inputStream = null;
	public Thread thread = null;
	private JTextArea textarea = null;

	public ClientService(Socket s) throws IOException {
		this.socket = s;
//		outputStream = socket.getOutputStream();
//		inputStream = socket.getInputStream();
		// try {

	}


	public void msgSend(String text) {
		boolean check = socket.isConnected();
		System.out.println("!!서버에 접속 확인.");
		System.out.println(check);
		if (check == false) {
			System.out.println("!!서버에 접속 할 수 없습니다. 확인바랍니다.");
			System.exit(0);
		}
		ClientSend.TcpSend(text, socket);
	}


	public void receiveReady(JTextArea textArea) {
		this.textarea = textArea;
		thread = new Thread(ClientReceive.TcpReceive(socket, textArea));
		thread.start();
		
		

	}

}
