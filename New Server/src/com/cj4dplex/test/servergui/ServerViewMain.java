package com.cj4dplex.test.servergui;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;


public class ServerViewMain extends JFrame {

	private JPanel contentPane = null;
	public JButton btnTCP = null;
	public JButton btnUDP = null;

	public ServerViewMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 184, 137);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnTCP = new JButton("TCP Server");

		btnTCP.setBounds(14, 12, 138, 27);
		contentPane.add(btnTCP);

		btnUDP = new JButton("UDP Server");

		btnUDP.setBounds(14, 51, 138, 27);
		contentPane.add(btnUDP);
	}
}
