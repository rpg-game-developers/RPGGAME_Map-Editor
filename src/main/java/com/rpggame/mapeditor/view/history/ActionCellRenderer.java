package com.rpggame.mapeditor.view.history;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.CompoundBorder;

import com.rpggame.mapeditor.constants.MapEditorConstants;
import com.rpggame.mapeditor.model.HistoryAction;
import com.rpggame.mapeditor.view.MapEditorWindow;
import com.rpggame.spritesheet.utils.ImageHelper;

public class ActionCellRenderer implements ListCellRenderer<HistoryAction> {

	@Override
	public Component getListCellRendererComponent(JList<? extends HistoryAction> list, HistoryAction value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());
		JLabel actionDisplay = new JLabel(value.getActionName());
		panel.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, MapEditorConstants.DARK_GRAY),
				BorderFactory.createEmptyBorder(5, 10, 5, 0)));
		String iconPath = "";
		switch (value.getActionType()) {
		case ADD:
			iconPath = "plus";
			break;

		case EDIT:
			iconPath = "wrench";
			break;

		default:
			iconPath = "minus"; // or garbage
			break;
		}

		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(MapEditorWindow.class.getResourceAsStream("/icons/" + iconPath + ".png"));
			JLabel picLabel = new JLabel(new ImageIcon(ImageHelper.getImageWithMultipliedSize(myPicture, 0.2)));
			picLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
			panel.add(picLabel, BorderLayout.WEST);
		} catch (IOException e) {
			e.printStackTrace();
		}

		panel.add(actionDisplay, BorderLayout.CENTER);

		return panel;
	}

}
