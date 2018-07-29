package PlayerManagement;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Enums.State;
import MainGame.Game;
import Utils.Sound;

public class KeyInput extends KeyAdapter {

	private Player player1 = Game.getInstance().getPlayer();

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		State state = Game.getInstance().getState();

		// MOVE
		if (key == KeyEvent.VK_A)
			player1.movX = -player1.speed;
		if (key == KeyEvent.VK_D)
			player1.movX = player1.speed;

		// JUMP
		if (key == KeyEvent.VK_SPACE && !player1.getOnAir()) {
			player1.setMovY(-player1.jumpStrength);
			player1.setOnAir(true);
			Sound.playSound("sounds/jump.wav");
		}

		if (key == KeyEvent.VK_SHIFT)
			run();

		// PAUSE
		if (key == KeyEvent.VK_ESCAPE) {
			if (state.equals(State.Game))
				Game.getInstance().setState(State.Pause);
			else if (state.equals(State.Pause))
				Game.getInstance().setState(State.Game);
		}

		// FPS
		if (key == KeyEvent.VK_F) {
			if (Game.getInstance().gui.FpsShow)
				Game.getInstance().gui.FpsShow = false;
			else
				Game.getInstance().gui.FpsShow = true;
		}

	}

	private void run() {
		if (player1.movX > 0)
			player1.movX = player1.runningSpeed;
		if (player1.movX < 0)
			player1.movX = -player1.runningSpeed;
	}

	private void stopRunning() {
		if (player1.movX > 0)
			player1.movX = player1.speed;
		if (player1.movX < 0)
			player1.movX = -player1.speed;
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_A)
			player1.movX = 0;
		if (key == KeyEvent.VK_D)
			player1.movX = 0;

		if (key == KeyEvent.VK_SHIFT)
			stopRunning();

	}

}
