package com.cruds.mainpage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class BOOKMAINPAGE  extends JFrame{

	JFrame parent;

	BOOKMAINPAGE() {

		setTitle("book demo");
		setSize(1000,1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		parent = this;

		add(new LoginPanel(parent));
		setVisible(true);

	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new BOOKMAINPAGE();

			}
		});
	}

}