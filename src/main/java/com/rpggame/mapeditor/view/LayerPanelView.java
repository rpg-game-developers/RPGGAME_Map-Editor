package com.rpggame.mapeditor.view;

import javax.swing.*;
import java.awt.*;

public class LayerPanelView extends JPanel {

	public LayerPanelView() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setPreferredSize(new Dimension(screenSize.width/5, screenSize.height));
	}
}
