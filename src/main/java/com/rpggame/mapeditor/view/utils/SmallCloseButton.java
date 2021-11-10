package com.rpggame.mapeditor.view.utils;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import com.rpggame.mapeditor.constants.MapEditorConstants;

public class SmallCloseButton extends JButton{

	public SmallCloseButton() {
		this.setText("x");
		this.setFont(MapEditorConstants.REGULAR_FONT);
		this.setForeground(MapEditorConstants.LIGHT_GRAY);
		this.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
		this.setBackground(MapEditorConstants.TRANSPARENT);
	}
	
	
	
}
