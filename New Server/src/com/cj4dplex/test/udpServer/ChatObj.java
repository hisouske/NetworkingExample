package com.cj4dplex.test.udpserver;

import com.cj4dplex.test.tcpserver.ServerResource;

public class ChatObj {
	private String Chat = null;

	private volatile static ChatObj chatObj = null;

	public static final ChatObj getInstance() {
		if (null == chatObj) {
			synchronized (ChatObj.class) {
				if (null == chatObj) {
					chatObj = new ChatObj();
				}
			}
		}
		return chatObj;
	}

	public String getChat() {
		return Chat;
	}

	public synchronized void setChat(String chat) {
		Chat = chat;
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
