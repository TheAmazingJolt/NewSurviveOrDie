package gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Assets {

	private static final int width = 64;
	private static final int height = 64;
	
	public static Font font20;
	public static Font font28;
	public static Font font30;
	public static Font font35;
	public static Font font40;
	public static Font font50;
	
	public static BufferedImage inventory;
	public static BufferedImage hotbar;
	
	public static BufferedImage dirtTile;
	public static BufferedImage grassTile;
	public static BufferedImage wallTile;
	
	public static BufferedImage woodItem;
	public static BufferedImage stoneItem;
	public static BufferedImage axeItem;
	public static BufferedImage cutWoodItem;
	public static BufferedImage flintItem;
	public static BufferedImage ironOreItem;
	public static BufferedImage ironBarItem;
	public static BufferedImage fireItem;
	public static BufferedImage woodenHandleItem;
	public static BufferedImage ironAxeItem;;
	public static BufferedImage rottenFleshItem;
	
	public static BufferedImage[] playerUp;
	public static BufferedImage[] playerDown;
	public static BufferedImage[] playerRight;
	public static BufferedImage[] playerLeft;
	
	public static BufferedImage[] zombieUp;
	public static BufferedImage[] zombieDown;
	public static BufferedImage[] zombieRight;
	public static BufferedImage[] zombieLeft;
	
	public static void init() {
		ImageIO.setCacheDirectory(new File("res/cache"));

		font20 = FontLoader.loadFont("res/fonts/motioncontrol.otf", 20F);
		font28 = FontLoader.loadFont("res/fonts/motioncontrol.otf", 28F);
		font30 = FontLoader.loadFont("res/fonts/motioncontrol.otf", 30F);
		font35 = FontLoader.loadFont("res/fonts/motioncontrol.otf", 35F);
		font40 = FontLoader.loadFont("res/fonts/motioncontrol.otf", 40F);
		font50 = FontLoader.loadFont("res/fonts/motioncontrol.otf", 50F);
		
		SpriteSheet items = new SpriteSheet(ImageLoader.loadImage("/textures/sprites/items.png"));
		SpriteSheet tiles = new SpriteSheet(ImageLoader.loadImage("/textures/sprites/tiles.png"));
		SpriteSheet zombieAnim = new SpriteSheet(ImageLoader.loadImage("/textures/sprites/zombieAnimation.png"));
		SpriteSheet playerAnim = new SpriteSheet(ImageLoader.loadImage("/textures/sprites/playerAnimation.png"));
		
		inventory = ImageLoader.loadImage("/textures/screens/inventoryScreen.png");
		hotbar = ImageLoader.loadImage("/textures/screens/hotbar.png");
		
		//tiles
		dirtTile = tiles.crop(0, 0, width, height);
		grassTile = tiles.crop(width, 0, width, height);
		wallTile = tiles.crop(width * 2, 0, width, height);
		
		//items
		////row 1
		cutWoodItem = items.crop(0, 0, width, height);
		axeItem = items.crop(width, 0, width, height);
		ironBarItem = items.crop(width * 2, 0, width, height);
		flintItem = items.crop(width * 3, 0, width, height);
		ironAxeItem = items.crop(width * 4, 0, width, height);
		rottenFleshItem = items.crop(width * 5, 0, width, height);
		////row 2
		stoneItem = items.crop(0, height, width, height);
		woodItem = items.crop(width, height, width, height);
		fireItem = items.crop(width * 2, height, width, height);
		ironOreItem = items.crop(width * 3, height, width, height);
		woodenHandleItem = items.crop(width * 4, height, width, height);
		
		//entities
		playerDown = new BufferedImage[2];
		playerDown[0] = playerAnim.crop(0, 0, width, height);
		playerDown[1] = playerAnim.crop(width, 0, width, height);
		
		playerUp = new BufferedImage[2];
		playerUp[0] = playerAnim.crop(width * 2, 0, width, height);
		playerUp[1] = playerAnim.crop(width * 3, 0, width, height);
		
		playerRight = new BufferedImage[2];
		playerRight[0] = playerAnim.crop(0, height, width, height);
		playerRight[1] = playerAnim.crop(width, height, width, height);
		
		playerLeft = new BufferedImage[2];
		playerLeft[0] = playerAnim.crop(width * 2, height, width, height);
		playerLeft[1] = playerAnim.crop(width * 3, height, width, height);
		
		zombieDown = new BufferedImage[2];
		zombieDown[0] = zombieAnim.crop(0, 0, width, height);
		zombieDown[1] = zombieAnim.crop(width, 0, width, height);
		
		zombieUp = new BufferedImage[2];
		zombieUp[0] = zombieAnim.crop(width * 2, 0, width, height);
		zombieUp[1] = zombieAnim.crop(width * 3, 0, width, height);
		
		zombieRight = new BufferedImage[2];
		zombieRight[0] = zombieAnim.crop(0, height, width, height);
		zombieRight[1] = zombieAnim.crop(width, height, width, height);
		
		zombieLeft = new BufferedImage[2];
		zombieLeft[0] = zombieAnim.crop(width * 2, height, width, height);
		zombieLeft[1] = zombieAnim.crop(width * 3, height, width, height);
	}
	
}
