import java.io.Serializable;


public class Map implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 570366463465046839L;
	// initialize Map fields

	private int width; // number of tiles wide
	private int height; // number of tiles high

	Tile[][] TileSet; // array to hold tiles and form level map

	public Map(int setWidth, int setHeight,
			boolean isVisible, boolean isWall) { // Map constructor
		width = setWidth;
		height = setHeight;

		TileSet = new Tile[width][height]; // initialize TileSet to suit Map
											// size
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				TileSet[x][y] = new Tile();
				TileSet[x][y].setVisible(isVisible);
				TileSet[x][y].setWall(isWall);
			}
		}
	}

	public int getWidth() { // return Map width
		return width;
	}

	public int getHeight() { // return Map height
		return height;
	}

	public void changeTile(int tileX, int tileY, byte bSkin, byte bHealth,
			boolean isDestructible, boolean isWall, boolean isVisible) {
		TileSet[tileX][tileY].setSkin(bSkin);
		TileSet[tileX][tileY].setHealth(bHealth);
		TileSet[tileX][tileY].setDestructible(isDestructible);
		TileSet[tileX][tileY].setWall(isWall);
		TileSet[tileX][tileY].setVisible(isVisible);
	}

}
