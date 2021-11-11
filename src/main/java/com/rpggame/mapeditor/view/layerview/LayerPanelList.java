package com.rpggame.mapeditor.view.layerview;

import com.rpggame.mapeditor.controller.LayerPanelController;
import com.rpggame.mapeditor.model.LayerRow;
import com.rpggame.mapeditor.model.tile.TileMap;

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

public class LayerPanelList extends JPanel {

	public LayerPanelList(LayerPanelController layerPanelController, List<TileMap> loadedMaps) {
		List<LayerPanelItem> layerPanels = new ArrayList<>();
		String[] layerTiles = loadedMaps.stream().map(TileMap::getName).toArray(String[]::new);
		this.setLayout(new BorderLayout());
		DefaultListModel<LayerRow> layersModel = new DefaultListModel<>();
		for(String layer : layerTiles) {
			layersModel.addElement(new LayerRow(layer));
		}
		JList<LayerRow> layers = new JList<>(layersModel);
		layers.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
			LayerPanelItem layerPanelItem = new LayerPanelItem(layerPanelController, value.getTileName(), index);
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
	}

}
