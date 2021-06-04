package com.cj4dplex.socket.test.integrated.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.cj4dplex.socket.test.integrated.ui.center.UiCenter;



public class UiMain extends JFrame {

	private volatile static UiMain object = null;

	public static UiMain getInstance() {
		if (object == null) {
			synchronized (UiMain.class) {
				if (object == null) {
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
		
		
		
	
		contentPane.add(UiCenter.getInstance(), BorderLayout.CENTER);
		

	}

}
