package com.rpggame.mapeditor.view.topbar;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.rpggame.mapeditor.controller.TopBarController;

public class TopBarView extends JPanel {

	public TopBarView(TopBarController topBarController) {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createLineBorder(Color.black));

		JMenuBar fileDropdown = new JMenuBar();
		JMenu fileMenu = new JMenu("File");

		JMenuItem newItem = new JMenuItem("New");
		newItem.addActionListener(topBarController::newItemSelected);
		fileMenu.add(newItem);

		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.addActionListener(topBarController::saveItemSelected);
		fileMenu.add(saveItem);

		JMenuItem loadItem = new JMenuItem("Load");
		loadItem.addActionListener(topBarController::loadItemSelected);
		fileMenu.add(loadItem);

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(topBarController::exitItemSelected);
		fileMenu.add(exitItem);

		JMenuBar viewDropdown = new JMenuBar();
		JMenu viewMenu = new JMenu("View");

		JMenuItem changeWindowSizeItem = new JMenuItem("Change window size");
		changeWindowSizeItem.addActionListener(topBarController::openWindowSizeDialog);
		viewMenu.add(changeWindowSizeItem);

		JMenuItem showTileSelectionItem = new JMenuItem("Show TileSelection");
		showTileSelectionItem.addActionListener(e -> topBarController.showTileSelectionItemSelected());
		viewMenu.add(showTileSelectionItem);

		JMenuItem showHistoryViewItem = new JMenuItem("Show History");
		showHistoryViewItem.addActionListener(e -> topBarController.showHistorySelected());
		viewMenu.add(showHistoryViewItem);

		JMenuBar helpDropdown = new JMenuBar();
		JMenu helpMenu = new JMenu("Help");

		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.addActionListener(topBarController::aboutItemSelected);
		helpMenu.add(aboutItem);

		fileDropdown.add(fileMenu);
		viewDropdown.add(viewMenu);
		helpDropdown.add(helpMenu);
		this.add(fileDropdown);
		this.add(viewDropdown);
		this.add(helpDropdown);
	}

}
