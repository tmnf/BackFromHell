package Tiles;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Enums.ID;
import MainGame.GameObject;
import Utils.GamePosition;

public abstract class Tile extends GameObject {

	public static final int SIZE = 64;

	public Tile(GamePosition pt, BufferedImage texture) {
		super(pt, texture, ID.Tile);
	}

	public abstract boolean isSolid();

	@Override
	public Rectangle getBounds() {
		return new Rectangle(pt.x, pt.y, SIZE, SIZE);
	}

}
