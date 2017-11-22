import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Kirby extends Character{

	static HashMap<String, Boolean> kirbyFlags = new HashMap<String, Boolean>();
	static HashMap<String, BufferedImage[]> kirbyImages = new HashMap<String, BufferedImage[]>();

	boolean isJumping = false;

	public Kirby(int x, int y) {
		super(x, y, kirbyFlags, kirbyImages, 7);
		Thread imageIndexControl = new Thread(new Runnable(){
			public void run(){
				while(this != null){
					String currentAction = "";
					for (String actionKey : actionFlags.keySet()) {
						if(actionFlags.get(actionKey)){
							currentAction = actionKey;
							if(actionImageIndex < actionImages.get(actionKey).length-1){
								actionImageIndex++;
							}else{
								actionImageIndex = 0;
							}
						}
					}
					try{ 
						int sleepTime = 0;
						//control animation speed by string
						//also applying dx and dy movement
						if(currentAction.equals("IdleRight") || currentAction.equals("IdleLeft")  ){
							sleepTime = 200;
							dx = 0;
							dy = 0;
						}
						if(currentAction.equals("WalkRight")){
							sleepTime = 64;
							dx = 4;
							dy = 0;
						}
						if(currentAction.equals("WalkLeft")){
							sleepTime = 64;
							dx = -4;
							dy = 0;
						}
						if(currentAction.equals("JumpRightSide")){
							sleepTime = 64;
							dx = 5;
							jump();
						}
						if(currentAction.equals("JumpLeftSide")){
							sleepTime = 64;
							dx = -5;
							jump();
						}
						if(currentAction.equals("JumpRightVertical") || currentAction.equals("JumpLeftVertical") ){
							sleepTime = 64;
							dx = 0;
							jump();
						}
						Thread.sleep(sleepTime);
					} catch( Exception e){ } 
				}
			}
		});
		imageIndexControl.start();
	}

	void jump(){
		if(isJumping || inAir) { return; }
		Thread jump = new Thread(new Runnable(){
			public void run(){
				isJumping = true;
				for(int i = 0; i < 50; i++){
					dy-=3;
					try{
						Thread.sleep(2);
					}catch(Exception e) { }
				}
				isJumping = false;
			}
		});
		jump.start();
	}


	static void loadKirbyConstants(){
		kirbyFlags.put("IdleRight", true); //Kirby starts as idle right 
		kirbyFlags.put("WalkRight", false);
		kirbyFlags.put("JumpRightSide", false);
		kirbyFlags.put("JumpRightVertical", false);

		
		kirbyImages.put("IdleRight", Texture.kirbyIdleRight);
		kirbyImages.put("WalkRight", Texture.kirbyWalkRight);
		kirbyImages.put("JumpRightSide", Texture.kirbyJumpRight);
		kirbyImages.put("JumpRightVertical", Texture.kirbyJumpRight);

		kirbyFlags.put("IdleLeft", false); 
		kirbyFlags.put("WalkLeft", false);
		kirbyFlags.put("JumpLeftSide", false);
		kirbyFlags.put("JumpLeftVertical", false);
		
		kirbyImages.put("IdleLeft", Texture.kirbyIdleLeft);
		kirbyImages.put("WalkLeft", Texture.kirbyWalkLeft);
		kirbyImages.put("JumpLeftSide", Texture.kirbyJumpLeft);
		kirbyImages.put("JumpLeftVertical", Texture.kirbyJumpLeft);
	}



}
