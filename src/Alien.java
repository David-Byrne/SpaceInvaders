package week5;

import java.awt.Image;

public class Alien extends Sprite2D{
	
	

	public Alien(Image i, Image i2, int windowWidth) {
		super(i, i2, windowWidth);
		this.xSpeed = 1;
	}
	
	public boolean move()
	{
		this.x = this.x + (5 * this.xSpeed);
		if( this.isAlive)
		{
			if(this.x > 740 && this.xSpeed > 0) return true;
			if(this.x < 10 && this.xSpeed < 0) return true;
		}
		return false;
	}
	
	public void reverseDirection()
	{
		this.y += 10;
		this.xSpeed = -1 * this.xSpeed;
	}
	private void setIsAlive(boolean alive)
	{
		this.isAlive = alive;
	}

}
