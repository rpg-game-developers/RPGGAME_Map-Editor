package com.rpggame.mapeditor.view.tileselector;

import static com.rpggame.mapeditor.constants.MapEditorConstants.TILE_SIZE;

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
import com.rpggame.mapeditor.model.Tile;

public class TileSelectorGrid extends JPanel {

	private static final long serialVersionUID = 1L;
	private final int padding = 5;
	private int columns = 0;
	private final int tileSize = TILE_SIZE * 3;

	public TileSelectorGrid(int parentWidth, List<Tile> tileList, SpriteSheet spriteSheet) {
		int width = parentWidth - 40;
		columns = width / tileSize;
		int rows = (tileList.size() + columns - 1)/columns;
		int height = (tileSize + padding) * rows + padding*2;
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new BorderLayout());
		Tile[][] mapTiles = new Tile[rows][columns];

		int rowCount = 0;
		int columnCount = 0;
		for (int i = 0; i < tileList.size(); i++) {
			mapTiles[columnCount][rowCount] = tileList.get(i);

			if (rowCount >= columns - 1) {
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
				return Tile.class;
			}

		};
		final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (value != null) {
					return new SelectionTile(tileSize, ((Tile) value), spriteSheet);
				}
				JPanel p = new JPanel();
				return p;
			}
		};

		final JTable selection = new JTable(model);
		selection.setRowMargin(5);
		selection.setRowHeight(tileSize + 5);
		selection.setShowGrid(false);
		selection.setTableHeader(null);
		selection.getColumnModel().setColumnMargin(5);
		IntStream.range(0, selection.getColumnCount())
				.forEach(e -> selection.getColumnModel().getColumn(e).setMaxWidth(tileSize + 5));
		selection.setDefaultRenderer(Tile.class, renderer);

		final JScrollPane rightSelectionList = new JScrollPane(selection);
		rightSelectionList.setMaximumSize(new Dimension((tileSize + 5) * columns, height));
		// this.add(s);
		this.add(rightSelectionList);
	}

}
