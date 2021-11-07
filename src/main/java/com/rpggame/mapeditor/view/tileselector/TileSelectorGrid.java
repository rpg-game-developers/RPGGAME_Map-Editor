package com.rpggame.mapeditor.view.tileselector;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class TileSelectorGrid extends JPanel {

	private final int padding = 20;
	private final int columns = 3;

	public TileSelectorGrid(int parentWidth, List<Color> tileList) {

//		this.setPreferredSize(new Dimension(parentWidth, 51 * parentWidth/3 + this.padding * 51));
//		this.setBackground(Color.orange);
//		GridBagLayout layout = new GridBagLayout();
//
//		this.setLayout(layout);
//
//		GridBagConstraints c = new GridBagConstraints();
//
//		int index = 0;
//
//		for (int i = 0; i < columns; i++) {
//			for (int j = 0; j < tileList.size() / columns; j++) {
//				c.weightx = 0.1;
//				c.weighty = 0.1;
//				c.gridx = i;
//				c.gridy = j;
//				JPanel a = new JPanel();
//				a.setPreferredSize(new Dimension(140, 140));
//				a.setMinimumSize(new Dimension(140, 140));
//				a.setMaximumSize(new Dimension(140, 140));
//				a.setBackground(tileList.get(index));
//
//				this.add(a, c);
//				index++;
//			}
//
//		}

		this.setPreferredSize(new Dimension(parentWidth, 50 * 200));
		this.setBackground(Color.orange);
		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		//TODO problem -> `this` panel seems to add some kind of default padding with any layout + children not taking the full size
		this.setLayout(layout);


		int index = 0;
		for (int i = 0; i < tileList.size(); i = i + columns) {

			JPanel a = new JPanel();
			a.setBackground(Color.blue);
			a.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
			a.setPreferredSize(new Dimension(parentWidth, 0));
			a.setMinimumSize(new Dimension(parentWidth, 0));
			a.setLayout(new BoxLayout(a, BoxLayout.LINE_AXIS));
			for (int j = 0; j < columns; j++) {
				JPanel p = new JPanel();
				p.setPreferredSize(new Dimension(150, 140));
				p.setMinimumSize(new Dimension(150, 140));
				p.setMaximumSize(new Dimension(150, 140));
				p.setBackground(tileList.get(index));
				p.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.black));
				index++;
				a.add(p);
			}
			this.add(a);

		}

	}

}
