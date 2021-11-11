package com.rpggame.mapeditor.view.tileselector;

import static com.rpggame.mapeditor.constants.MapEditorConstants.TILE_SIZE;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.stream.IntStream;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.rpggame.mapeditor.controller.spritesheet.SpriteSheet;
import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.mapeditor.model.selector.SelectorListener;
import com.rpggame.mapeditor.model.tile.Tile;
import com.rpggame.mapeditor.model.tile.TileMap;
import com.rpggame.mapeditor.model.tile.TileSelector;

public class TileSelectorGrid extends JPanel {

	private static final long serialVersionUID = 1L;
	private final int padding = 2;
	private final int tileSize = TILE_SIZE * 3;
	private int columns;
	private int width;

	public TileSelectorGrid(int parentWidth, TileSelector tileSelector, Selector<TileMap> tileMapSelector) {
		this.width = parentWidth - 40;
		this.columns = width / tileSize;

		tileMapSelector.subscribe(newSelection -> {
			this.updateTiles(tileSelector, newSelection.getSpriteSheet());
		});

		TileMap selectedTileMap = tileMapSelector.getSelected();
		if (selectedTileMap != null)
			this.updateTiles(tileSelector, selectedTileMap.getSpriteSheet());
	}

	public void updateTiles(TileSelector tileSelector, SpriteSheet spriteSheet) {
		this.removeAll();
		this.revalidate();
		int rows = (tileSelector.size() + columns - 1)/columns;
		int height = (tileSize + padding) * rows;
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new BorderLayout());
		Tile[][] mapTiles = new Tile[rows][columns];

		for (int i = 0; i < tileSelector.size(); i++) {
			mapTiles[i/columns][i%columns] = tileSelector.get(i);
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
					if (hasFocus) {
						tileSelector.setSelected(((Tile) value));
					}
					return new SelectionTile(hasFocus, tileSize, ((Tile) value), spriteSheet);
				}
				JPanel p = new JPanel();
				return p;
			}
		};

		final JTable selection = new JTable(model);
		selection.setRowMargin(padding);
		selection.setRowHeight(tileSize + padding);
		selection.setShowGrid(false);
		selection.setTableHeader(null);
		selection.getColumnModel().setColumnMargin(padding);
		IntStream.range(0, selection.getColumnCount())
				.forEach(e -> selection.getColumnModel().getColumn(e).setMaxWidth(tileSize + padding));
		selection.setDefaultRenderer(Tile.class, renderer);

		this.add(selection);
		this.repaint();
	}
}
