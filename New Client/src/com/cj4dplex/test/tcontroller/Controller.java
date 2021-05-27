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
	// private Future future = null;
	// private boolean flag = true;

	public Controller(ClientService cs) {
		this.clientService = cs;

		clientView = new ClientView();
		clientView.setVisible(true);

		clientView.addWindowListener(this);
		clientView.btnSend.addActionListener(this);

		clientService.receiveReady(clientView.textArea);
		// this.thread = new Thread(this.setTextArea(), "GUI Client TextArea Receive");
		// thread.start();

	}

	// public Runnable setTextArea() {
	//
	// return new Runnable() {
	// @Override
	// public void run() {
	// while (flag) {
	// try {
	// future = clientService.executor.submit(clientService.callable);
	// clientView.textArea.append(future.get() + "\n");
	// if (null == future.get()) {
	// flag = false;
	// }
	// } catch (InterruptedException | ExecutionException e) {
	// flag = false;
	//
	// }
	// }
	// }
	// };
	//
	// }
//	public void setTextArea() {
//		clientService.setTextArea(clientView.textArea);
//	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == clientView.btnSend) {
			getTextField = clientView.textField.getText();
			System.out.println(getTextField);
			clientService.msgSend(getTextField);
			clientView.textField.setText("");
		}

	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		clientService.msgSend("#종료#");
		clientService.thread.interrupt();
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