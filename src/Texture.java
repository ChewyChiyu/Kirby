import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Texture {
	public Texture(){
		load();
	}
	
	void load(){
		loadKirbyTextures();
	}
	
	// THIS IS THE START OF KRIBY TEXTURES
	
	
	//THIS IS THE END OF KIRBY TEXTURES
	
	public static BufferedImage[] kirbyIdleRight = new BufferedImage[2];
	public static BufferedImage[] kirbyWalkRight = new BufferedImage[10];
	public static BufferedImage[] kirbyJumpRight = new BufferedImage[10];
	
	public static BufferedImage[] kirbyIdleLeft = new BufferedImage[2];
	public static BufferedImage[] kirbyWalkLeft = new BufferedImage[10];
	public static BufferedImage[] kirbyJumpLeft = new BufferedImage[10];
	
	void loadKirbyTextures(){
		try{
			BufferedImage kirbySpriteSheetRight = ImageIO.read(getClass().getResource("KirbySpriteSheetRight.png"));
			BufferedImage kirbySpriteSheetLeft = ImageIO.read(getClass().getResource("KirbySpriteSheetLeft.png"));

			kirbyIdleRight[0] = kirbySpriteSheetRight.getSubimage(8,5,21,19);
			kirbyIdleRight[1] = kirbySpriteSheetRight.getSubimage(32,5,21,19);
			
			kirbyWalkRight[0] = kirbySpriteSheetRight.getSubimage(8,54,22,20);
			kirbyWalkRight[1] = kirbySpriteSheetRight.getSubimage(33,55,21,19);
			kirbyWalkRight[2] = kirbySpriteSheetRight.getSubimage(57,57,20,17);
			kirbyWalkRight[3] = kirbySpriteSheetRight.getSubimage(80,56,18,18);
			kirbyWalkRight[4] = kirbySpriteSheetRight.getSubimage(101,55,18,19);
			kirbyWalkRight[5] = kirbySpriteSheetRight.getSubimage(122,54,22,20);
			kirbyWalkRight[6] = kirbySpriteSheetRight.getSubimage(147,55,21,19);
			kirbyWalkRight[7] = kirbySpriteSheetRight.getSubimage(171,57,20,17);
			kirbyWalkRight[8] = kirbySpriteSheetRight.getSubimage(194,56,20,18);
			kirbyWalkRight[9] = kirbySpriteSheetRight.getSubimage(217,55,21,19);
			
			kirbyJumpRight[0] = kirbySpriteSheetRight.getSubimage(9,102,21,21);
			kirbyJumpRight[1] = kirbySpriteSheetRight.getSubimage(34,101,21,21);
			kirbyJumpRight[2] = kirbySpriteSheetRight.getSubimage(54,103,21,19);
			kirbyJumpRight[3] = kirbySpriteSheetRight.getSubimage(78,102,22,20);
			kirbyJumpRight[4] = kirbySpriteSheetRight.getSubimage(103,101,21,21);
			kirbyJumpRight[5] = kirbySpriteSheetRight.getSubimage(127,104,22,18);
			kirbyJumpRight[6] = kirbySpriteSheetRight.getSubimage(152,103,23,19);
			kirbyJumpRight[7] = kirbySpriteSheetRight.getSubimage(178,102,22,20);
			kirbyJumpRight[8] = kirbySpriteSheetRight.getSubimage(203,102,21,20);
			kirbyJumpRight[9] = kirbySpriteSheetRight.getSubimage(230,100,21,22);

			kirbyIdleLeft[0] = kirbySpriteSheetLeft.getSubimage(kirbySpriteSheetRight.getWidth()-8-21,5,21,19);
			kirbyIdleLeft[1] = kirbySpriteSheetLeft.getSubimage(kirbySpriteSheetRight.getWidth()-32-21,5,21,19);
			
			kirbyWalkLeft[0] = kirbySpriteSheetLeft.getSubimage(kirbySpriteSheetRight.getWidth()-8-22,54,22,20);
			kirbyWalkLeft[1] = kirbySpriteSheetLeft.getSubimage(kirbySpriteSheetRight.getWidth()-33-21,55,21,19);
			kirbyWalkLeft[2] = kirbySpriteSheetLeft.getSubimage(kirbySpriteSheetRight.getWidth()-57-20,57,20,17);
			kirbyWalkLeft[3] = kirbySpriteSheetLeft.getSubimage(kirbySpriteSheetRight.getWidth()-80-18,56,18,18);
			kirbyWalkLeft[4] = kirbySpriteSheetLeft.getSubimage(kirbySpriteSheetRight.getWidth()-101-18,55,18,19);
			kirbyWalkLeft[5] = kirbySpriteSheetLeft.getSubimage(kirbySpriteSheetRight.getWidth()-122-22,54,22,20);
			kirbyWalkLeft[6] = kirbySpriteSheetLeft.getSubimage(kirbySpriteSheetRight.getWidth()-147-21,55,21,19);
			kirbyWalkLeft[7] = kirbySpriteSheetLeft.getSubimage(kirbySpriteSheetRight.getWidth()-171-20,57,20,17);
			kirbyWalkLeft[8] = kirbySpriteSheetLeft.getSubimage(kirbySpriteSheetRight.getWidth()-194-20,56,20,18);
			kirbyWalkLeft[9] = kirbySpriteSheetLeft.getSubimage(kirbySpriteSheetRight.getWidth()-217-21,55,21,19);
	
			kirbyJumpLeft[0] = kirbySpriteSheetLeft.getSubimage(kirbySpriteSheetRight.getWidth()-9-21,102,21,21);
			kirbyJumpLeft[1] = kirbySpriteSheetLeft.getSubimage(kirbySpriteSheetRight.getWidth()-34-21,101,21,21);
			kirbyJumpLeft[2] = kirbySpriteSheetLeft.getSubimage(kirbySpriteSheetRight.getWidth()-54-21,103,21,19);
			kirbyJumpLeft[3] = kirbySpriteSheetLeft.getSubimage(kirbySpriteSheetRight.getWidth()-78-21,102,22,20);
			kirbyJumpLeft[4] = kirbySpriteSheetLeft.getSubimage(kirbySpriteSheetRight.getWidth()-103-21,101,21,21);
			kirbyJumpLeft[5] = kirbySpriteSheetLeft.getSubimage(kirbySpriteSheetRight.getWidth()-127-22,104,22,18);
			kirbyJumpLeft[6] = kirbySpriteSheetLeft.getSubimage(kirbySpriteSheetRight.getWidth()-152-23,103,23,19);
			kirbyJumpLeft[7] = kirbySpriteSheetLeft.getSubimage(kirbySpriteSheetRight.getWidth()-178-22,102,22,20);
			kirbyJumpLeft[8] = kirbySpriteSheetLeft.getSubimage(kirbySpriteSheetRight.getWidth()-203-21,102,21,20);
			kirbyJumpLeft[9] = kirbySpriteSheetLeft.getSubimage(kirbySpriteSheetRight.getWidth()-230-21,100,21,22);
		
		}catch(Exception e) {  e.printStackTrace(); }
	}
	
	
	
}
