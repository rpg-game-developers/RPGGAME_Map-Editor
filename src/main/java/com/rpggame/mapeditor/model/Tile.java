package com.rpggame.mapeditor.model;

public class Tile {
	private final int sheetRow;
	private final int sheetColumn;

	public Tile(int sheetRow, int sheetColumn) {
		this.sheetColumn = sheetColumn;
		this.sheetRow = sheetRow;
	}

	public int getSheetColumn() {
		return sheetColumn;
	}
	
	public int getSheetRow() {
		return sheetRow;
	}

}
