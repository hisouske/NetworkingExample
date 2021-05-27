package com.cj4dplex.test.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.Socket;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.cj4dplex.test.inoutmsg.ServerService;
import com.cj4dplex.test.servergui.ServerViewTCP;
import com.cj4dplex.test.serverif.ServerInterface;

public class Controller implements ActionListener, WindowListener {

	private ServerViewTCP serverViewTCP = new ServerViewTCP();
	private ServerInterface serverService = null;


	public Controller() {

		serverViewTCP = new ServerViewTCP();
		serverViewTCP.setVisible(true);

	}

	public void setServer(ServerInterface serverService) {
		this.serverService = serverService;
		serverService.setJText(serverViewTCP.textarea,serverViewTCP.userarea);

	}

	public void setUserArea(TreeMap<Integer, Socket> clientList) {
		serverViewTCP.userarea.setText("");
		for (Integer i : clientList.keySet()) {
			serverViewTCP.userarea.append(i + " 번 " + "\n");
			System.out.println("@controller server > " + clientList);
		}
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
		serverService.msgSend("#servererr#");
		try {
			serverService.AllStop();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
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
