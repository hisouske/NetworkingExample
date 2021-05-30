package com.cj4dplex.test.servergui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class ServerView extends JFrame {
	public JPanel contentPane;
	public JTextArea textarea = new JTextArea();
	public static JTextArea userarea = new JTextArea();


	public ServerView() {

		setTitle("Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 343, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textarea.setBounds(14, 31, 172, 218);
		contentPane.add(textarea);

		userarea.setBounds(197, 31, 114, 218);
		contentPane.add(userarea);

		JLabel lblLoginUser = new JLabel("Login User");
		lblLoginUser.setBounds(215, 12, 77, 18);
		contentPane.add(lblLoginUser);

		JLabel lblChatting = new JLabel("Chatting");
		lblChatting.setBounds(68, 12, 62, 18);
		contentPane.add(lblChatting);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(184, 130, 2, 2);
		contentPane.add(scrollPane);

	}
}
