package com.cj4dplex.test.tcpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Set;

import javax.swing.JTextArea;

import com.cj4dplex.test.function.Send;
import com.cj4dplex.test.function.ServerReceive;
import com.cj4dplex.test.serverif.ServerInterface;
import com.cj4dplex.test.tfunction.TcpClientListUpdate;

public class ServerService {
	private int clientNum;
	public Socket socket = null;

	private JTextArea textArea = null;
	private JTextArea userArea = null;
	private TcpClientListUpdate tcpClientListUpdate = null;
	public Thread thread = null;
	// private int timeout = 30000;
	private OutputStream outputStream = null;

	public ServerService(int cNum) throws IOException {
		this.clientNum = cNum;
		this.socket = ServerResource.getInstance().getClientList().get(clientNum);
		// socket.setSoTimeout(timeout);
		// 지정 된 시간만큼 기다렸다가 Exception으로 빠짐
		tcpClientListUpdate = TcpClientListUpdate.getInstance();

	}

	public void receiveReady(JTextArea textArea) {
		this.textArea = textArea;
		thread = new Thread(ServerReceive.TcpReceive(clientNum, textArea));
		thread.start();

	}

	public void userAreaReady(JTextArea userArea) {
		tcpClientListUpdate.setUserArea(userArea);
		tcpClientListUpdate.listUpdate();

	}

	public void Send(String output) {
		Set<Integer> test1 = ServerResource.getInstance().getClientList().keySet();
		for (Integer i : ServerResource.getInstance().getClientList().keySet()) {
			Send.TcpSend(output, ServerResource.getInstance().getClientList().get(i));
		}
	}

}
