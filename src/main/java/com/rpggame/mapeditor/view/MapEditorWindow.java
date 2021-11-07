package com.rpggame.mapeditor.view;

import javax.swing.*;
import java.awt.*;

public class MapEditorWindow {

	public MapEditorWindow() {
		buildAndShowView();
	}

	public void buildAndShowView() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLocationRelativeTo(null);

		JPanel root = new JPanel();
		root.setLayout(new BorderLayout());
		root.add(new TileSelectorView(), BorderLayout.EAST);
		root.add(new MapEditingPanel(), BorderLayout.CENTER);
		root.add(new LayerPanelView(), BorderLayout.WEST);

		frame.add(root);
		frame.setVisible(true);
	}

}
