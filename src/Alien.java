package week6;

import java.awt.Image;

public class Alien extends Sprite2D{
	

	public Alien(Image i, Image i2, int windowWidth, int speed) {
		super(i, i2, windowWidth);
		this.xSpeed = speed;
	}
	
	public boolean move()
	{
		//System.out.println(winWidth);
		this.x = this.x + this.xSpeed;
		if( this.isAlive)
		{
			if(this.x > winWidth-60 && this.xSpeed > 0) return true;
			if(this.x < 10 && this.xSpeed < 0) return true;
		}
		return false;
	}
	
	public void reverseDirection()
	{
		this.y += 20;
		this.xSpeed = -1 * this.xSpeed;
	}
	public double getXSpeed()
	{
		return xSpeed;
	}

}
