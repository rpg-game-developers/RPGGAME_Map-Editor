package com.rpggame.mapeditor.controller;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import com.rpggame.mapeditor.view.history.HistoryView;
import com.rpggame.mapeditor.view.tileselector.TileSelectorView;
import com.rpggame.mapeditor.view.topbar.AboutDialog;
import com.rpggame.mapeditor.view.topbar.WindowSizeChangeDialog;

public class TopBarController {

	private final TileSelectorView tileSelectorView;
	private final HistoryView historyView;
	private final Consumer<String> updateWindowSize;

	public TopBarController(Consumer<String> updateWindowSize, TileSelectorView tileSelectorView, HistoryView historyView) {
		this.updateWindowSize = updateWindowSize;
		this.tileSelectorView = tileSelectorView;
		this.historyView = historyView;
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

	public void showTileSelectionItemSelected() {
		if(!tileSelectorView.isVisible()) {
			tileSelectorView.setVisible(true);
		}
	}

	public void showHistorySelected() {
		if(!historyView.isVisible()) {
			historyView.setVisible(true);
		}
	}
}
