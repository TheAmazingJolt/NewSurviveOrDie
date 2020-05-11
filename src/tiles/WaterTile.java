package tiles;

import gfx.Assets;

public class WaterTile extends Tile{
	public WaterTile(int id) {
		super(Assets.waterTile, id);
	}
	
	public boolean isDeadly() {
		return false;
	}
	
	public float speedMultiplier() {
		return 0.75f;
	}
	
}
