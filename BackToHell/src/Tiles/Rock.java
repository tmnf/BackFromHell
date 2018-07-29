package Tiles;

import Utils.Assets;
import Utils.GamePosition;

public class Rock extends Tile {

	private boolean isSolid = true;

	public Rock(GamePosition pt) {
		super(pt, Assets.rock);
	}

	@Override
	public boolean isSolid() {
		return isSolid;
	}

}
