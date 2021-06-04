/**
 * Create Time: 2021-06-01 12:38:33:984
 * Made by: Tristan
 */
package com.cj4dplex.socket.intergrated.init;

import java.util.ArrayList;
import java.util.List;

import com.cj4dplex.socket.intergrated.socket.ServerSocketTcpServiceImpl;
import com.cj4dplex.socket.intergrated.socket.ServerSocketUdpServiceImpl;
import com.cj4dplex.socket.intergrated.socket.client.ClientSocket;
import com.cj4dplex.socket.intergrated.socket.service.ServerSocketService;

public class IntergratedSocketInit {

	private volatile static IntergratedSocketInit object = null; 
	
	public static IntergratedSocketInit getInstance(){
		if(object == null){
			synchronized (IntergratedSocketInit.class) {
				if(object == null){
					object = new IntergratedSocketInit();
				}
			}
		}
		
		return object;
	}
	
	private final ServerSocketService tcp = new ServerSocketTcpServiceImpl();
	private final ServerSocketService udp = new ServerSocketUdpServiceImpl();
	
	public ServerSocketService getTcp() {
		return tcp;
	}

	public ServerSocketService getUdp() {
		return udp;
	}

	private final List<ClientSocket> clientSockets = new ArrayList<ClientSocket>();

	public List<ClientSocket> getClientSockets() {
		return clientSockets;
	}
	
}
