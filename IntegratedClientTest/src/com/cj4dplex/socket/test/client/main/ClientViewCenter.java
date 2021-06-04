package com.cj4dplex.socket.test.client.main;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.cj4dplex.socket.test.client.controller.ClientController;

public class ClientViewCenter extends JSplitPane {

	
	private static final long serialVersionUID = -3434627225007495866L;
	
	private volatile static ClientViewCenter object = null;

	public static ClientViewCenter getInstance() {
		if (object == null) {
			synchronized (ClientViewCenter.class) {
				if (object == null) {
					object = new ClientViewCenter();
				}
			}
		}
		return object;
	}

	public final JTextPane jTextPane = new JTextPane();
	public JTextField textField = new JTextField();
	public JButton btnSend = null;
	public ButtonGroup radioGroup = null;
	public JRadioButton rdbtnTcp = null;
	public JRadioButton rdbtnUdp = null;
	public JTextPane textPane = null;
	private ClientController clientController = null;

	public ClientViewCenter() {
		setOrientation(JSplitPane.VERTICAL_SPLIT);
		setResizeWeight(0.95F);
		
		textField = new JTextField();
		textField.setBounds(5, 246, 335, 30);
		jTextPane.add(textField);
		textField.setColumns(10);
		btnSend = new JButton("Send");
		btnSend.setBounds(269, 245, 75, 25);


		rdbtnTcp = new JRadioButton("TCP");
		rdbtnTcp.setBounds(14, 8, 57, 27);
		jTextPane.add(rdbtnTcp);

		rdbtnUdp = new JRadioButton("UDP");
		rdbtnUdp.setSelected(true);
		rdbtnUdp.setBounds(77, 8, 75, 27);
		jTextPane.add(rdbtnUdp);

		radioGroup = new ButtonGroup();
		radioGroup.add(rdbtnTcp);
		radioGroup.add(rdbtnUdp);

//		textPane = new JTextPane();
//		textPane.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
//		textPane.setBounds(14, 34, 328, 200);
//		textPane.setEditable(false);

		clientController = new ClientController();
		btnSend.addActionListener(clientController);
		rdbtnTcp.addActionListener(clientController);
		rdbtnUdp.addActionListener(clientController);

		jTextPane.setFocusable(false);
		jTextPane.setOpaque(false);
		jTextPane.setEditable(false);
//		jTextPane.add(textPane);
		setTopComponent(new JScrollPane(jTextPane));
		setBottomComponent(btnSend);

	}
}
