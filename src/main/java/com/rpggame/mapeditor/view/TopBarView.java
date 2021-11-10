package com.rpggame.mapeditor.view;

import com.rpggame.mapeditor.controller.TopBarController;

import javax.swing.*;
import java.awt.*;

public class TopBarView extends JPanel {

	public TopBarView() {
		TopBarController topBarController = new TopBarController();
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createLineBorder(Color.black));

		JMenuBar fileDropdown = new JMenuBar();
		JMenu fileMenu = new JMenu("File");

		JMenuItem newItem = new JMenuItem("New");
		newItem.addActionListener(topBarController::newItemSelected);
		fileMenu.add(newItem);

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(topBarController::exitItemSelected);
		fileMenu.add(exitItem);

		JMenuBar helpDropdown = new JMenuBar();
		JMenu helpMenu = new JMenu("Help");

		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.addActionListener(topBarController::aboutItemSelected);
		helpMenu.add(aboutItem);

		fileDropdown.add(fileMenu);
		helpDropdown.add(helpMenu);
		this.add(fileDropdown);
		this.add(helpDropdown);
	}
}
