package Utils;

import java.awt.image.BufferedImage;

public class Assets {

	public static int width = 16, height = 16;

	// Player
	public static BufferedImage player, playerFacingLeft, playerFacingRight;
	public static BufferedImage background, single, singlepress, multi, multipressed, exit, exitpress;

	// PlayerAnims
	public static Animation playerIdleRight;
	public static Animation playerIdleLeft;
	public static Animation playerRunRight;
	public static Animation playerRunLeft;
	public static Animation playerWalkRight;
	public static Animation playerWalkLeft;
	public static Animation playerJumpLeft;
	public static Animation playerJumpRight;
	public static Animation playerDeathLeft;
	public static Animation playerDeathRight;
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
		playerTile = new SpriteSheet(ImageHandler.loadImage("/sprites/player.png"));
		tiles = new SpriteSheet(ImageHandler.loadImage("/sprites/tiles.png"));
		zombieTile = new SpriteSheet(ImageHandler.loadImage("/sprites/zombie.png"));
		// skeletonTile = new
		// SpriteSheet(ImageLoader.loadImage("/sprites/skeleton.png"));
		startPlayer();
		startTiles();
		startEntities();
		getMenu();
		getGUI();
	}

	private static void getGUI() {
		lifebar = ImageHandler.loadImage("/images/lifebar.png");

		// Loading
		loadingScreen = ImageHandler.loadImage("/images/loading.png");
	}

	public static void getMenu() {
		background = ImageHandler.loadImage("/images/background.png");
		single = ImageHandler.loadImage("/images/singlebutton.png");
		singlepress = ImageHandler.loadImage("/images/singlepressed.png");
		multi = ImageHandler.loadImage("/images/multi.png");
		multipressed = ImageHandler.loadImage("/images/multipressed.png");
		exit = ImageHandler.loadImage("/images/exit.png");
		exitpress = ImageHandler.loadImage("/images/exitpressed.png");
	}

	public static void startPlayer() {
		player = playerTile.crop(0, 0, width, height);

		playerIdleRight = Animation.creatAnim(ImageHandler.loadAnim("res/animations/playerAnims/playerIdleRight.anim"),
				3);
		playerIdleLeft = Animation.creatAnim(ImageHandler.loadAnim("res/animations/playerAnims/playerIdleLeft.anim"),
				3);
		playerRunRight = Animation.creatAnim(ImageHandler.loadAnim("res/animations/playerAnims/playerRunRight.anim"),
				3);
		playerRunLeft = Animation.creatAnim(ImageHandler.loadAnim("res/animations/playerAnims/playerRunLeft.anim"), 3);
		playerWalkLeft = Animation.creatAnim(ImageHandler.loadAnim("res/animations/playerAnims/playerWalkLeft.anim"),
				3);
		playerWalkRight = Animation.creatAnim(ImageHandler.loadAnim("res/animations/playerAnims/playerWalkRight.anim"),
				3);
		playerJumpLeft = Animation.creatAnim(ImageHandler.loadAnim("res/animations/playerAnims/playerJumpLeft.anim"),
				3);
		playerJumpRight = Animation.creatAnim(ImageHandler.loadAnim("res/animations/playerAnims/playerJumpRight.anim"),
				3);
		playerDeathLeft = Animation.creatAnim(ImageHandler.loadAnim("res/animations/playerAnims/playerDeathLeft.anim"),
				3);
		playerDeathRight = Animation
				.creatAnim(ImageHandler.loadAnim("res/animations/playerAnims/playerDeathRight.anim"), 3);
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
		sky = ImageHandler.loadImage("/images/sky.png");
		rock = tiles.crop(7 * width + 7, 0, width, height);
	}

}
