package week5;

import java.awt.Graphics;
import java.awt.Image;

public abstract class Sprite2D 
{
	protected double x, y;
	protected double xSpeed = 0;
	protected Image image, image2;
	int winWidth;
	protected int framesDrawn = 0;
	protected boolean isAlive = true;
	
	public Sprite2D(Image i, Image i2, int windowWidth)
	{
		image = i;
		image2 = i2;
		winWidth = windowWidth;
	}
	
	public void setPosition(double xx, double yy)
	{
		this.x = xx;
		this.y = yy;
	}
	
	public void setXSpeed(double dx)
	{
		this.xSpeed = dx;
	}
	
	public void paint(Graphics g)
	{
		framesDrawn++;
		
		if (this.isAlive)
		{
			if (framesDrawn % 100 < 50)
			{
				g.drawImage(image, (int)x , (int)y , null);
			}
			else
			{
				g.drawImage(image2, (int)x , (int)y , null);
			}
		}
	}
}
