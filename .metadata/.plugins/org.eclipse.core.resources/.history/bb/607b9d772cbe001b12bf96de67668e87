package com.cj4dplex.test.inoutmsg;

import java.io.BufferedReader;
import java.io.IOException;
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

public class ClientService implements ClientInterface {
	private PrintWriter printWriter = null;
	private Socket socket = null;
	private InputStreamReader inputStreamReader = null;
	private BufferedReader bufferedReader = null;
	private Thread inThread = null;
	private JTextArea textarea = null;
	// public MsgRecieveCallable callable = null;
	// public ExecutorService executor = null;
	// public Future<String> future = null;

	public ClientService(Socket s) throws IOException {
		this.socket = s;
		try {
			this.inputStreamReader = new InputStreamReader(socket.getInputStream());
			this.bufferedReader = new BufferedReader(inputStreamReader);
			this.printWriter = new PrintWriter(socket.getOutputStream());

			this.inThread = new Thread(this.msgReceive(), "TCP Client Receive MessageThread");
			inThread.start();
		} catch (IOException e) {

			stopClient();
			e.printStackTrace();
		}

		// this.printWriter = new PrintWriter(socket.getOutputStream());
		// this.callable = new MsgRecieveCallable(bufferedReader);
		// this.executor = Executors.newSingleThreadExecutor();
	}

	@Override
	public void stopClient() throws IOException {

		if (null != inputStreamReader) {
			inputStreamReader.close();
		}
		if (null != printWriter) {
			printWriter.close();
		}
		if (null != bufferedReader) {
			bufferedReader.close();
		}
		if (null != socket) {
			socket.close();
			socket = null;
		}
		if (null != inThread) {
			try {
				inThread.sleep(1000);
			} catch (InterruptedException e) {
				inThread = null;
				e.printStackTrace();
			}
		}
	}

	@Override
	public Runnable msgReceive() {
		return new Runnable() {
			@Override
			public void run() {

				String msg = null;
				try {
					while ((msg = bufferedReader.readLine()) != null) {

						textarea.append(msg + "\n");
						System.out.println(msg);
					}
				} catch (IOException e) {
					try {
						stopClient();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

			}

		};

	}

	@Override
	public void msgSendTCP(String msg) {
		if (null != printWriter) {
			printWriter.write(msg + "\n");
			printWriter.flush();
		}
	}

	@Override
	public void setTextArea(JTextArea textarea) {
		this.textarea = textarea;
	}

	@Override
	public void msgSendUDP(String msg) {
		// try {
		// InetAddress ip = InetAddress.getByName("localhost");
		// DatagramSocket udpsocket = new DatagramSocket();
		// byte[] buf = msg.getBytes();
		//
		// int port = 8889;
		// DatagramPacket p = new DatagramPacket(buf, buf.length, ip, port);
		// udpsocket.send(p);
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

	}

}
