package com.rpggame.mapeditor.model;

public class MapTile {

	private String tileType;
	private final String tileName;
	private int level;
	private final int sheetRow;
	private final int sheetColumn;

	public MapTile(String tileType, String tileName, int level, int sheetRow, int sheetColumn) {
		this.tileType = tileType;
		this.tileName = tileName;
		this.level = level;
		this.sheetColumn = sheetColumn;
		this.sheetRow = sheetRow;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setTileType(String tileType) {
		this.tileType = tileType;
	}

	public String getTileType() {
		return tileType;
	}

	public String getTileName() {
		return tileName;
	}

	public int getLevel() {
		return level;
	}

	public int getSheetColumn() {
		return sheetColumn;
	}
	
	public int getSheetRow() {
		return sheetRow;
	}

}
