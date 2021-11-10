package com.rpggame.mapeditor.view.layerview;

import static com.rpggame.mapeditor.constants.MapEditorConstants.REGULAR_FONT;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.rpggame.mapeditor.controller.LayerPanelController;
import com.rpggame.mapeditor.view.MapEditorWindow;

public class LayerPanelItem extends JPanel {

	public LayerPanelItem(LayerPanelController controller, String tileName, int index) {
		JButton visibilityButton = new JButton();
		visibilityButton.addActionListener(controller::visibilityButtonClick);
		try {
			Image img = ImageIO.read(Objects
					.requireNonNull(MapEditorWindow.class.getResourceAsStream("/icons/non-visible-eye-symbol-50.png")));
//			Image img = ImageIO.read(Objects.requireNonNull(MapEditorWindow.class.getResourceAsStream(
//					"/icons/eye-symbol-50.png")));
			visibilityButton.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		if (index == 0) { // checking if it's the first element
			this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
		} else {
			this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.black));
		}
		JLabel tileNameLabel = new JLabel(tileName);
		tileNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		tileNameLabel.setFont(REGULAR_FONT);
		this.add(tileNameLabel, BorderLayout.WEST);
		this.add(visibilityButton, BorderLayout.EAST);
		this.setBackground(Color.lightGray);
	}

}
