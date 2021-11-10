package com.rpggame.mapeditor.view.layerview;

import static com.rpggame.mapeditor.constants.FrameVariables.FRAME_HEIGHT;
import static com.rpggame.mapeditor.constants.FrameVariables.FRAME_WIDTH;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.rpggame.mapeditor.controller.LayerPanelController;
import com.rpggame.mapeditor.model.MapTile;

public class LayerPanelList extends JPanel {

	public LayerPanelList(LayerPanelController layerPanelController, List<MapTile> loadedTiles) {
		String[] layerTiles = loadedTiles.stream().map(MapTile::getTileName).collect(Collectors.toList())
				.toArray(new String[0]);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		/*
		 * WRAP ALL OF IT WITH JSCROLLPAINER :) You need a JPanel with BoxLayout(this =
		 * container) this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS))
		 * 
		 * WHAT MY SLAVE HAS TO DO
		 * 
		 * make nice color add some colorized circle infront of text add a placeholder
		 * at the end (for visibility setting shit) :)
		 */

		JList<String> layers = new JList<>(layerTiles);
		layers.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		layers.setLayoutOrientation(JList.VERTICAL);
		layers.setVisibleRowCount(-1);
		layers.addListSelectionListener(layerPanelController::onItemSelected);

		JScrollPane layersScroller = new JScrollPane(layers);
		layersScroller.setPreferredSize(new Dimension(FRAME_WIDTH / 5, FRAME_HEIGHT / 2));
		this.add(layersScroller);
	}

}
