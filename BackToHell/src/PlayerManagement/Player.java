package PlayerManagement;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Enums.ID;
import MainGame.Game;
import Utils.Animation;
import Utils.Assets;
import Utils.GamePosition;
import Utils.PhysicalBody;

public class Player extends PhysicalBody {

	// =========ANIMAÇÕES==============//
	private Animation anim, anim2, current;
	private BufferedImage idle;
	// ================================//

	public int movX, life, level;
	public int speed = 8, jumpStrength = 15, runningSpeed = 14;

	private static final int size = 64;
	private static final int MaxHealth = 100;

	public Player(GamePosition pt) {
		super(pt, Assets.player, size, size, ID.Player);
		initAnims();
		life = MaxHealth;
		level = 1;
	}

	public void move() {
		GamePosition pos = getPos().plus(new GamePosition(movX, 0));
		if (!Game.getInstance().checkColision(new Player(pos)))
			setPosition(pos);
	}

	@Override
	public void update() {
		super.update();
		Game.getInstance().getCamera().centerOnEntity(this);
		move();
		anim();
	}

	@Override
	public void render(Graphics g) {
		if (current != null)
			current.drawAnimation(g, pt.x, pt.y, 0);
		else
			g.drawImage(idle, pt.x, pt.y, size, size, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(pt.x + 20, pt.y + 5, 24, boundSizeY - 5);
	}

	public void initAnims() {
		anim = new Animation(5, Assets.playerLeft[0], Assets.playerLeft[1], Assets.playerLeft[0], Assets.playerLeft[3]);
		anim2 = new Animation(5, Assets.playerRight[0], Assets.playerRight[1], Assets.playerRight[0],
				Assets.playerRight[3]);
		idle = Assets.player;
	}

	public void anim() {
		if (movX < 0) {
			current = anim;
			idle = Assets.playerFacingLeft;
		} else if (movX > 0) {
			current = anim2;
			idle = Assets.playerFacingRight;
		} else
			current = null;

		if (current != null)
			current.runAnimation();
	}

	public void takeDamage(int i) {
		life -= i;
	}

}
