package Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Enums.ID;
import MainGame.Game;
import MainGame.GameObject;
import Tiles.Tile;
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

	private boolean playerNear, stuck;
	private GamePosition toMove;
	private Temporizer clock;

	public Entitie(GamePosition pt, BufferedImage texture) {
		super(pt, texture, ID.Entities);
		clock = new Temporizer();
		initAnims();
	}

	@Override
	public void update() {
		super.update();
		if (!stuck) {
			lookForPlayer();
			move();
		}
		anim();
		checkIfStuck();
	}

	private void lookForPlayer() {
		GamePosition pos = Game.getInstance().getPlayer().getPos();

		if (getPos().distanceTo(pos) <= Tile.SIZE * 5)
			playerNear = true;
		else
			playerNear = false;
	}

	private void move() {

		GamePosition aux = getPos().plus(new GamePosition(movX, 0));
		GameObject temp = new GameObject(aux, bounds, ID.Entities);

		if (colides(temp, Game.getInstance().getPlayer()))
			colideWithPlayer();
		else if (!Game.getInstance().checkColision(temp))
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

	private void checkIfStuck() {
		GamePosition tmp = getPos().getRandomNearPosition(1);
		if (tmp.equals(getPos())) {
			stuck = true;
			toMove = null;
			movX = 0;
		} else if (stuck) {
			stuck = false;
			toMove = tmp;
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
			toMove = getPos().getRandomNearPosition(4);
		else {
			if (toMove.x > pt.x)
				movX = speed;
			else if (toMove.x < pt.x)
				movX = -speed;
		}

		if (getPos().distanceToInX(toMove) < size) {
			if (clock.checkClock(1))
				toMove = null;
		}
	}

	@Override
	public void render(Graphics g) {
		if (current != null)
			current.drawAnimation(g, pt.x, pt.y, 0);
		else
			g.drawImage(idle, pt.x, pt.y, size, size, null);

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
