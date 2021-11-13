package com.rpggame.mapeditor.view.layerview;

import static com.rpggame.mapeditor.constants.MapEditorConstants.REGULAR_FONT;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.rpggame.mapeditor.controller.LayerPanelController;
import com.rpggame.mapeditor.view.MapEditorWindow;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an item inside the LayerPanelList.
 */
public class LayerPanelItem extends JPanel {

	/**
	 * Creates a new Layer Panel item which consists of the layer that is added, and the visibility button.
	 * @param controller the LayerPanelController
	 * @param tileName the name of the layer that was added.
	 */
	public LayerPanelItem(@NotNull LayerPanelController controller, String tileName) {
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));

		JButton visibilityButton = new JButton();
		visibilityButton.addActionListener(controller::visibilityButtonClick);

		try {
			Image img = ImageIO.read(Objects
					.requireNonNull(MapEditorWindow.class.getResourceAsStream("/icons/eye-symbol-50.png")));
//			Image img = ImageIO.read(Objects.requireNonNull(MapEditorWindow.class.getResourceAsStream(
//					"/icons/eye-symbol-50.png")));
			visibilityButton.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		JLabel tileNameLabel = new JLabel(tileName);
		ArrayList<JButton> panelButtons = new ArrayList<>();
		panelButtons.add(visibilityButton);
		tileNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		tileNameLabel.setFont(REGULAR_FONT);

		this.add(tileNameLabel, BorderLayout.WEST);
		this.add(visibilityButton, BorderLayout.EAST);
		this.setBackground(Color.lightGray);
	}

}
