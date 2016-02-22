package week4;

import java.awt.Image;

public class Alien extends Sprite2D{

	public Alien(Image i, int windowWidth) {
		super(i, windowWidth);
		this.xSpeed = 1;
	}
	
	public boolean move()
	{
		this.x = this.x + (2 * this.xSpeed);
		if(this.x > 740 && this.xSpeed > 0) return true;
		if(this.x < 10 && this.xSpeed < 0) return true;
		return false;
	}
	
	public void reverseDirection()
	{
		this.y += 10;
		this.xSpeed = -1 * this.xSpeed;
	}

}
