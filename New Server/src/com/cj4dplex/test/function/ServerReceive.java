package com.cj4dplex.test.function;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.JTextArea;

import com.cj4dplex.test.tcpfunction.TcpClientListUpdate;
import com.cj4dplex.test.tcpfunction.TcpOutClient;
import com.cj4dplex.test.tcpserver.ServerResource;
import com.cj4dplex.test.udpfunction.ClientVO;
import com.cj4dplex.test.udpfunction.UdpInClient;
import com.cj4dplex.test.udpfunction.UdpOutClient;
import com.cj4dplex.test.udpserver.UdpResource;

public class ServerReceive {
	
	
	private static String serverIP = "localhost";
	
	
//	public  Runnable ServerReceive(int port) {
//	return new Runnable() {
//		@Override
//		public void run() {
//			try { 
//				MulticastSocket ms = new MulticastSocket(port); 
//				InetAddress ia = InetAddress.getByName(serverIP); 
//				
//				ms.joinGroup(ia); 
//				
//				byte data[] = new byte[512]; 
//				
//				DatagramPacket dp = new DatagramPacket(data, data.length); 
//				
//				System.out.println("받기 대기중"); 
//				ms.receive(dp);
//				byte receiveData[] = dp.getData(); 
//				int receiveCount = dp.getLength(); 
//				System.out.println("받은 문자-->"+ new String(receiveData,0,receiveData.length)); 
//			}catch(Exception e) { 
//		e.printStackTrace(); 
//		}
//
//		
//	} 
//};
//}
//	
//	
//	
	
	
	
	
	
	
	
	
	

	public static final Runnable UdpReceive(DatagramSocket socket, DatagramPacket receivePacket, JTextArea textarea) {
		return new Runnable() {
			private String message = null;
			private UdpOutClient outClient = null;
			private UdpInClient inClient = null;

			@Override
			public void run() {
				while (true) {
					try {
						socket.receive(receivePacket);
						message = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");

						if (message.equals("#exit#")) {
							outClient = new UdpOutClient(receivePacket);
						} else {

							inClient = new UdpInClient(receivePacket);
							textarea.append(receivePacket.getAddress()+"/"+receivePacket.getPort()+" :"+message + "\n");
							System.out.println(message);

							for (ClientVO client : UdpResource.getInstance().getClientList()) {
								Send.UdpSend(receivePacket.getAddress()+"/"+receivePacket.getPort()+" :"+message, client.getPort(), socket);
							}

						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		};
	}

	public static final Runnable TcpReceive(int clientNum, JTextArea textarea) {
		return new Runnable() {
			@Override
			public void run() {
				int size = 0;
				OutputStream outputStream = null;
				TcpOutClient outClient = null;
				InputStream inputStream = null;
				byte[] bt = new byte[256];
				try {
					inputStream = ServerResource.getInstance().getClientList().get(clientNum).getInputStream();
					
					while (-1 != (size = inputStream.read(bt))) {
						System.out.println(inputStream);
						System.out.println("exit");

						String output = new String(bt, 0, size, "utf-8");

						if (output.equals("#exit#")) {
							System.out.println("exit");
							textarea.append(clientNum + "번 님이 종료하셨습니다 " + "\n");

							outClient = new TcpOutClient(clientNum);
							TcpClientListUpdate.getInstance().listUpdate();
							break;
						} else {
							textarea.append(clientNum + "번 : " + output + "\n");
						}

						for (Integer i : ServerResource.getInstance().getClientList().keySet()) {
							
//							Send.TcpSend(clientNum + "번 :" + output, ServerResource.getInstance().getClientList().get(i));
						}

					}
				} catch (IOException e) {
					//ServiceReceive Socket closed Exception > System.exit()
					System.exit(0);
					e.printStackTrace();
				}
			}
		};
	}













}
