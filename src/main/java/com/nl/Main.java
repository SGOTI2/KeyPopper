package com.nl;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        GlobalListener listener = new GlobalListener();
        SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SwingUICore(listener);
			}
		});
    }
}