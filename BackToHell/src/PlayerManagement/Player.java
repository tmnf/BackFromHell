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

	// =========ANIMAÇÕES=============================================//
	private Animation idleLeft, idleRight, RunLeft, RunRight, WalkLeft, WalkRight, JumpLeft, JumpRight, DeathLeft,
			DeathRight, current;
	// ===============================================================//

	public int movX, life, level;
	public int speed = 8, jumpStrength = 15, runningSpeed = 14, wasMoving = 0;

	private static final int size = 64;
	private static final int MaxHealth = 100;

	public boolean running;

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
		idleLeft = Assets.playerIdleLeft;
		idleRight = Assets.playerIdleRight;
		RunLeft = Assets.playerRunLeft;
		RunRight = Assets.playerRunRight;
		WalkLeft = Assets.playerWalkLeft;
		WalkRight = Assets.playerWalkRight;
		JumpLeft = Assets.playerJumpLeft;
		JumpRight = Assets.playerJumpRight;
		DeathLeft = Assets.playerDeathLeft;
		DeathRight = Assets.playerDeathRight;
	}

	public void anim() {
		if (movX < 0) {
			if (running)
				current = RunLeft;
			else if (getOnAir())
				current = JumpLeft;
			else
				current = WalkLeft;
			wasMoving = -1;
		} else if (movX > 0) {
			if (running)
				current = RunRight;
			else if (getOnAir())
				current = JumpRight;
			else
				current = WalkRight;
			wasMoving = 1;
		} else {
			if (wasMoving < 0) {
				if (getOnAir())
					current = JumpLeft;
				else
					current = idleLeft;
			} else {
				if (getOnAir())
					current = JumpRight;
				else
					current = idleRight;
			}
		}

		current.runAnimation();
	}

	public synchronized void takeDamage(int i) {
		life -= i;
	}

}
