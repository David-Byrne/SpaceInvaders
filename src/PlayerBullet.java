package week5;

import java.awt.Image;

public class PlayerBullet extends Sprite2D{
	
	
	
	public PlayerBullet(Image i, int windowWidth, int x, int y)
	{
		super(i, i, windowWidth);
		this.setPosition(x + 10 , y);
	}
	public void move()
	{
		this.y-=5;
	}
	public void setIsAlive(boolean alive)
	{
		this.isAlive = alive;
	}
}
