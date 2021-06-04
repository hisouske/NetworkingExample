/**
 * Create Time: 2021-06-01 13:55:25:967
 * Made by: Tristan
 */
package com.cj4dplex.socket.intergrated.ui.center.cmd;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.cj4dplex.socket.intergrated.socket.client.ClientSocket;
import com.cj4dplex.socket.intergrated.ui.center.JSplitPaneCenter;

public class JSplitPaneCenterCmd {

	public void tableAdd(final ClientSocket clientSocket) {
		final JSplitPaneCenter ui = JSplitPaneCenter.getInstance();
		final DefaultTableModel defaultTableModel = ui.defaultTableModel;
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				defaultTableModel.addRow(new Object[] {clientSocket});
			}
		});
	}
	
	public void messageViewInit() {
		final JSplitPaneCenter ui = JSplitPaneCenter.getInstance();
		final JTextPane jTextPane = ui.jTextPane;
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				jTextPane.setText("");
			}
		});
	}
	
	public void messageView(final Color color, final int fontSize, final String message){
		final JSplitPaneCenter ui = JSplitPaneCenter.getInstance();
		final JTextPane jTextPane = ui.jTextPane;
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
