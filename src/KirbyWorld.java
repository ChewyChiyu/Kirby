import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
@SuppressWarnings("serial")
public class KirbyWorld extends JPanel implements Runnable{

	Dimension worldDim;

	Thread worldThread;
	boolean worldIsRunning;

	int currentFPS;
	int MAX_FPS = 60;

	ArrayList<GameObject> sprites = new ArrayList<GameObject>();
	Kirby kirby;


	boolean A_PRESS = false, S_PRESS = false, D_PRESS = false, W_PRESS = false, isLeft = false, isRight = true;

	public KirbyWorld(Dimension worldDim){
		this.worldDim = worldDim;
		applyKeyBinds();
		createPanel();
		addKirby();
		createWorldThread();
		startWorld();

	}

	void applyKeyBinds(){
		getInputMap().put(KeyStroke.getKeyStroke("A"), "A");
		getInputMap().put(KeyStroke.getKeyStroke("S"), "S");
		getInputMap().put(KeyStroke.getKeyStroke("D"), "D");
		getInputMap().put(KeyStroke.getKeyStroke("W"), "W");

		getInputMap().put(KeyStroke.getKeyStroke("released A"), "released A");
		getInputMap().put(KeyStroke.getKeyStroke("released S"), "released S");
		getInputMap().put(KeyStroke.getKeyStroke("released D"), "released D");
		getInputMap().put(KeyStroke.getKeyStroke("released W"), "released W");

		getActionMap().put("A", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				A_PRESS = true;
				isLeft = true;
				isRight = false;
			}

		});
		getActionMap().put("S", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				S_PRESS = true;
			}

		});
		getActionMap().put("D", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				D_PRESS = true;
				isLeft = false;
				isRight = true;
			}

		});
		getActionMap().put("W", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				W_PRESS = true;
			}

		});


		getActionMap().put("released A", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				A_PRESS = false;
			}

		});
		getActionMap().put("released S", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				S_PRESS = false;
			}

		});
		getActionMap().put("released D", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				D_PRESS = false;
			}

		});
		getActionMap().put("released W", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				W_PRESS = false;
			}

		});

	}

	void addKirby(){
		if(kirby == null){
			kirby = new Kirby(300,300);
			sprites.add(kirby);
		}
	}

	void createWorldThread(){
		worldThread = new Thread(this);
		worldIsRunning = true;
	}

	synchronized void startWorld(){
		worldIsRunning = true;
		worldThread.start();
		backEndThreads();
	}

	void backEndThreads(){
		Thread manageActionFlags = new Thread(new Runnable(){

			@Override
			public void run() {
				while(worldIsRunning){
					if(W_PRESS && A_PRESS){
						if(!kirby.actionFlags.get("JumpLeftSide")){
							kirby.changeAction("JumpLeftSide");
						}
					} 
					else if(W_PRESS && D_PRESS){
						if(!kirby.actionFlags.get("JumpRightSide")){
							kirby.changeAction("JumpRightSide");
						}
					}
					else if(W_PRESS){
						if(isRight){
							if(!kirby.actionFlags.get("JumpRightVertical")){
								kirby.changeAction("JumpRightVertical");
							}
						}
						if(isLeft){
							if(!kirby.actionFlags.get("JumpLeftVertical")){
								kirby.changeAction("JumpLeftVertical");
							}
						}
					}
					else if(A_PRESS){
						if(!kirby.inAir){
							if(!kirby.actionFlags.get("WalkLeft")){
								kirby.changeAction("WalkLeft");
							}
						}
					} 
					else if(D_PRESS){
						if(!kirby.inAir){
							if(!kirby.actionFlags.get("WalkRight")){
								kirby.changeAction("WalkRight");
							}
						}
					} 
					else if(S_PRESS){

					}
					//after this means no key is being pressed
					else if(isRight){
						if(!kirby.isJumping && !kirby.inAir){
							if(!kirby.actionFlags.get("IdleRight")){
								kirby.changeAction("IdleRight");
							}
						}
					} 
					else if(isLeft){
						if(!kirby.isJumping && !kirby.inAir){
							if(!kirby.actionFlags.get("IdleLeft")){
								kirby.changeAction("IdleLeft");
							}
						}
					}

					try{
						Thread.sleep(1);
					}catch(Exception e)  { }
				}
			}

		});

		manageActionFlags.start();
	}




	synchronized void pauseWorld(){
		worldIsRunning = false;
	}

	synchronized void resumeWorld(){
		worldIsRunning = true;
	}

	void createPanel(){
		JFrame frame = new JFrame("KirbyWorld");
		frame.add(this);
		frame.setPreferredSize(worldDim.getSize());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public void run(){
		long previousTime = System.currentTimeMillis();
		long currentTime = previousTime;
		long elapsedTime;
		long totalElapsedTime = 0;
		int frameCount = 0;

		while(worldIsRunning)
		{
			currentTime = System.currentTimeMillis();
			elapsedTime = (currentTime - previousTime); 
			totalElapsedTime += elapsedTime;

			if (totalElapsedTime > 1000)
			{
				currentFPS = frameCount;
				frameCount = 0;
				totalElapsedTime = 0;
			}

			update();

			try
			{
				Thread.sleep(getFpsDelay(MAX_FPS));
			} catch (Exception e) {
				e.printStackTrace();
			}

			previousTime = currentTime;
			frameCount++;

		}

	}
	int getFpsDelay(int desiredFps)
	{
		return 1000 / desiredFps;
	}

	void update(){
		repaint();
		moveSprites();
		physics();
	}

	void physics(){
		synchronized(sprites){
			for(int i = 0; i < sprites.size(); i++){
				if(sprites.get(i) instanceof Character){

					Character c = (Character) sprites.get(i);

					//checking if inAir to apply gravity
					if(c.y < worldDim.getHeight()*0.7){
						c.inAir = true;
						c.dy = 10;
					}else{
						c.inAir = false;
						c.dy = 0;
					}

				}
			}
		}
	}

	void moveSprites(){
		synchronized(sprites){
			for(int i = 0; i < sprites.size(); i++){
				sprites.get(i).x += sprites.get(i).dx;
				sprites.get(i).y += sprites.get(i).dy;
			}
		}
	}

	public void paintComponent(Graphics g){
		drawStats(g);
		drawSprites(g);
	}

	void drawSprites(Graphics g){
		synchronized(sprites){
			for(int i = 0; i < sprites.size(); i++){
				sprites.get(i).draw(g);
			}
		}
	}

	void drawStats(Graphics g){
		g.setFont(new Font("Aerial",Font.BOLD,20));
		g.drawString("FPS: " + currentFPS, (int)(worldDim.width*0.05), (int)(worldDim.height*0.05));
	}
}
