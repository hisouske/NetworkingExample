package com.cj4dplex.socket.test.integrated;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Painter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.cj4dplex.socket.test.integrated.init.IntegratedSocketInit;
import com.cj4dplex.socket.test.integrated.ui.UiMain;

//ServerSocket & UI Start
public class IntegratedSocketMain {

	private IntegratedSocketMain() {
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
					graphics2d.setColor(new Color(0, 0, 0));
					graphics2d.fillRect(0, 0, width, height);
				}
			});
			UIManager.put("SplitPane:SplitPaneDivider[Enabled].backgroundPainter", new Painter<Object>() {
				@Override
				public void paint(Graphics2D graphics2d, Object object, int width, int height) {
					graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
							RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					graphics2d.setColor(new Color(0, 0, 0));
					graphics2d.fillRect(0, 0, width, height);
				}
			});

			UIManager.getLookAndFeelDefaults().put("Button.background", Color.darkGray);
			UIManager.getLookAndFeelDefaults().put("Button.textForeground", Color.white);
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		IntegratedSocketInit.getInstance().getTcp().socketOpen(1112);
		IntegratedSocketInit.getInstance().getUdp().socketOpen(1111);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				UiMain.getInstance().setVisible(true);
			}
		});
	}

}
