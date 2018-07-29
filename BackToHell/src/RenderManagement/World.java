package RenderManagement;

import java.util.Random;

import Entities.Zombie;
import MainGame.Game;
import MainGame.ObjectHandler;
import Tiles.Dirt;
import Tiles.Grass;
import Tiles.Rock;
import Tiles.Tile;
import Utils.GamePosition;

public class World {

	public static final int WorldLimit = Game.Width + 10000;
	public static final int HeightLimit = Game.Height + 10000;

	private ObjectHandler handler;
	private Random rnd;

	public World(ObjectHandler handler) {
		this.handler = handler;
		rnd = new Random();
	}

	public void terraFormer() {
		for (int a = 0; a <= WorldLimit; a += Tile.boundSize)
			for (int b = Game.Height / 2; b <= HeightLimit; b += Tile.boundSize)
				if (b <= Game.Height + 256)
					formGrass(a, b);
				else
					formLand(a, b);

		spawnMonsters();
	}

	private void formLand(int a, int b) {
		if (rnd.nextBoolean())
			handler.addObject(new Dirt(new GamePosition(a, b)));
		else
			handler.addObject(new Rock(new GamePosition(a, b)));
	}

	private void formGrass(int a, int b) {
		if (b <= Game.Height + 64 && rnd.nextInt(10) == 9)
			return;
		else
			handler.addObject(new Grass(new GamePosition(a, b)));
	}

	private void spawnMonsters() {
		int i = 0;
		int numberOfMonsters = 15;
		Random rnd = new Random();

		while (i != numberOfMonsters) {
			handler.addObject(new Zombie(new GamePosition(rnd.nextInt(WorldLimit), Game.Height / 2 - 100)));
			i++;
		}

	}

}
