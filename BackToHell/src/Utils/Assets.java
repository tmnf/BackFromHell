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

	private static Animation creatAnim(BufferedImage[] animFrames, int speed) {
		switch (animFrames.length) {
		case 2:
			return new Animation(speed, animFrames[0], animFrames[1]);
		case 3:
			return new Animation(speed, animFrames[0], animFrames[1], animFrames[2]);
		case 4:
			return new Animation(speed, animFrames[0], animFrames[1], animFrames[2], animFrames[4]);
		case 5:
			return new Animation(speed, animFrames[0], animFrames[1], animFrames[2], animFrames[3], animFrames[4]);
		case 6:
			return new Animation(speed, animFrames[0], animFrames[1], animFrames[2], animFrames[3], animFrames[4],
					animFrames[5]);
		case 7:
			return new Animation(speed, animFrames[0], animFrames[1], animFrames[2], animFrames[3], animFrames[4],
					animFrames[5], animFrames[6]);
		case 8:
			return new Animation(speed, animFrames[0], animFrames[1], animFrames[2], animFrames[4], animFrames[5],
					animFrames[6], animFrames[7]);
		case 9:
			return new Animation(speed, animFrames[0], animFrames[1], animFrames[2], animFrames[4], animFrames[5],
					animFrames[6], animFrames[7], animFrames[8]);
		case 10:
			return new Animation(speed, animFrames[0], animFrames[1], animFrames[3], animFrames[4], animFrames[5],
					animFrames[6], animFrames[7], animFrames[8], animFrames[9]);
		case 11:
			return new Animation(speed, animFrames[0], animFrames[1], animFrames[2], animFrames[3], animFrames[4],
					animFrames[5], animFrames[6], animFrames[7], animFrames[8], animFrames[9], animFrames[10]);
		case 12:
			return new Animation(speed, animFrames[0], animFrames[1], animFrames[2], animFrames[3], animFrames[4],
					animFrames[5], animFrames[6], animFrames[7], animFrames[8], animFrames[9], animFrames[10],
					animFrames[11]);
		default:
			return null;
		}
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

		playerIdleRight = creatAnim(ImageHandler.loadAnim("res/animations/playerIdleRight.anim"), 2);
		playerIdleLeft = creatAnim(ImageHandler.loadAnim("res/animations/playerIdleLeft.anim"), 2);
		playerRunRight = creatAnim(ImageHandler.loadAnim("res/animations/playerRunRight.anim"), 2);
		playerRunLeft = creatAnim(ImageHandler.loadAnim("res/animations/playerRunLeft.anim"), 2);
		System.out.println("Sucesso");
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
