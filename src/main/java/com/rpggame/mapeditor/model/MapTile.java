package com.rpggame.mapeditor.model;

public class MapTile {

	private String tileType;
	private final String tileName;
	private int level;
	private final String imagePath;

	public MapTile(String tileType, String tileName, int level, String imagePath) {
		this.tileType = tileType;
		this.tileName = tileName;
		this.level = level;
		this.imagePath = imagePath;
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

	public String getImagePath() {
		return imagePath;
	}

}
