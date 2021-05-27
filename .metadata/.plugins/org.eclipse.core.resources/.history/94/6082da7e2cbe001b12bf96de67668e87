package com.cj4dplex.test.clientif;

import java.awt.TextArea;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.swing.JTextArea;

import com.cj4dplex.test.clientgui.ClientView;
import com.cj4dplex.test.inoutmsg.MsgRecieveCallable;

//import com.cj4dplex.test.controller.BufferedReader;

public interface ClientInterface {






	public void msgSendTCP(String msg);


	public void msgSendUDP(String msg);

	void stopClient() throws IOException;


	Runnable msgReceive();


	void setTextArea(JTextArea textarea);

}
