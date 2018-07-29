package Tiles;

import java.awt.image.BufferedImage;

import Enums.ID;
import MainGame.GameObject;
import Utils.GamePosition;

public abstract class Tile extends GameObject{

	public final static int boundSize = 64;
	
	public Tile(GamePosition pt, BufferedImage texture) {
		super(pt, texture, boundSize, boundSize, ID.Tile);
	}

	public abstract boolean isSolid();

}
