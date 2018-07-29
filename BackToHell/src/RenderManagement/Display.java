package RenderManagement;

import java.awt.Canvas;

import javax.swing.JFrame;

import MainGame.Game;
import Utils.ImageLoader;

public class Display extends Canvas {

	private static final long serialVersionUID = 2129026719218242034L;

	public Display(int width, int height, String title) {
		JFrame frame = new JFrame(title);

		frame.setIconImage(ImageLoader.loadImage("/images/incontemp.png"));
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(Game.getInstance());
		Game.getInstance().start();
		Game.getInstance().requestFocus();
	}

}
