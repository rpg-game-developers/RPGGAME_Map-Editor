package com.rpggame.mapeditor.view.layerview;

import com.rpggame.mapeditor.controller.LayerPanelController;
import com.rpggame.mapeditor.model.selector.Selector;
import com.rpggame.mapeditor.model.selector.SelectorListener;
import com.rpggame.mapeditor.model.tile.TileMap;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.rpggame.mapeditor.constants.FrameVariables.FRAME_HEIGHT;
import static com.rpggame.mapeditor.constants.FrameVariables.FRAME_WIDTH;

/**
 * Creates the list of all the layers added by the user.
 */
public class LayerPanelList extends JPanel {

	private final LayerPanelController layerPanelController;
	private final Selector<TileMap> tileMapSelector;

	/**
	 * Creates the initial list and subscribes to the onListChange event to update the list when a
	 * new layer is added.
	 * @param layerPanelController the controller for the layer panel.
	 * @param tileMapSelector the tilemap selector.
	 */
	public LayerPanelList(LayerPanelController layerPanelController, @NotNull Selector<TileMap> tileMapSelector) {
		this.tileMapSelector = tileMapSelector;
		this.layerPanelController = layerPanelController;
		updateList(tileMapSelector.getList());
		tileMapSelector.subscribe(new SelectorListener<>() {
			@Override
			public void onListChange(List<TileMap> newSelection) {
				updateList(newSelection);
			}
		});
	}

	/**
	 * Updates the list of all the layers when a new layer gets added.
	 * @param tileMaps the tile maps added to the list.
	 */
	public void updateList(@NotNull List<TileMap> tileMaps) {
		// TODO: Think of a better way so the list is not recreated everytime but updated instead
		this.removeAll();
		this.revalidate();
		List<LayerPanelItem> layerPanels = new ArrayList<>();
		this.setLayout(new BorderLayout());
		DefaultListModel<TileMap> layersModel = new DefaultListModel<>();
		for(TileMap tileMap : tileMaps) {
			layersModel.addElement(tileMap);
		}
		JList<TileMap> layers = new JList<>(layersModel);
		layers.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
			LayerPanelItem layerPanelItem = new LayerPanelItem(layerPanelController, value.getName());
			layerPanels.add(layerPanelItem);
			return layerPanelItem;
		});
		layers.setFixedCellHeight(FRAME_HEIGHT/20);
		layers.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		layers.setLayoutOrientation(JList.VERTICAL);
		layers.setVisibleRowCount(-1);
		layers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				int index = layers.locationToIndex(event.getPoint());
				List<Component> components = Arrays.stream(layerPanels.get(index).getComponents()).
						filter(component -> component instanceof JButton).collect(Collectors.toList());
				JButton button = (JButton) components.get(0);
				button.doClick();
			}
		});
		layers.addListSelectionListener(layerPanelController::onItemSelected);

		JScrollPane layersScroller = new JScrollPane(layers);
		layersScroller.getVerticalScrollBar().setPreferredSize(new Dimension(FRAME_WIDTH/85, Integer.MAX_VALUE));
		layersScroller.getHorizontalScrollBar().setPreferredSize(new Dimension(FRAME_WIDTH/85, Integer.MAX_VALUE));
		layersScroller.setBorder(BorderFactory.createMatteBorder(1,1,1,0, Color.black));
		layersScroller.setPreferredSize(new Dimension(FRAME_WIDTH / 5, FRAME_HEIGHT / 2));
		this.add(layersScroller);
		this.repaint();
	}

	/**
	 * Getter for tileMapSelector.
	 * @return tileMapSelector.
	 */
	public Selector<TileMap> getTileMapSelector() {
		return tileMapSelector;
	}
}
