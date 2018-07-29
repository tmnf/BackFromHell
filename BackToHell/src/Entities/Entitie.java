package Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Enums.ID;
import MainGame.Game;
import Utils.Animation;
import Utils.GamePosition;
import Utils.PhysicalBody;
import Utils.Temporizer;

public abstract class Entitie extends PhysicalBody {

	protected static final int size = 64;

	// =========ANIMAÇÕES==============//
	protected Animation anim, anim2, current;
	protected BufferedImage idle;
	// ================================//

	private int movX, jumpForce = -15;
	private int speed = 1;

	private boolean playerNear;
	private GamePosition toMove;
	private Temporizer clock;

	public Entitie(GamePosition pt, BufferedImage texture) {
		super(pt, texture, size, size, ID.Entities);
		clock = new Temporizer();
	}

	@Override
	public void update() {
		super.update();
		checkPlayer();
		move();
		anim();
	}

	private void checkPlayer() {
		GamePosition pos = Game.getInstance().getPlayer().getPos();
		int tileSize = 64;
		if (getPos().distanceTo(pos) <= tileSize * 5)
			playerNear = true;
		else
			playerNear = false;
	}

	private void move() {

		GamePosition aux = getPos().plus(new GamePosition(movX, 0));

		if (colides(new Zombie(aux), Game.getInstance().getPlayer()))
			colideWithPlayer();
		else if (!Game.getInstance().checkColision(new Zombie(aux)))
			setPosition(aux);
		else
			jump();

		if (playerNear) {
			followPlayer();
			toMove = null;
		} else
			randomMove();
	}

	private void jump() {
		if (!onAir) {
			setMovY(jumpForce);
			onAir = true;
		}
	}

	private void followPlayer() {
		GamePosition pos = Game.getInstance().getPlayer().getPos();
		if (pos.x > pt.x)
			movX = speed;
		else if (pos.x < pt.x)
			movX = -speed;
	}

	private void randomMove() {

		if (toMove == null)
			toMove = getPos().getRandomNearPosition(1);
		else {
			if (toMove.x > pt.x)
				movX = speed;
			else if (toMove.x < pt.x)
				movX = -speed;
		}

		if (getPos().distanceTo(toMove) <= 3) {
			if (clock.checkClock(2))
				toMove = null;
			movX = 0;
		}
	}

	@Override
	public void render(Graphics g) {
		if (current != null)
			current.drawAnimation(g, pt.x, pt.y, 0);
		else
			g.drawImage(idle, pt.x, pt.y, size, size, null);

		g.drawRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
	}

	public abstract void initAnims();

	public abstract void colideWithPlayer();

	public void anim() {
		if (movX < 0)
			current = anim;
		else if (movX > 0)
			current = anim2;
		else
			current = null;

		if (current != null)
			current.runAnimation();
	}

}
