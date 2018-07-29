package Tiles;

import Utils.Assets;
import Utils.GamePosition;

public class Dirt extends Tile {

	private boolean isSolid = true;
	
	public Dirt(GamePosition pt) {
		super(pt, Assets.dirt);
	}

	
	@Override
	public boolean isSolid() {
		return isSolid;
	}
	
	

}
