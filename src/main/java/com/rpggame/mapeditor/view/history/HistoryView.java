package com.rpggame.mapeditor.view.history;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;

import com.rpggame.mapeditor.constants.MapEditorConstants;

public class HistoryView extends JPanel {

	public HistoryView() {
		// this.setBorder(BorderFactory.createEmptyBorder(20, 50, 25, 15));
		this.setBorder(BorderFactory.createMatteBorder(20, 50, 25, 15, MapEditorConstants.PRIMARY));
		this.setBackground(MapEditorConstants.DARK_BACKGROUND);
		this.setLayout(new BorderLayout());

		String[] testData = { "test", "test2", "test3" };
		JList<String> actionList = new JList<>(testData);
		this.add(new HistoryViewHeader(), BorderLayout.NORTH);
		this.add(actionList, BorderLayout.CENTER);
		System.out.println(this.getSize() + " / " + this.getMaximumSize() + " / " + this.getMinimumSize());

	}

}
