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
	private InputStreamReader inputStreamReader = null;
	private OutputStream outputStream = null;
	private InputStream inputStream = null;
	private BufferedReader bufferedReader = null;
	public Thread thread = null;
	private JTextArea textarea = null;
	// public MsgRecieveCallable callable = null;
	// public ExecutorService executor = null;
	// public Future<String> future = null;

	public ClientService(Socket s) throws IOException {
		this.socket = s;
		outputStream = socket.getOutputStream();
		inputStream = socket.getInputStream();
		// try {
		// this.inputStreamReader = new InputStreamReader(socket.getInputStream());
		// this.bufferedReader = new BufferedReader(inputStreamReader);
		// this.printWriter = new PrintWriter(socket.getOutputStream());
		//
		//// this.thread = new Thread(this.msgReceive(), "TCP Client Receive
		// MessageThread");
		//// thread.start();
		// } catch (IOException e) {
		//
		//
		// e.printStackTrace();
		// }

		// this.printWriter = new PrintWriter(socket.getOutputStream());
		// this.callable = new MsgRecieveCallable(bufferedReader);
		// this.executor = Executors.newSingleThreadExecutor();
	}

	// @Override
	// public void stopClient() throws IOException {
	//
	// if (null != inputStreamReader) {
	// inputStreamReader.close();
	// }
	// if (null != printWriter) {
	// printWriter.close();
	// }
	// if (null != bufferedReader) {
	// bufferedReader.close();
	// }
	// if (null != socket) {
	// socket.close();
	// socket = null;
	// }
	// if (null != inThread) {
	// try {
	// inThread.sleep(1000);
	// } catch (InterruptedException e) {
	// inThread = null;
	// e.printStackTrace();
	// }
	// }
	// }

	// @Override
	// public Runnable msgReceive() {
	// return new Runnable() {
	// @Override
	// public void run() {
	//
	// String msg = null;
	// try {
	// while ((msg = bufferedReader.readLine()) != null) {
	//
	// textarea.append(msg + "\n");
	// System.out.println(msg);
	// }
	// } catch (IOException e) {
	// try {
	// stopClient();
	// } catch (IOException e1) {
	// e1.printStackTrace();
	// }
	// }
	//
	// }
	//
	// };
	//
	// }

	// @Override
	// public void msgSendTCP(String msg) {
	// if (null != printWriter) {
	// System.out.println("msgSendTCP >>"+msg);
	//
	// printWriter.write(msg + "\n");
	// printWriter.flush();
	// }
	// }
	public void msgSend(String text) {
		ClientSend.TcpSend(text, outputStream);
	}

	// @Override
	// public void setTextArea(JTextArea textarea) {
	// this.textarea = textarea;
	// }
	public void receiveReady(JTextArea textArea) {
		this.textarea = textArea;
		thread = new Thread(ClientReceive.TcpReceive(inputStream, textArea));
		thread.start();
	}

}
