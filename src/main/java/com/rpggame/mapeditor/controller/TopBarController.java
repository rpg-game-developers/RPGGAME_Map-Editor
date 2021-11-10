package com.rpggame.mapeditor.controller;

import com.rpggame.mapeditor.view.topbar.AboutDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TopBarController {

	public void newItemSelected(ActionEvent e) {

	}

	public void exitItemSelected(ActionEvent e) {
		JMenuItem source = (JMenuItem) e.getSource();
		JPopupMenu popupMenu = (JPopupMenu) source.getParent();
		JFrame frame = (JFrame) SwingUtilities.getRoot(popupMenu.getInvoker());
		frame.dispose();
	}

	public void aboutItemSelected(ActionEvent e) {
		new AboutDialog();
	}
}
