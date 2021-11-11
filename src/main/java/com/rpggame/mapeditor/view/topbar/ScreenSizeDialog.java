package com.rpggame.mapeditor.view.topbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScreenSizeDialog extends JDialog {

	public ScreenSizeDialog() {
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);

		String[] resolutions = {"1280x720"};
		JComboBox<String> resolutionsComboBox = new JComboBox<>(resolutions);
		resolutionsComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resolutionsComboBox.getSelectedItem();
			}
		});

		this.setVisible(true);
	}
}
