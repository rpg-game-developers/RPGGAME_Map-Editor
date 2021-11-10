package com.rpggame.mapeditor.view.history;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import com.rpggame.mapeditor.model.HistoryAction;
import com.rpggame.mapeditor.view.MapEditorWindow;

public class ActionCellRenderer extends JPanel implements ListCellRenderer<HistoryAction> {

	public ActionCellRenderer() {
		this.setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends HistoryAction> list, HistoryAction value, int index,
			boolean isSelected, boolean cellHasFocus) {
		System.out.println("value: "+value.getActionName());
		
		this.setLayout(new BorderLayout());
		JLabel actionDisplay = new JLabel(value.getActionName());

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
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			//this.add(picLabel, BorderLayout.WEST);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.add(actionDisplay, BorderLayout.CENTER);

		return this;
	}

}
