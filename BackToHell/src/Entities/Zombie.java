package Entities;

import java.awt.Rectangle;

import MainGame.Game;
import Utils.Animation;
import Utils.Assets;
import Utils.GamePosition;
import Utils.Temporizer;

public class Zombie extends Entitie {

	private Temporizer clock;
	private boolean NotTired;

	public Zombie(GamePosition pt) {
		super(pt, Assets.zombie);
		NotTired = true;
		clock = new Temporizer();
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(pt.x + 20, pt.y + 5, 24, size - 5);
	}

	@Override
	public void initAnims() {
		anim = new Animation(5, Assets.zombieLeft[0], Assets.zombieLeft[1], Assets.zombieLeft[0], Assets.zombieLeft[3]);
		anim2 = new Animation(5, Assets.zombieRight[0], Assets.zombieRight[1], Assets.zombieRight[0],
				Assets.zombieRight[3]);
		idle = Assets.zombie;
	}

	@Override
	public void colideWithPlayer() {
		if (clock.checkClock(1))
			NotTired = true;

		if (NotTired) {
			Game.getInstance().getPlayer().takeDamage(5);
			NotTired = false;
		}
	}

}
