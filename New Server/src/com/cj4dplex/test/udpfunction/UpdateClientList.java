package com.cj4dplex.test.udpfunction;

import javax.swing.JTextArea;

import com.cj4dplex.test.udpserver.UdpResource;

public class UpdateClientList {

	JTextArea userArea = null;

	private volatile static UpdateClientList updateClientList = null;

	public static final UpdateClientList getInstance() {
		if (null == updateClientList) {
			synchronized (UpdateClientList.class) {
				if (null == updateClientList) {
					updateClientList = new UpdateClientList();
				}
			}
		}
		return updateClientList;
	}

	public void setTextArea(JTextArea userArea) {
		this.userArea = userArea;
	}

	public void listUpdate() {
		userArea.setText("");
		for (ClientVO client : UdpResource.getInstance().getClientList()) {
			String ipPort = client.getAddress() + " / " + client.getPort() + "\n";
			userArea.append(ipPort);
		}
	}
}
