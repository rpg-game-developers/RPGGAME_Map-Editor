package com.rpggame.mapeditor.view.layerview;

import javax.swing.*;
import java.awt.*;

public class LayerPanelSettings extends JPanel {

	public LayerPanelSettings() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel elementName = new JLabel();
	}

	public void redrawLayerPanelSettings() {
		this.repaint();
	}
}
