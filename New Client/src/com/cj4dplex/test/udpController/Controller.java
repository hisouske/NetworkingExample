package com.cj4dplex.test.udpcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.cj4dplex.test.clientgui.ClientView;
import com.cj4dplex.test.udpclient.UdpClient;

public class Controller implements ActionListener, WindowListener {
	private ClientView clientView = null;
	private UdpClient udpClient = null;

	public Controller(UdpClient uc) {
		this.udpClient = uc;
		this.clientView = new ClientView();
		clientView.setVisible(true);
		clientView.addWindowListener(this);
		clientView.btnSend.addActionListener(this);
		udpClient.receiveReady(clientView.textArea);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == clientView.btnSend) {
			udpClient.msgSend(clientView.textField.getText());
			clientView.textField.setText("");
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		udpClient.msgSend("#exit#");
		try {
			udpClient.thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		udpClient.thread.interrupt();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

}
