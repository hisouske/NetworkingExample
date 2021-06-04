/**
 * Create Time: 2021-06-01 13:22:25:981
 * Made by: Tristan
 */
package com.cj4dplex.socket.intergrated.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.cj4dplex.socket.intergrated.ui.center.JSplitPaneCenter;

public class UiMain extends JFrame {

	private static final long serialVersionUID = -5852719554230323010L;
	
	private volatile static UiMain object = null; 
	
	public static UiMain getInstance(){
		if(object == null){
			synchronized (UiMain.class) {
				if(object == null){
					object = new UiMain();
				}
			}
		}
		
		return object;
	}
	
	private JPanel contentPane = null;
	
	public UiMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		contentPane.add(JSplitPaneCenter.getInstance(), BorderLayout.CENTER);
	}

}
