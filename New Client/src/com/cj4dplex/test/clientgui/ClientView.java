package com.cj4dplex.test.clientgui;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;

public class ClientView extends JFrame {

	public JPanel contentPane = null;
	public JTextField textField = null;
	public JTextArea textArea = null;
	public JButton btnSend = null;

	public ButtonGroup radioGroup = null;

	public ClientView() {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(14, 246, 251, 24);
		contentPane.add(textField);
		textField.setColumns(10);

		btnSend = new JButton("Send");
		btnSend.setBounds(269, 245, 75, 25);
		contentPane.add(btnSend);

		textArea = new JTextArea();
		textArea.setBounds(14, 41, 330, 199);
		contentPane.add(textArea);

	}

}
