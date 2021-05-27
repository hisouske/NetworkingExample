package com.cj4dplex.test.udpServer;

import java.util.ArrayList;

import com.cj4dplex.test.udpFunc.ClientVO;

public class UdpResource {

	private ArrayList<ClientVO> clientList = new ArrayList<ClientVO>();

	private volatile static UdpResource udpResource = null;

	public static final UdpResource getInstance() {
		if (null == udpResource) {
			synchronized (UdpResource.class) {
				if (null == udpResource) {
					udpResource = new UdpResource();
				}
			}
		}
		return udpResource;
	}

	public ArrayList<ClientVO> getClientList() {
		return clientList;
	}
}
