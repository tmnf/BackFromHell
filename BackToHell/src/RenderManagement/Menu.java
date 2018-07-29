package RenderManagement;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Utils.Assets;

public class Menu {

	public void setBt1(BufferedImage bt1) {
		this.bt1 = bt1;
	}

	public void setBt2(BufferedImage bt2) {
		this.bt2 = bt2;
	}

	public void setBt3(BufferedImage bt3) {
		this.bt3 = bt3;
	}

	private BufferedImage bt1, bt2, bt3;

	public Menu() {
		bt1 = Assets.single;
		bt2 = Assets.multi;
		bt3 = Assets.exit;
	}

	public void render(Graphics g) {

		g.drawImage(Assets.background, 0, 0, null);

		g.drawImage(bt1, 63, 274, null);
		g.drawImage(bt2, 63, 410, null);
		g.drawImage(bt3, 63, 538, null);
	}

}
