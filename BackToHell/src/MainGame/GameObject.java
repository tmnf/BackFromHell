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
	protected Rectangle bounds;
	protected ID id;

	public GameObject(GamePosition pt, BufferedImage texture, ID id) {
		this.pt = pt;
		this.texture = texture;
		this.bounds = getBounds();
		this.id = id;
	}

	public GameObject(GamePosition pt, Rectangle bounds, ID id) {
		this.pt = pt;
		this.bounds = bounds;
		this.id = id;
	}

	public void render(Graphics g) {
		g.drawImage(texture, pt.x, pt.y, bounds.width, bounds.height, null);
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

	// Corrigir bug sobreposto
	public Rectangle getBounds() {
		return new Rectangle(pt.x + 20, pt.y + 5, 24, 64 - 5);
	}

}
