package com.cj4dplex.test.udpcontroller;


import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.cj4dplex.test.servergui.ServerView;
import com.cj4dplex.test.udpfunction.UpdateClientList;
import com.cj4dplex.test.udpserver.UdpServer;

public class Controller implements WindowListener {
	ServerView serverView = new ServerView();
	UdpServer udpServer = null;

	public Controller(UdpServer server) {
		serverView.setVisible(true);
		System.out.print(server);
		this.udpServer = server;
		udpServer.receiveReady(serverView.textarea);
		UpdateClientList.getInstance().setTextArea(serverView.userarea);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {

	}

	@Override
	public void windowClosed(WindowEvent arg0) {

	}

	@Override
	public void windowClosing(WindowEvent arg0) {

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {

	}

	@Override
	public void windowIconified(WindowEvent arg0) {

	}

	@Override
	public void windowOpened(WindowEvent arg0) {

	}

}
