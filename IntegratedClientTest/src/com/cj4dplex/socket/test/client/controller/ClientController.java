package com.cj4dplex.socket.test.client.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.cj4dplex.socket.test.client.connect.ClientResouce;
import com.cj4dplex.socket.test.client.main.ClientViewCenter;

public class ClientController implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		final ClientViewCenter clientViewCenter = ClientViewCenter.getInstance();

		if (e.getSource() == clientViewCenter.btnSend) {
			if (clientViewCenter.rdbtnTcp.isSelected()) {
				ClientResouce.getInstance().getTcp().Send(clientViewCenter.textField.getText());
			}
			if (clientViewCenter.rdbtnUdp.isSelected()) {
				ClientResouce.getInstance().getUdp().Send(clientViewCenter.textField.getText());

			}
			messageFieldInit();
		}
	}

	public void messageFieldInit() {
		final ClientViewCenter clientViewCenter = ClientViewCenter.getInstance();
		final JTextField jTextField = clientViewCenter.textField;
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				jTextField.setText("");
			}
		});

	}

	public void messageView(final Color color, final int fontSize, final String message) {
		final ClientViewCenter clientViewCenter = ClientViewCenter.getInstance();
		final JTextPane jTextPane = clientViewCenter.jTextPane;

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				final StyledDocument styledDocument = jTextPane.getStyledDocument();

				final SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
				StyleConstants.setForeground(simpleAttributeSet, color);
				StyleConstants.setFontSize(simpleAttributeSet, fontSize);

				final String screenLog = String.format("%s\n", message);

				try {
					styledDocument.insertString(styledDocument.getLength(), screenLog, simpleAttributeSet);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		});
	}
}