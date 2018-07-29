package MainGame;

import java.awt.Graphics;
import java.util.LinkedList;

import Interfaces.Updatable;

public class ObjectHandler {

	public LinkedList<GameObject> objects = new LinkedList<>();
	public LinkedList<GameObject> buffer = new LinkedList<>();

	public void update() {
		for (GameObject ob : objects)
			if (ob instanceof Updatable && Game.getInstance().isInsideCamera(ob.getPos()))
				((Updatable) ob).update();
	}

	public void render(Graphics g) {
		objects.removeAll(buffer);
		buffer.clear();

		for (GameObject ob : objects)
			if (Game.getInstance().isInsideCamera(ob.getPos()))
				ob.render(g);
	}

	public void addObject(GameObject e) {
		objects.add(e);
	}

	public void removeObject(GameObject e) {
		buffer.add(e);
	}

}
