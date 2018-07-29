package Utils;

import java.awt.Point;
import java.util.Random;

import Entities.Zombie;
import MainGame.Game;

public class GamePosition extends Point {

	private static final long serialVersionUID = -7305613390904433711L;

	public GamePosition(int x, int y) {
		super(x, y);
	}

	public GamePosition plus(GamePosition pt) {
		return new GamePosition(x + pt.x, y + pt.y);
	}

	public int distanceTo(GamePosition pt) {
		return (int) Math.sqrt(Math.pow(pt.x - x, 2) + Math.pow(pt.y - y, 2));
	}

	public GamePosition getRandomNearPosition(int blocks) {
		Random rnd = new Random();

		int orient;

		if (rnd.nextBoolean() == false)
			orient = -1;
		else
			orient = 1;

		GamePosition aux = this.plus(new GamePosition(blocks * 64 * orient, 0));
		if (!Game.getInstance().checkColision(new Zombie(aux)))
			return aux;
		else
			return this;

	}
	
}
