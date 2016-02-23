package week5;

import java.awt.Image;

public class Spaceship extends Sprite2D{

	public Spaceship(Image i, int windowWidth) {
		super(i, i, windowWidth);
		this.setPosition(10 + winWidth/2, 570);
	}
	
	public void move()
	{
		if (xSpeed == 1 && x < winWidth-40) x+= 10;
		else if (xSpeed == -1 && x > 10) x-= 10;
	}

}
