package com.rpggame.mapeditor.view.topbar;

import java.util.function.Consumer;

import javax.swing.JOptionPane;

public class WindowSizeChangeDialog {

	private final String[] windowSizes = { "800x600", "1200x720", "1920x1080", "2560x1440" };

	public WindowSizeChangeDialog(Consumer<String> updateFrameSizeFunction) {
		String windowSize = (String) JOptionPane.showInputDialog(null, "Adjust the window size", "Change Window Size",
				JOptionPane.PLAIN_MESSAGE, null, windowSizes, windowSizes[0]);
		updateFrameSizeFunction.accept(windowSize);
	}

}
