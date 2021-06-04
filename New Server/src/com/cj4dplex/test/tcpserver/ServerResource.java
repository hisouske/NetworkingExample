package com.cj4dplex.test.tcpserver;

import java.net.Socket;
import java.util.TreeMap;

import com.cj4dplex.test.serverconnect.ServerConnect;

public class ServerResource {

	private volatile static ServerResource serverResource = null;

	public static final ServerResource getInstance() {
		if (null == serverResource) {
			synchronized (ServerResource.class) {
				if (null == serverResource) {
					serverResource = new ServerResource();
				}
			}
		}
		return serverResource;
	}

	private final TreeMap<Integer, Socket> clientList = new TreeMap<>();



	public TreeMap<Integer, Socket> getClientList() {
		return clientList;
	}

}
