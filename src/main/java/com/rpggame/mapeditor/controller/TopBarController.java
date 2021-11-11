package com.rpggame.mapeditor.controller;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import com.rpggame.mapeditor.view.topbar.AboutDialog;
import com.rpggame.mapeditor.view.topbar.WindowSizeChangeDialog;

public class TopBarController {

	

	private final Consumer<String> updateWindowSize;

	public TopBarController(Consumer<String> updateWindowSize) {
		this.updateWindowSize = updateWindowSize;
	}

	public void newItemSelected(ActionEvent e) {

	}

	public void exitItemSelected(ActionEvent e) {
		JMenuItem source = (JMenuItem) e.getSource();
		JPopupMenu popupMenu = (JPopupMenu) source.getParent();
		JFrame frame = (JFrame) SwingUtilities.getRoot(popupMenu.getInvoker());
		frame.dispose();
	}

	public void openWindowSizeDialog(ActionEvent e) {
		new WindowSizeChangeDialog(this.updateWindowSize);
	}

	public void aboutItemSelected(ActionEvent e) {
		new AboutDialog();
	}
}
