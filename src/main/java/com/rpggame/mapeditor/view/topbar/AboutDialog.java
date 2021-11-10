package com.rpggame.mapeditor.view.topbar;

import com.rpggame.mapeditor.constants.FrameVariables;
import static com.rpggame.mapeditor.constants.MapEditorConstants.REGULAR_FONT;

import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog {

	public AboutDialog() {
		this.setLocationRelativeTo(null);
		this.setSize(new Dimension(FrameVariables.FRAME_WIDTH/5, FrameVariables.FRAME_HEIGHT/5));
		JLabel text = new JLabel();
		text.setText("Application for building maps in RPG_GAME.");
		text.setFont(REGULAR_FONT);
		this.add(text);
		this.setVisible(true);
	}
}
