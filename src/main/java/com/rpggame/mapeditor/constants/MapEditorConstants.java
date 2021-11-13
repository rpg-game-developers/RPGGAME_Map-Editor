package com.rpggame.mapeditor.constants;

import java.awt.Color;
import java.awt.Font;

import com.rpggame.mapeditor.model.tile.TileMap;
import com.rpggame.mapeditor.view.MapEditorWindow;
import com.rpggame.spritesheet.SpriteSheet;
import com.rpggame.spritesheet.SpriteSheetBuilder;

/**
 * Saves all constants related to the map editor.
 */
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

	// Sprite Sheets
	public final static SpriteSheet GROUND_SPRITE_SHEET = new SpriteSheetBuilder()
			.withSheet(MapEditorWindow.class, "/spriteAssets/rogueLikeSheet_transparent.png").withColumns(26)
			.withRows(18).build(TILE_SIZE, TILE_BORDER);
	public final static SpriteSheet FURNITURE_SPRITE_SHEET = new SpriteSheetBuilder()
			.withSheet(MapEditorWindow.class, "/spriteAssets/testSpriteSheet.png").withColumns(26).withRows(18).build(TILE_SIZE, TILE_BORDER);

	// TileMaps
	TileMap GROUND = new TileMap("ground", GROUND_SPRITE_SHEET, 100, 100);
	TileMap FURNITURE = new TileMap("furniture", FURNITURE_SPRITE_SHEET, 100, 100);

}
