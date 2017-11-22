import java.awt.Dimension;
import java.awt.Toolkit;

public class KirbyLauncher {
	public static void main(String[] args){
		new KirbyLauncher();
	}
	
	KirbyLauncher(){
		//load textures
		new Texture();
		//load characters
		Kirby.loadKirbyConstants();
		//loading in world
		new KirbyWorld(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
	}
	
}
