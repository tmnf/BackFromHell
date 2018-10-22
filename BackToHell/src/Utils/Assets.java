package Utils;

import java.awt.image.BufferedImage;

public class Assets {

	public static int width = 16, height = 16;

	// Player
	public static BufferedImage player, playerFacingLeft, playerFacingRight;
	public static BufferedImage background, single, singlepress, multi, multipressed, exit, exitpress;

	public static BufferedImage[] playerIdle = FileLoader.loadFile("./res/animations/playerIdle.anim");
	public static BufferedImage[] playerRight = FileLoader.loadFile("./res/animations/playerRight.anim");
	public static BufferedImage[] playerLeft = new BufferedImage[4];
	//

	// GUI
	public static BufferedImage lifebar;

	// Loading
	public static BufferedImage loadingScreen;

	// Tiles
	public static BufferedImage grass, dirt, sky, rock;
	//

	// Entities
	public static BufferedImage zombie;
	public static BufferedImage[] zombieRight = new BufferedImage[4];
	public static BufferedImage[] zombieLeft = new BufferedImage[4];
	//

	private static SpriteSheet playerTile;
	private static SpriteSheet zombieTile;
	// private static SpriteSheet skeletonTile;
	private static SpriteSheet tiles;

	public static void init() {
		playerTile = new SpriteSheet(FileLoader.loadImage("/sprites/player.png"));
		tiles = new SpriteSheet(FileLoader.loadImage("/sprites/tiles.png"));
		zombieTile = new SpriteSheet(FileLoader.loadImage("/sprites/zombie.png"));
		// skeletonTile = new
		// SpriteSheet(ImageLoader.loadImage("/sprites/skeleton.png"));

		startPlayer();
		startTiles();
		startEntities();
		getMenu();
		getGUI();
	}

	private static void getGUI() {
		lifebar = FileLoader.loadImage("/images/lifebar.png");

		// Loading
		loadingScreen = FileLoader.loadImage("/images/loading.png");
	}

	public static void getMenu() {
		background = FileLoader.loadImage("/images/background.png");
		single = FileLoader.loadImage("/images/singlebutton.png");
		singlepress = FileLoader.loadImage("/images/singlepressed.png");
		multi = FileLoader.loadImage("/images/multi.png");
		multipressed = FileLoader.loadImage("/images/multipressed.png");
		exit = FileLoader.loadImage("/images/exit.png");
		exitpress = FileLoader.loadImage("/images/exitpressed.png");
	}

	public static void startPlayer() {
		player = playerTile.crop(0, 0, width, height);

		playerLeft[0] = playerTile.crop(0, 3 * height, width, height);
		playerLeft[1] = playerTile.crop(width, 3 * height, width, height);
		playerLeft[2] = playerTile.crop(0, 3 * height, width, height);
		playerLeft[3] = playerTile.crop(width * 2, 3 * height, width, height);
	}

	public static void startEntities() {

		int width = 30;
		int height = 40;

		zombie = zombieTile.crop(width, 0, width, height);
		zombieRight[0] = zombieTile.crop(0, 2 * height, width, height);
		zombieRight[1] = zombieTile.crop(width, 2 * height, width, height);
		zombieRight[2] = zombieTile.crop(0, 2 * height, width, height);
		zombieRight[3] = zombieTile.crop(width * 2, 2 * height + 1, width, height);

		zombieLeft[0] = zombieTile.crop(0, height, width, height);
		zombieLeft[1] = zombieTile.crop(width, height, width, height);
		zombieLeft[2] = zombieTile.crop(0, height, width, height);
		zombieLeft[3] = zombieTile.crop(width * 2, height, width, height);
	}

	public static void startTiles() {
		grass = tiles.crop(width * 5 + 5, 0, width, height);
		dirt = tiles.crop(width * 6 + 6, 0, width, height);
		sky = FileLoader.loadImage("/images/sky.png");
		rock = tiles.crop(7 * width + 7, 0, width, height);
	}

}
