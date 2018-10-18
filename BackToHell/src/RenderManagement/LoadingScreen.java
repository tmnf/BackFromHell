package RenderManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Enums.State;
import Interfaces.Renderable;
import MainGame.Game;
import Utils.Assets;

public class LoadingScreen implements Renderable {

	public static final int PROCESSES = 4;
	public static final int fullLoaded = 660;

	private int loaded, fill;

	@Override
	public void render(Graphics g) {

		fill = (fullLoaded / PROCESSES) * loaded;

		g.drawImage(Assets.loadingScreen, 0, 0, null);

		g.setFont(new Font("Arial", 1, 30));
		g.setColor(Color.WHITE);
		g.drawString(loaded + "%", 880, 650);

		g.setFont(new Font("Arial", 1, 15));
		g.drawString("Press F during the game to see your frame rate!", 40, 40);

		g.setColor(Color.red);
		g.fillRect(172, 569, fill, 83);

		if (loaded != 100)
			Game.getInstance().setState(State.Game);
	}

	public void refreshLoading() {
		loaded++;
	}

}
