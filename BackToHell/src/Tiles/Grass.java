package Tiles;

import Utils.Assets;
import Utils.GamePosition;

public class Grass extends Tile {

	private boolean isSolid = true;

	public Grass(GamePosition pt) {
		super(pt, Assets.grass);
	}

	@Override
	public boolean isSolid() {
		return isSolid;

	}
}
