package Utils;

import java.awt.image.BufferedImage;

import Enums.ID;
import Interfaces.Updatable;
import MainGame.Game;
import MainGame.GameObject;
import PlayerManagement.Player;

public class PhysicalBody extends GameObject implements Updatable {

	public static int gravForce = 5;
	protected int movY;
	protected boolean onAir;

	public PhysicalBody(GamePosition pt, BufferedImage texture, ID id) {
		super(pt, texture, id);
		movY = gravForce;
	}

	private void grav() {
		GamePosition pos = getPos().plus(new GamePosition(0, movY));
		GameObject ob = null;

		if (this instanceof Player)
			ob = new Player(pos);
		else
			ob = new GameObject(pos, getBounds(), ID.Entities);

		if (!Game.getInstance().checkColision(ob)) {
			setPosition(pos);
			movY++;
		} else {
			movY = gravForce;
			onAir = false;
		}
	}

	@Override
	public void update() {
		grav();
	}

	public void setMovY(int y) {
		movY = y;
	}

	public void setOnAir(boolean jumping) {
		onAir = jumping;
	}

	public boolean getOnAir() {
		return onAir;
	}

	public static boolean colides(GameObject ob1, GameObject ob2) {
		if (ob1.getBounds().intersects(ob2.getBounds()))
			return true;
		return false;
	}

}
