package com.rpggame.mapeditor.view.utils;

import com.rpggame.mapeditor.constants.MapEditorConstants;

import javax.swing.*;

public class SmallCloseButton extends JButton{

	public SmallCloseButton() {
		this.setText("x");
		this.setFont(MapEditorConstants.REGULAR_FONT);
		this.setForeground(MapEditorConstants.LIGHT_GRAY);
		this.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
		this.setBackground(MapEditorConstants.TRANSPARENT);
		// TODO: Refactor to controller
		this.addActionListener(e -> {
			SmallCloseButton button = (SmallCloseButton) e.getSource();
			button.getParent().getParent().setVisible(false);
		});
	}
	
	
	
}
