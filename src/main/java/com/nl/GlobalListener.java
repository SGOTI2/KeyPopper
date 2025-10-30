package com.nl;

import java.util.function.Consumer;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class GlobalListener implements NativeKeyListener {
	Consumer<String> keyPressHook = null;

	public void nativeKeyPressed(NativeKeyEvent e) {
		System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

		if (keyPressHook != null) {
			keyPressHook.accept(NativeKeyEvent.getKeyText(e.getKeyCode()));
		}

		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
      try {
        GlobalScreen.unregisterNativeHook();
      } catch (NativeHookException nativeHookException) {
				nativeHookException.printStackTrace();
				return;
      }
			System.out.println("Deregistration was successful!\n[Your keys are no longer being tracked]");
    }
	}

	public void init() {
		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());
			System.err.println("This may be because permission was denied to access required features of the OS");

			System.exit(1);
		}
		System.out.println("Registration was successful!\n[Your keys are now being tracked] Tracking will end on program exit");

		GlobalScreen.addNativeKeyListener(this);
	}
}