package com.nl;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.dispatcher.SwingDispatchService;

public class SwingUICore extends JFrame implements WindowListener {
  GlobalListener globalListener;

  public SwingUICore(GlobalListener listener) {
    globalListener = listener;
    
    GlobalScreen.setEventDispatcher(new SwingDispatchService());

    setTitle("Key Popper - UI Core");
    setSize(getPreferredSize());
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    addWindowListener(this);
    setVisible(true);
  }
  
  public void windowOpened(WindowEvent e) {
		globalListener.init();
	}

	public void windowClosed(WindowEvent e) {
    try {
      GlobalScreen.unregisterNativeHook();
    }
		catch (NativeHookException ex) {
      System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());
			ex.printStackTrace();

      System.exit(1);
    }
    
		System.exit(0);
	}

	public void windowClosing(WindowEvent e) { /* Unimplemented */ }
	public void windowIconified(WindowEvent e) { /* Unimplemented */ }
	public void windowDeiconified(WindowEvent e) { /* Unimplemented */ }
	public void windowActivated(WindowEvent e) { /* Unimplemented */ }
	public void windowDeactivated(WindowEvent e) { /* Unimplemented */ }
}
