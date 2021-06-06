package com.cj4dplex.socket.test.integrated.socket.client;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

import com.cj4dplex.socket.test.integrated.init.IntegratedSocketInit;
import com.cj4dplex.socket.test.integrated.ui.center.controller.JSplitPaneCenterCmd;

public class ClientSocket {

	private boolean udpOrTcp = false;
	private boolean uiSelected = false;
	private String ipAddressAndPort = null;
	private Thread thread = null;
	private Object object = null;
	private Object oInput = null;
	private Object oOutput = null;

	public boolean isUdpOrTcp() {
		return udpOrTcp;
	}

	public String getIpAddressAndPort() {
		return ipAddressAndPort;
	}

	public Boolean isUiSelected() {
		return uiSelected;
	}

	public void setUiSelected(boolean uiSelected) {
		this.uiSelected = uiSelected;
	}

	public void init(final Object object) {
		this.object = object;

		if (object instanceof Socket) {
			final Socket socket = (Socket) object;
			this.udpOrTcp = true;
			this.ipAddressAndPort = socket.getRemoteSocketAddress().toString();

			try {
				final BufferedReader bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				this.oInput = bufferReader;
				final PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
				this.oOutput = printWriter;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			final DatagramSocket datagramSocket = (DatagramSocket) object;
			this.udpOrTcp = false;
			this.ipAddressAndPort = "Common UDP Port";

			final byte[] iOneByte = new byte[1];
			this.oInput = iOneByte;
		}
		System.out.println(udpOrTcp + ipAddressAndPort);
		final List<ClientSocket> clientSockets = IntegratedSocketInit.getInstance().getClientSockets();
		clientSockets.add(this);
		new JSplitPaneCenterCmd().tableAdd(this);
	}

	public void performOn() {
		final Runnable runnable = new Runnable() {
			@Override
			public void run() {
				if (object instanceof Socket) {
					System.out.println("TCP Socket !!");
					final BufferedReader bufferedReader = (BufferedReader) oInput;
					final PrintWriter printWriter = (PrintWriter) oOutput;

					while (!Thread.currentThread().isInterrupted()) {

						try {
							final String message = bufferedReader.readLine();
							if (uiSelected) {
								new JSplitPaneCenterCmd().messageView(Color.black, 11,
										((Socket) object).getRemoteSocketAddress() + ">" + message);
							}

							printWriter.println(String.format("Server> %s", message));
							printWriter.flush();
						} catch (IOException e) {
							try {
								System.out.println("Server Exit");

								Thread.sleep(500);
								Thread.interrupted();
								System.exit(0);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							e.printStackTrace();

						}
					}
				} else {
					System.out.println("UDP Socket !!");
					final DatagramSocket datagramSocket = (DatagramSocket) object;
					final StringBuffer stringBuffer = new StringBuffer();
					// final byte[] oneByte = (byte[]) oInput;
					while (!Thread.currentThread().isInterrupted()) {
						final byte[] oneByte = new byte[1024];
						final DatagramPacket datagramPacket = new DatagramPacket(oneByte, 0, oneByte.length);

						stringBuffer.setLength(0);
						try {
							datagramSocket.receive(datagramPacket);
							do {
								String getData = new String(datagramPacket.getData());
								stringBuffer.append(getData);

							} while ("\n".equals(new String(datagramPacket.getData())));

							if (uiSelected) {
								new JSplitPaneCenterCmd().messageView(Color.black, 11,
										datagramPacket.getSocketAddress() + ">" + stringBuffer.toString());
							}

							final byte[] sendByte = ("Server>" + stringBuffer).toString().getBytes();
							final DatagramPacket sendPacket = new DatagramPacket(sendByte, sendByte.length,
									datagramPacket.getAddress(), datagramPacket.getPort());
							datagramSocket.send(sendPacket);

						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		this.thread = new Thread(runnable);
		this.thread.setDaemon(true);
		thread.setName("Tcp??>>" + udpOrTcp);
		thread.start();
	}

	private void exceptionOff() {
		if (object instanceof Socket) {
			final Socket socket = (Socket) object;

			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			final DatagramSocket datagramSocket = (DatagramSocket) object;
			datagramSocket.close();
		}
	}

	public void performOff() {
		if (object instanceof Socket) {
			final Socket socket = (Socket) object;
		} else {
			final DatagramSocket datagramSocket = (DatagramSocket) object;
			datagramSocket.close();
		}
		while (null != thread && thread.isAlive()) {
			thread.interrupt();
		}
	}

}
