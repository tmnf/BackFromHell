package MainGame;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import Enums.State;
import PlayerManagement.KeyInput;
import PlayerManagement.MouseInput;
import PlayerManagement.Player;
import RenderManagement.Display;
import RenderManagement.GameCamera;
import RenderManagement.LoadingScreen;
import RenderManagement.Menu;
import RenderManagement.Pause;
import RenderManagement.World;
import Utils.Assets;
import Utils.GamePosition;
import Utils.PhysicalBody;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -4846506186674452075L;

	public final static int Width = 1000, Height = Width / 12 * 9;

	private boolean running;
	private Thread thread;
	private static Game INSTANCE;

	private ObjectHandler handler;
	private World world;
	private LoadingScreen loading;
	private GameCamera gameCamera;
	private Player player1;
	public GUI gui;

	public int fps;

	// ESTADOS//
	private State currentState;
	private Menu menu;

	public Game() {
		FunctionsInit();
		DisplayInit();
	}

	public void DisplayInit() {
		INSTANCE = this;
		new Display(Width, Height, "BackFromHell");
		this.addKeyListener(new KeyInput());
		this.addMouseListener(new MouseInput(menu));
	}

	public void FunctionsInit() {
		Assets.init();
		loading = new LoadingScreen();
		// Sound.playSoundLoop("sounds/backmusic.wav", 1);
		loading.refreshLoading();

		menu = new Menu();
		gui = new GUI();
		handler = new ObjectHandler();
		gameCamera = new GameCamera(0, 0);
		player1 = new Player(new GamePosition(0, Height / 2 - 100));
		handler.addObject(player1);
		loading.refreshLoading();

		world = new World(handler);
		world.terraFormer();
		loading.refreshLoading();

		currentState = State.Menu;
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				delta--;
			}
			if (running)
				render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				fps = frames;
				frames = 0;
			}
		}
	}

	public void update() {
		if (currentState.equals(State.Game))
			handler.update();
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		Graphics g = null;
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		Graphics2D g2 = (Graphics2D) g;

		// =====================================================//
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// MENU
		if (currentState.equals(State.Menu))
			menu.render(g);
		//

		// Loading
		else if (currentState.equals(State.Loading))
			loading.render(g);
		//

		// JOGO
		else if (currentState.equals(State.Game) || currentState.equals(State.Pause)) {
			g.drawImage(Assets.sky, 0, 0, null);
			gui.render(g);

			g2.translate(-gameCamera.getX(), -gameCamera.getY());
			handler.render(g);
		}

		if (currentState.equals(State.Pause))
			Pause.render(g);
		//

		// ==================================================//
		bs.show();
		g.dispose();
	}

	public boolean isInsideCamera(GamePosition pos) {
		if (pos.x >= gameCamera.getX() - 64 && pos.x <= gameCamera.getX() + Width + 64
				&& pos.y >= gameCamera.getY() - 64 && pos.y <= gameCamera.getY() + Height + 64)
			return true;
		return false;
	}

	public boolean checkColision(GameObject ob) {
		if (isOutsideScreen(ob))
			return true;

		for (GameObject aux : handler.objects)
			if (PhysicalBody.colides(ob, aux) && (aux.getId() != ob.getId()))
				return true;
		return false;
	}

	private boolean isOutsideScreen(GameObject ob) {
		if (ob.getPos().x >= 0 && ob.getPos().x <= World.WorldLimit - 64)
			return false;
		return true;
	}

	public GameObject objectOnPos(GamePosition pos) {
		for (GameObject aux : handler.objects)
			if (aux.getBounds().contains(pos))
				return aux;
		return null;
	}

	public GameCamera getCamera() {
		return gameCamera;
	}

	public ObjectHandler getHandler() {
		return handler;
	}

	public Player getPlayer() {
		return player1;
	}

	public static Game getInstance() {
		return INSTANCE;
	}

	public void setState(State state) {
		currentState = state;
	}

	public State getState() {
		return currentState;
	}

	public static void main(String[] args) {
		new Game();
	}

}
