package com.rpggame.mapeditor.view.layerview;

import com.rpggame.mapeditor.controller.LayerPanelController;
import com.rpggame.mapeditor.model.LayerRow;
import com.rpggame.mapeditor.model.MapTile;
import com.rpggame.mapeditor.view.MapEditorWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

import static com.rpggame.mapeditor.constants.FrameVariables.FRAME_HEIGHT;
import static com.rpggame.mapeditor.constants.FrameVariables.FRAME_WIDTH;
import static com.rpggame.mapeditor.constants.MapEditorConstants.REGULAR_FONT;

public class LayerPanelList extends JPanel {

	public LayerPanelList(LayerPanelController layerPanelController, List<MapTile> loadedTiles) {
		String[] layerTiles = loadedTiles.stream().map(MapTile::getTileName).toArray(String[]::new);
		this.setLayout(new BorderLayout());
		DefaultListModel<LayerRow> layersModel = new DefaultListModel<>();
		for(String layer : layerTiles) {
			layersModel.addElement(new LayerRow(layer));
		}
		JList<LayerRow> layers = new JList<>(layersModel);
		layers.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
			JPanel layerPanel = new JPanel();
			JButton visibilityButton = new JButton();
			visibilityButton.addActionListener(layerPanelController::visibilityButtonClick);
			try {
				Image img = ImageIO.read(Objects.requireNonNull(MapEditorWindow.class.getResourceAsStream(
						"/icons/non-visible-eye-symbol-50.png")));
//				Image img = ImageIO.read(Objects.requireNonNull(MapEditorWindow.class.getResourceAsStream(
//						"/icons/eye-symbol-50.png")));
				visibilityButton.setIcon(new ImageIcon(img));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			layerPanel.setLayout(new BorderLayout());
			layerPanel.setOpaque(false);
			if(index == 0) { // checking if it's the first element
				layerPanel.setBorder(BorderFactory.createMatteBorder(0,0,0,1, Color.black));
			} else {
				layerPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.black));
			}
			JLabel tileName = new JLabel(value.getTileName());
			tileName.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
			tileName.setFont(REGULAR_FONT);
			layerPanel.add(tileName, BorderLayout.WEST);
			layerPanel.add(visibilityButton, BorderLayout.EAST);
			layerPanel.setBackground(Color.lightGray);
			return layerPanel;
		});
		layers.setFixedCellHeight(FRAME_HEIGHT/20);
		layers.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		layers.setLayoutOrientation(JList.VERTICAL);
		layers.setVisibleRowCount(-1);
		layers.addListSelectionListener(layerPanelController::onItemSelected);

		JScrollPane layersScroller = new JScrollPane(layers);
		layersScroller.getVerticalScrollBar().setPreferredSize(new Dimension(FRAME_WIDTH/85, Integer.MAX_VALUE));
		layersScroller.getHorizontalScrollBar().setPreferredSize(new Dimension(FRAME_WIDTH/85, Integer.MAX_VALUE));
		layersScroller.setBorder(BorderFactory.createMatteBorder(1,1,1,0, Color.black));
		layersScroller.setPreferredSize(new Dimension(FRAME_WIDTH / 5, FRAME_HEIGHT / 2));
		this.add(layersScroller);
	}

}
