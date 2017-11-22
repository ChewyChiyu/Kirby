import java.awt.Graphics;

public abstract  class GameObject {
	
	int x, y;
	int dy = 0, dx = 0;
	
	public GameObject(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	abstract void draw(Graphics g);
}
