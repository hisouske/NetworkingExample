package com.cj4dplex.test.tcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.Socket;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.cj4dplex.test.servergui.ServerView;
import com.cj4dplex.test.serverif.ServerInterface;
import com.cj4dplex.test.tcpserver.ServerService;

public class Controller implements ActionListener, WindowListener {

	private ServerView serverView = null;
	private ServerService serverService = null;

	public Controller(ServerView sv, ServerService ss) {
		this.serverService = ss;
		this.serverView = sv;
		serverService.receiveReady(serverView.textarea);
	}

	public void setUserArea(TreeMap<Integer, Socket> clientList) {
		serverView.userarea.setText("");
		for (Integer i : clientList.keySet()) {
			serverView.userarea.append(i + " 번 " + "\n");
			System.out.println("@controller server > " + clientList);
		}
	}

	public void setServer(ServerService ss) {
		this.serverService = ss;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// serverService.msgSend("#serverExit#");

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

}
