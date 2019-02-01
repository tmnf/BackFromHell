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
	//

	private static SpriteSheet playerTile;
	private static SpriteSheet zombieTile;
	// private static SpriteSheet skeletonTile;
	private static SpriteSheet tiles;

	public static void init() {
		startTiles();

//		startPlayer();

//		startEntities();
		getMenu();
		getGUI();
	}

	private static void getGUI() {
		lifebar = ImageHandler.loadImage("/images/GUI/lifebar.png");

		// Loading
		loadingScreen = ImageHandler.loadImage("/images/GUI/loading.png");
	}

	public static void getMenu() {
		background = ImageHandler.loadImage("/images/MenuImages/background.png");
		single = ImageHandler.loadImage("/images/MenuImages/singlebutton.png");
		singlepress = ImageHandler.loadImage("/images/MenuImages/singlepressed.png");
		multi = ImageHandler.loadImage("/images/MenuImages/multi.png");
		multipressed = ImageHandler.loadImage("/images/MenuImages/multipressed.png");
		exit = ImageHandler.loadImage("/images/MenuImages/exit.png");
		exitpress = ImageHandler.loadImage("/images/MenuImages/exitpressed.png");
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

	}

	public static void startTiles() {
		tiles = new SpriteSheet(ImageHandler.loadImage("/sprites/tiles.png"));

		grass = tiles.crop(width * 5 + 5, 0, width, height);
		dirt = tiles.crop(width * 6 + 6, 0, width, height);
		sky = ImageHandler.loadImage("/images/GUI/sky.png");
		rock = tiles.crop(7 * width + 7, 0, width, height);
	}

}
