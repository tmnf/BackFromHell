package RenderManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Enums.State;
import Interfaces.Renderable;
import MainGame.Game;
import Utils.Assets;
import Utils.Temporizer;

public class LoadingScreen implements Renderable {

	private int loaded, fill;
	private Temporizer clock;

	public LoadingScreen() {
		clock = new Temporizer();
	}

	@Override
	public void render(Graphics g) {
		if (loaded != 100)
			fill = loaded * 6;

		g.drawImage(Assets.loadingScreen, 0, 0, null);

		g.setFont(new Font("Arial", 1, 30));
		g.setColor(Color.WHITE);
		g.drawString(loaded + "%", 880, 650);

		g.setFont(new Font("Arial", 1, 15));
		g.drawString("Press F during the game to see your frame rate!", 40, 40);

		g.setColor(Color.red);
		g.fillRect(172, 569, fill, 83);

		refreshLoading();
	}

	private void refreshLoading() {
		if (loaded >= 100) {
			loaded = 100;
			fill = 660;
			if (clock.checkClock(2))
				Game.getInstance().setState(State.Game);
		}

		else if (clock.checkClock(1))
			loaded += 15;

	}

}
