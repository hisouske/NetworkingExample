package com.cj4dplex.socket.test.client.main;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Painter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.cj4dplex.socket.test.client.connect.ClientResouce;
import com.cj4dplex.socket.test.client.integrated.IntegratedClient;
import com.cj4dplex.socket.test.client.ui.ClientView;

public class ClientMain {

	private ClientMain() {

	}

	public static void main(String... strings) {
		try {

			UIManager.setLookAndFeel(new NimbusLookAndFeel());

			UIManager.put("FileChooser.readOnly", Boolean.TRUE);
			UIManager.put("SplitPane:SplitPaneDivider[Enabled].backgroundPainter", new Painter<Object>() {
				@Override
				public void paint(Graphics2D graphics2d, Object object, int width, int height) {
					graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
							RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				}
			});
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		ClientResouce.getInstance().getTcp().init(1112);
		ClientResouce.getInstance().getUdp().init(1111);
		ClientResouce.getInstance().getUdp().Receive();
		ClientResouce.getInstance().getTcp().Receive();

//		new IntegratedClient();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ClientView.getInstance().setVisible(true);
			}
		});
	}

}
