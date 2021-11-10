package com.rpggame.mapeditor.view.layerview;

import static com.rpggame.mapeditor.constants.FrameVariables.FRAME_HEIGHT;
import static com.rpggame.mapeditor.constants.MapEditorConstants.REGULAR_FONT;

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
		this.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0,1,1,0, Color.black),
				BorderFactory.createEmptyBorder(0,10,0,0)
		));

		this.setVisible(false);
	}

	// TODO replace Sting with layer data
	public void updatePanelContents(String s) {
		this.removeAll();
		this.setVisible(true);
		this.setPreferredSize(new Dimension(200, this.panelHeight));

		JLabel typeLabel = new JLabel("Type:");
		typeLabel.setFont(REGULAR_FONT);
		this.add(typeLabel);
		JComboBox<String> types = new JComboBox<>();
		types.setFont(REGULAR_FONT);
		this.add(types);

		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setFont(REGULAR_FONT);
		this.add(nameLabel);
		JLabel elementName = new JLabel(s);
		elementName.setFont(REGULAR_FONT);
		this.add(elementName);

		JLabel levelLabel = new JLabel("Level:");
		levelLabel.setFont(REGULAR_FONT);
		this.add(levelLabel);
		JComboBox<Integer> levels = new JComboBox<>();
		levels.setFont(REGULAR_FONT);
		this.add(levels);

		JLabel deleteButtonLabel = new JLabel("Actions:");
		deleteButtonLabel.setFont(REGULAR_FONT);
		this.add(deleteButtonLabel);
		JButton deleteButton = new JButton("Delete");
		deleteButton.setFont(REGULAR_FONT);
		this.add(deleteButton);

		this.revalidate();
	}
}
