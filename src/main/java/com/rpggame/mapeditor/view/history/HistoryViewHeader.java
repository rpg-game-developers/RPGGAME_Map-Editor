package com.rpggame.mapeditor.view.history;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.rpggame.mapeditor.constants.MapEditorConstants;
import com.rpggame.mapeditor.view.utils.SmallCloseButton;

public class HistoryViewHeader extends JPanel {

	public HistoryViewHeader() {

		this.setLayout(new BorderLayout());
		this.setBackground(MapEditorConstants.DARK_BACKGROUND);
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(MapEditorConstants.TRANSPARENT);
		titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 15));
		titlePanel.add(new JLabel("History"));

		this.add(titlePanel, BorderLayout.WEST);

		this.add(new SmallCloseButton(), BorderLayout.EAST);

	}

}
