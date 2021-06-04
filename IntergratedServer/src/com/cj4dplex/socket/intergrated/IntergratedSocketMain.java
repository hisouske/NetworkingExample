/**
 * Create Time: 2021-06-01 12:28:26:290
 * Made by: Tristan
 */
package com.cj4dplex.socket.intergrated;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Painter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.cj4dplex.socket.intergrated.init.IntergratedSocketInit;
import com.cj4dplex.socket.intergrated.ui.UiMain;

public class IntergratedSocketMain {

	private IntergratedSocketMain() {
		
	}
	
	public static void main(String...strings) {
		try {
			System.setProperty("sun.java2d.opengl", "true");
			System.setProperty("swing.aatext", "true");
			System.setProperty("sun.java2d.d3d", "false");
			
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			
			// 파일 탐색기 새폴더 못만들게 하기
			UIManager.put("FileChooser.readOnly", Boolean.TRUE);
			
			// 화면 불할 색
			UIManager.put("SplitPane:SplitPaneDivider[Enabled].backgroundPainter", new Painter<Object>() {
				@Override
				public void paint(Graphics2D graphics2d, Object object, int width, int height) {
					graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON );
					graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					graphics2d.setColor(new Color(0, 0, 0));
					graphics2d.fillRect(0, 0, width, height);
				}
			});
			
			// 버튼 색 및 색깔 변경
			UIManager.getLookAndFeelDefaults().put("Button.background", Color.darkGray);
			UIManager.getLookAndFeelDefaults().put("Button.textForeground", Color.white);
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		IntergratedSocketInit.getInstance().getTcp().socketOpen(7777);
		IntergratedSocketInit.getInstance().getUdp().socketOpen(7778);
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				UiMain.getInstance().setVisible(true);
			}
		});
	}
	
}
