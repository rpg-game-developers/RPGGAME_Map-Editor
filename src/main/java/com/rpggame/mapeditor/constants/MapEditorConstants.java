package com.rpggame.mapeditor.constants;

import java.awt.Color;
import java.awt.Font;

public interface MapEditorConstants {

	// Size
	public final static int TILE_SIZE = 16;
	public final static int TILE_BORDER = 1;

	public static int PANEL_WIDTH_DIVIDER = 5;

	// Colors
	public static final Color PRIMARY = new Color(60, 63, 65);
	public final static Color DARK_BACKGROUND = new Color(33, 33, 33);
	public static final Color LIGHT_GRAY = new Color(169, 169, 169);
	public static final Color DARK_GRAY = new Color(50, 50, 50);
	public static final Color TRANSPARENT = new Color(0, 0, 0, 0);

	// Fonts
	public final static Font HEADER_FONT = new Font("Comic Sans", Font.BOLD, 20);
	public final static Font REGULAR_FONT = new Font("Comic Sans", Font.BOLD, 15);

}
