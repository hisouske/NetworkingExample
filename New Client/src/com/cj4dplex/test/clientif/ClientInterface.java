package com.cj4dplex.test.clientif;

import java.io.IOException;

import javax.swing.JTextArea;



public interface ClientInterface {

	public void msgSendTCP(String msg);

	void stopClient() throws IOException;

	Runnable msgReceive();

	void setTextArea(JTextArea textarea);

}
