package com.rpggame.mapeditor;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.rpggame.mapeditor.view.MapEditorWindow;

import javax.swing.*;

public class RpgGameMapEditorStandalone {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatDarculaLaf());
		} catch( Exception ex ) {
			System.err.println( "Failed to initialize LaF" );
		}
		new MapEditorWindow();
	}
	
}
