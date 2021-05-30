package com.cj4dplex.test.tcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Enumeration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.swing.AbstractButton;
import javax.swing.JRadioButton;

import com.cj4dplex.test.clientgui.ClientView;
import com.cj4dplex.test.tcpclient.ClientService;

public class Controller implements ActionListener, WindowListener {

	private ClientView clientView = null;
	private ClientService clientService = null;
	private String getTextField = "";

	public Controller(ClientService cs) {
		this.clientService = cs;

		clientView = new ClientView();
		clientView.setVisible(true);

		clientView.addWindowListener(this);
		clientView.btnSend.addActionListener(this);

		clientService.receiveReady(clientView.textArea);


	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == clientView.btnSend) {
			getTextField = clientView.textField.getText();
			System.out.println(getTextField);
			//이슈 = clientService.msgSend가 서버쪽에서 종료시 닫히도록 수정 요청
			clientService.msgSend(getTextField);
			clientView.textField.setText("");
		}

	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		clientService.msgSend("#exit#");
		try {
			clientService.thread.sleep(50);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {

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
