package RenderManagement;

import MainGame.GameObject;
import MainGame.Game;

public class GameCamera {

	private int x, y;

	public GameCamera(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void centerOnEntity(GameObject e) {
		if (e.getPos().x - Game.Width / 2 >= 0 && e.getPos().x + Game.Width / 2 <= World.WorldLimit)
			x = e.getPos().x - Game.Width / 2;
		y = e.getPos().y - Game.Height / 2;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
