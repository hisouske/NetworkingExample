package com.cj4dplex.socket.test.client.ui;


import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import com.cj4dplex.socket.test.client.main.ClientViewCenter;


import java.awt.BorderLayout;

public class ClientView extends JFrame {

	private static final long serialVersionUID = 376745000444606222L;

	private volatile static ClientView object = null;

	public static ClientView getInstance() {
		if (object == null) {
			synchronized (ClientView.class) {
				if (object == null) {
					object = new ClientView();
				}
			}
		}

		return object;
	}

	public JPanel contentPane = null;

	private ClientView() {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 384);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(ClientViewCenter.getInstance(),BorderLayout.CENTER);

	}
}
