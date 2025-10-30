package com.nl;

import java.awt.event.WindowListener;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.dispatcher.SwingDispatchService;

public class SwingUICore extends JFrame implements WindowListener {
  private GlobalListener globalListener;
  private JTextArea textArea;

  public SwingUICore(GlobalListener listener) {
    globalListener = listener;
    
    GlobalScreen.setEventDispatcher(new SwingDispatchService());

    setTitle("Key Popper - UI Core");
    setSize(getPreferredSize());
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    addWindowListener(this);
    setVisible(true);

    textArea = new JTextArea(10, 30);
    textArea.setEditable(false);
    add(new JScrollPane(textArea), BorderLayout.CENTER);
    pack();
  }

  public void keyPressHook(String keyText) {
    System.out.println(keyText);
    String stringSummery = keyText;
    textArea.setText(textArea.getText() + "\n" + stringSummery);
  }

  public void windowOpened(WindowEvent e) {
		globalListener.init();
    globalListener.keyPressHook = (String T) -> keyPressHook(T);
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
    System.out.println("Deregistration was successful!\n[Your keys are no longer being tracked]");
    
		System.exit(0);
	}

	public void windowClosing(WindowEvent e) { /* Unimplemented */ }
	public void windowIconified(WindowEvent e) { /* Unimplemented */ }
	public void windowDeiconified(WindowEvent e) { /* Unimplemented */ }
	public void windowActivated(WindowEvent e) { /* Unimplemented */ }
	public void windowDeactivated(WindowEvent e) { /* Unimplemented */ }
}
