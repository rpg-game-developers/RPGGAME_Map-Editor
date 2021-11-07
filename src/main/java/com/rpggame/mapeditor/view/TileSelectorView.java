package com.rpggame.mapeditor.view;

import javax.swing.*;
import java.awt.*;

public class TileSelectorView extends JPanel {

	public TileSelectorView() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setPreferredSize(new Dimension(screenSize.width/5, screenSize.height));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
}
