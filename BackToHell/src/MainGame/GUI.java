package MainGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Interfaces.Renderable;
import Utils.Assets;

public class GUI implements Renderable {

	public boolean FpsShow;

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);

		// Life
		g.setColor(Color.RED);
		g.fillRect(23, 20, Game.getInstance().getPlayer().life * 2 - 8, 28);
		g.drawImage(Assets.lifebar, 19, 19, null);

		// Strings
		g.setFont(new Font("Arial", 1, 15));

		// Level
		g.setColor(Color.WHITE);
		g.drawString("Level: " + Game.getInstance().getPlayer().level, 20, 70);

		// FPS
		if (FpsShow)
			g.drawString("Fps: " + Game.getInstance().fps, Game.Width - 100, 20);
	}

}
