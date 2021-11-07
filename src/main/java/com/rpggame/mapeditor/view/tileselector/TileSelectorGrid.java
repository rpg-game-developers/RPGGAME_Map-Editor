package com.rpggame.mapeditor.view.tileselector;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class TileSelectorGrid extends JPanel {

	private static final long serialVersionUID = 1L;
	private final int padding = 10;
	private final int columns = 3;
	private final int tileSize = 100;

	public TileSelectorGrid(int parentWidth, List<Color> tileList) {

		this.setPreferredSize(new Dimension(parentWidth - 30, (tileSize - (3 * padding)) * tileList.size()));
		this.setBackground(Color.orange);
		this.setLayout(new BorderLayout());
		JPanel s = new JPanel();
		s.setBackground(Color.green);
		BoxLayout layout = new BoxLayout(s, BoxLayout.PAGE_AXIS);
		s.setLayout(layout);

		int index = 0;
		for (int i = 0; i < tileList.size(); i = i + columns) {

			JPanel a = new JPanel();
			a.setBackground(Color.blue);
			a.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
			a.setLayout(new GridLayout(1, columns, padding, 0));
			for (int j = 0; j < columns; j++) {
				JPanel p = new JPanel();
				p.setPreferredSize(new Dimension(tileSize, tileSize));
				p.setMinimumSize(new Dimension(tileSize, tileSize));
				p.setMaximumSize(new Dimension(tileSize, tileSize));
				p.setBackground(tileList.get(index));
				index++;
				a.add(p);
			}
			s.add(a);

		}
		this.add(s);

	}

}
