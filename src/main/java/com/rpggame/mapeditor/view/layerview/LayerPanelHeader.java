package com.rpggame.mapeditor.view.layerview;

import javax.swing.*;
import java.awt.*;

public class LayerPanelHeader extends JPanel {

	public LayerPanelHeader() {
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		Font font = new Font("Comic Sans", Font.BOLD, 20);
		JLabel layerLabel = new JLabel("Layer");
		layerLabel.setFont(font);

		this.add(layerLabel);
	}
}
