package com.rpggame.mapeditor.view.layerview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LayerPanelSettings extends JPanel {

	private int panelHeight = 400;

	public LayerPanelSettings() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(Color.red);
		JLabel elementName = new JLabel();
		this.setPreferredSize(new Dimension(10, 20));
	}

	// TODO replace Sting with layer data
	public void updatePanelContents(String s) {
		this.setPreferredSize(new Dimension(200, this.panelHeight));
		System.out.println(s);
		this.revalidate();
		
		System.out.println("size: " + this.getPreferredSize());
	}
}
