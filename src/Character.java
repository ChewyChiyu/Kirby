import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Character extends GameObject{

	HashMap<String, Boolean> actionFlags = new HashMap<String,Boolean>();
	HashMap<String,BufferedImage[]> actionImages = new HashMap<String,BufferedImage[]>();
	
	int actionImageIndex = 0;
	int imageScaleConstant;
	
	boolean inAir = false;
	
	public Character(int x, int y, HashMap<String, Boolean> actionFlags, HashMap<String,BufferedImage[]> actionImages, int imageScaleConstant ) {
		super(x, y);
		this.actionFlags = actionFlags;
		this.actionImages = actionImages;
		this.imageScaleConstant = imageScaleConstant;
	}

	@Override
	void draw(Graphics g) {
		for (String actionKey : actionFlags.keySet()) {
			if(actionFlags.get(actionKey)){
				g.drawImage(actionImages.get(actionKey)[actionImageIndex].getScaledInstance(actionImages.get(actionKey)[actionImageIndex].getWidth()*imageScaleConstant, actionImages.get(actionKey)[actionImageIndex].getHeight()*imageScaleConstant, Image.SCALE_DEFAULT), x, y, null);
			}
		}
	}
	
	void changeAction(String action){
		actionImageIndex = 0; //reseting image index
		for (String actionKey : actionFlags.keySet()) {
			 actionFlags.put(actionKey, false); //reseting action keys to all false
		}
		//applying new action key
		actionFlags.put(action, true);
	}
	
}
