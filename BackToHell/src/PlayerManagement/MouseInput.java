package PlayerManagement;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Enums.State;
import MainGame.Game;
import MainGame.GameObject;
import RenderManagement.Menu;
import Utils.Assets;
import Utils.GamePosition;
import Utils.Sound;

public class MouseInput implements MouseListener {

	private State state;
	private Menu menu;

	public MouseInput(Menu menu) {
		state = Game.getInstance().getState();
		this.menu = menu;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		state = Game.getInstance().getState();

		if (state.equals(State.Game)) {
			GameObject temp = Game.getInstance()
					.objectOnPos(new GamePosition(e.getX() + Game.getInstance().getCamera().getX(),
							e.getY() + Game.getInstance().getCamera().getY()));
			if (temp != null) {
				Game.getInstance().getHandler().removeObject(temp);
				Sound.playSound("sounds/break.wav");
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		state = Game.getInstance().getState();

		if (state.equals(State.Menu)) {
			if (e.getX() >= 63 && e.getX() <= 63 + 376)
				if (e.getY() >= 274 && e.getY() <= 274 + 86) // SinglePlayer
					menu.setBt1(Assets.singlepress);
				else if (e.getY() >= 410 && e.getY() <= 410 + 86) // MultiPlayer
					System.out.println("Not Avaible Yet");
				else if (e.getY() >= 538 && e.getY() <= 538 + 86) // Exit
					menu.setBt3(Assets.exitpress);
			Sound.playSound("sounds/clickmenu.wav");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		state = Game.getInstance().getState();

		if (state.equals(State.Menu)) {
			if (e.getX() >= 63 && e.getX() <= 63 + 376)
				if (e.getY() >= 274 && e.getY() <= 274 + 86) // SinglePlayer
					Game.getInstance().setState(State.Loading);
				else if (e.getY() >= 410 && e.getY() <= 410 + 86) // MultiPlayer
					System.out.println("Not Avaible Yet");
				else if (e.getY() >= 538 && e.getY() <= 538 + 86) // Exit
					System.exit(0);
		}
	}

}
