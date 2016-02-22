package week4;

import java.awt.Graphics;
import java.awt.Image;

public abstract class Sprite2D 
{
	protected double x, y;
	protected double xSpeed = 0;
	protected Image image;
	int winWidth;
	
	public Sprite2D(Image i, int windowWidth)
	{
		image = i;
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
		g.drawImage(image, (int)x , (int)y , null);
	}
}
