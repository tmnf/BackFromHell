package RenderManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import MainGame.Game;

public class Pause {

	public static void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", 1, 50));
		g.drawString("PAUSED", Game.Width / 2 - 135 + Game.getInstance().getCamera().getX(),
				Game.Height / 2 - 50 + Game.getInstance().getCamera().getY());
	}

}
