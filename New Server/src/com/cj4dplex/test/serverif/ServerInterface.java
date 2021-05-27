package com.cj4dplex.test.serverif;

import java.io.IOException;

import javax.swing.JTextArea;

public interface ServerInterface {


	public Runnable msgReceive();

	public void msgSend(String msg);


	public void setJText(JTextArea textarea,JTextArea userarea);

	public void serverStop() throws IOException;
	public void AllStop() throws InterruptedException ;
	
}
