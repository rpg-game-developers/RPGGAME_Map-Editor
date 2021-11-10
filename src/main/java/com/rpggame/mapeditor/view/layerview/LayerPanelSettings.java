package com.rpggame.mapeditor.view.layerview;

import static com.rpggame.mapeditor.constants.FrameVariables.FRAME_HEIGHT;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LayerPanelSettings extends JPanel {

	private int panelHeight = 200;

	public LayerPanelSettings() {
		this.setLayout(new GridLayout(4, 2, FRAME_HEIGHT / 10, 0));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setVisible(false);
	}

	// TODO replace Sting with layer data
	public void updatePanelContents(String s) {
		this.removeAll();
		this.setVisible(true);
		this.setPreferredSize(new Dimension(200, this.panelHeight));

		JLabel typeLabel = new JLabel("Type:");
		this.add(typeLabel);
		JComboBox<String> types = new JComboBox<>();
		this.add(types);

		JLabel nameLabel = new JLabel("Name:");
		this.add(nameLabel);
		JLabel elementName = new JLabel(s);
		this.add(elementName);

		JLabel levelLabel = new JLabel("Level:");
		this.add(levelLabel);
		JComboBox<Integer> levels = new JComboBox<>();
		this.add(levels);

		JLabel deleteButtonLabel = new JLabel("Actions:");
		this.add(deleteButtonLabel);
		JButton deleteButton = new JButton("Delete");
		this.add(deleteButton);

		this.revalidate();
	}
}
