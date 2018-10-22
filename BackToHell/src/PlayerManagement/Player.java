package PlayerManagement;

import java.awt.Graphics;
import java.awt.Rectangle;

import Enums.ID;
import MainGame.Game;
import Utils.Animation;
import Utils.Assets;
import Utils.GamePosition;
import Utils.PhysicalBody;

public class Player extends PhysicalBody {

	// =========ANIMAÇÕES==============//
	private Animation anim, anim2, idle, current;
	// ================================//

	public int movX, life, level;
	public int speed = 8, jumpStrength = 15, runningSpeed = 14;

	private static final int size = 64;
	private static final int MaxHealth = 100;

	public Player(GamePosition pt) {
		super(pt, Assets.player, ID.Player);
		initAnims();
		life = MaxHealth;
		level = 1;
	}

	public void move() {
		GamePosition pos = getPos().plus(new GamePosition(movX, 0));
		if (!Game.getInstance().checkColision(new Player(pos)))
			setPosition(pos);
	}

	@Override
	public void update() {
		super.update();
		Game.getInstance().getCamera().centerOnEntity(this);
		move();
		anim();
	}

	@Override
	public void render(Graphics g) {
		current.drawAnimation(g, pt.x, pt.y, 0);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(pt.x + 20, pt.y + 5, 24, size - 5);
	}

	public void initAnims() {
		anim = new Animation(5, Assets.playerLeft[0], Assets.playerLeft[1], Assets.playerLeft[0], Assets.playerLeft[3]);
		anim2 = new Animation(2, Assets.playerRight[0], Assets.playerRight[1], Assets.playerRight[2],
				Assets.playerRight[3], Assets.playerRight[4], Assets.playerRight[5], Assets.playerRight[6],
				Assets.playerRight[7]);
		idle = new Animation(2, Assets.playerIdle[0], Assets.playerIdle[1], Assets.playerIdle[2], Assets.playerIdle[3],
				Assets.playerIdle[4], Assets.playerIdle[5], Assets.playerIdle[6], Assets.playerIdle[7],
				Assets.playerIdle[8], Assets.playerIdle[9]);
		;
	}

	public void anim() {
		if (movX < 0) {
			current = anim;
		} else if (movX > 0) {
			current = anim2;
		} else
			current = idle;

		current.runAnimation();
	}

	public synchronized void takeDamage(int i) {
		life -= i;
	}

}
