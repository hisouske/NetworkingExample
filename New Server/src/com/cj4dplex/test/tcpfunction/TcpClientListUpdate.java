package com.cj4dplex.test.tcpfunction;

import javax.swing.JTextArea;

import com.cj4dplex.test.tcpserver.ServerResource;



public class TcpClientListUpdate {
	
	private volatile static TcpClientListUpdate tcpClientListUpdate = null;
	private JTextArea userArea = null;

	public static final TcpClientListUpdate getInstance() {
		if (null == tcpClientListUpdate) {
			synchronized (TcpClientListUpdate.class) {
				if (null == tcpClientListUpdate) {
					tcpClientListUpdate = new TcpClientListUpdate();
				}
			}
		}
		return tcpClientListUpdate;
	}
	
	
	public void setUserArea(JTextArea userArea) {
		this.userArea = userArea;
	}


	public void listUpdate() {
		userArea.setText("");
		for (Integer i : ServerResource.getInstance().getClientList().keySet()) {
			userArea.append(i+"번 "+"\n");
		}
	}

}
