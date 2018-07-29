package MainGame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Enums.ID;
import Interfaces.Renderable;
import Utils.GamePosition;

public class GameObject implements Renderable {

	protected GamePosition pt;
	protected BufferedImage texture;
	protected int boundSizeX, boundSizeY;
	protected ID id;

	public GameObject(GamePosition pt, BufferedImage texture, int boundSizeX, int boundSizeY, ID id) {
		this.pt = pt;
		this.texture = texture;
		this.boundSizeX = boundSizeX;
		this.boundSizeY = boundSizeY;
		this.id = id;
	}

	public void render(Graphics g) {
		g.drawImage(texture, pt.x, pt.y, boundSizeX, boundSizeY, null);
	}

	public GamePosition getPos() {
		return pt;
	}

	public void setPosition(GamePosition pos) {
		pt = pos;
	}

	public ID getId() {
		return id;
	}

	public Rectangle getBounds() {
		return new Rectangle(pt.x, pt.y, boundSizeX, boundSizeY);
	}

}
