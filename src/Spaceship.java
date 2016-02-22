package week4;

import java.awt.Image;

public class Spaceship extends Sprite2D{

	public Spaceship(Image i, int windowWidth) {
		super(i, windowWidth);
		this.setPosition(10 + winWidth/2, 570);
		// TODO Auto-generated constructor stub
	}
	
	public void move()
	{
		if (xSpeed == 1 && x < winWidth-40) x+= 10;
		else if (xSpeed == -1 && x > 10) x-= 10;
	}

}
