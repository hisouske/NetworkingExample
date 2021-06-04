/**
 * Create Time: 2021-06-01 12:36:02:191
 * Made by: Tristan
 */
package com.cj4dplex.socket.intergrated.socket.client;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.List;

import com.cj4dplex.socket.intergrated.init.IntergratedSocketInit;
import com.cj4dplex.socket.intergrated.ui.center.JSplitPaneCenter;
import com.cj4dplex.socket.intergrated.ui.center.cmd.JSplitPaneCenterCmd;

public class ClientSocket {

	// false UDP, true: TCP
	private boolean udpOrTcp = false;
	private String ipAddressAndPort = null;

	public boolean isUdpOrTcp() {
		return udpOrTcp;
	}

	public String getIpAddressAndPort() {
		return ipAddressAndPort;
	}

	// UI Selected
	private boolean uiSelected = false;

	public boolean isUiSelected() {
		return uiSelected;
	}

	public void setUiSelected(boolean uiSelected) {
		this.uiSelected = uiSelected;
	}

	// Socket
	private Object object = null;

	// input
	private Object oInput = null;

	// output
	private Object oOutput = null;

	public void init(final Object object) {
		this.object = object;

		if (object instanceof Socket) {
			final Socket socket = (Socket) object;
			this.uiSelected = true;
			this.ipAddressAndPort = socket.getRemoteSocketAddress().toString();
			System.out.println(String.format("Connect: %s, %s", uiSelected ? "TCP" : "UDP", ipAddressAndPort));

			try {
				final BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				final PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

				this.oInput = bufferedReader;
				this.oOutput = printWriter;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.uiSelected = false;
			final DatagramSocket datagramSocket = (DatagramSocket) object;
			this.ipAddressAndPort = datagramSocket.getRemoteSocketAddress().toString();

			System.out.println(String.format("Connect: %s, %s", uiSelected ? "TCP" : "UDP", ipAddressAndPort));

			final byte[] iOneByte = new byte[1];

			this.oInput = iOneByte;
		}

		final List<ClientSocket> clientSockets = IntergratedSocketInit.getInstance().getClientSockets();
		clientSockets.add(this);

		new JSplitPaneCenterCmd().tableAdd(this);
	}

	private Thread thread = null;

	public void performOn() {
		final Runnable runnable = new Runnable() {
			public void run() {
				if (object instanceof Socket) {
					final BufferedReader bufferedReader = (BufferedReader) oInput;
					final PrintWriter printWriter = (PrintWriter) oOutput;

					while (!Thread.currentThread().isInterrupted()) {
						try {

							final String message = bufferedReader.readLine();
							if (uiSelected) {
								new JSplitPaneCenterCmd().messageView(Color.black, 11, message);
							}
							printWriter.println(String.format("Sergver>> %s", message));
							printWriter.flush();

						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} else {
					final DatagramSocket datagramSocket = (DatagramSocket) object;

					final StringBuffer stringBuffer = new StringBuffer();
					while (!Thread.currentThread().isInterrupted()) {
						final byte[] oneByte = (byte[]) oInput;

						final DatagramPacket datagramPacket = new DatagramPacket(oneByte, 0, oneByte.length);
						try {
							do {
								datagramSocket.receive(datagramPacket);
								stringBuffer.append(new String(datagramPacket.getData()));
							} while ("\\n".equals(new String(datagramPacket.getData())));

							if (uiSelected) {
								new JSplitPaneCenterCmd().messageView(Color.black, 11, stringBuffer.toString());
							}

							final byte[] sendByte = stringBuffer.toString().getBytes();
							final DatagramPacket sendDatagramPacket = new DatagramPacket(sendByte, 0, sendByte.length);

							datagramSocket.send(sendDatagramPacket);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};

		this.thread = new Thread(runnable);
		this.thread.setDaemon(true);
		thread.setName(
				String.format("Client Thread, Type: %s, IP Address: %s", udpOrTcp ? "TCP" : "UDP", ipAddressAndPort));
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

		while (null != thread && thread.isAlive()) {
			thread.interrupt();
		}

		final List<ClientSocket> clientSockets = IntergratedSocketInit.getInstance().getClientSockets();
		clientSockets.remove(this);
	}

	public void performOff() {
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

		while (null != thread && thread.isAlive()) {
			thread.interrupt();
		}
	}

}
