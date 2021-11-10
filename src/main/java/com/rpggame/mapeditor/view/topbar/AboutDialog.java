package com.rpggame.mapeditor.view.topbar;

import com.rpggame.mapeditor.constants.FrameVariables;

import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog {

	public AboutDialog() {
		this.setLocationRelativeTo(null);
		this.setSize(new Dimension(FrameVariables.FRAME_WIDTH/5, FrameVariables.FRAME_HEIGHT/5));
		this.setVisible(true);
	}
}
