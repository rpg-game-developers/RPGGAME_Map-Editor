package com.rpggame.mapeditor.view.tileselector;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;
import java.util.stream.IntStream;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.rpggame.mapeditor.controller.spritesheet.SpriteSheet;
import com.rpggame.mapeditor.model.MapTile;

public class TileSelectorGrid extends JPanel {

	private static final long serialVersionUID = 1L;
	private final int padding = 10;
	private final int columns = 6;
	private final int tileSize = 100;

	public TileSelectorGrid(int parentWidth, List<MapTile> tileList, SpriteSheet spriteSheet) {

		this.setPreferredSize(new Dimension(parentWidth - 30, (tileSize - (columns * padding)) * tileList.size()));
		this.setLayout(new BorderLayout());
		MapTile[][] mapTiles = new MapTile[columns][tileList.size()];

		int rowCount = 0;
		int columnCount = 0;
		for (int i = 0; i < tileList.size(); i++) {
			mapTiles[columnCount][rowCount] = tileList.get(i);

			if (rowCount >= columns) {
				rowCount = -1;
				columnCount++;
			}

			rowCount++;

		}

		final DefaultTableModel model = new DefaultTableModel(mapTiles,
				IntStream.range(0, columns).mapToObj(e -> "").toArray(String[]::new)) {

			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return MapTile.class;
			}
		};
		final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (value != null) {
					return new SelectionTile(tileSize, ((MapTile) value), spriteSheet);
				}
				JPanel p = new JPanel();
				return p;
			}
		};

		final JTable selection = new JTable(model);
		selection.setRowMargin(5);
		selection.setRowHeight(64);
		selection.setShowGrid(false);
		selection.setTableHeader(null);
		selection.getColumnModel().setColumnMargin(5);
		IntStream.range(0, selection.getColumnCount())
				.forEach(e -> selection.getColumnModel().getColumn(e).setMaxWidth(64));
		selection.setDefaultRenderer(MapTile.class, renderer);

		final JScrollPane rightSelectionList = new JScrollPane(selection);
		rightSelectionList.setMaximumSize(new Dimension(64 * columns, 10000));
		// this.add(s);
		this.add(rightSelectionList);
	}

}
