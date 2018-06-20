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
	public boolean collisionDetection(Alien[] aliens)
	{
		for (int i = 0; i < aliens.length; i++)
		{
			if(aliens[i].isAlive)
			{
			
				if(((this.x > aliens[i].x) && (this.x < aliens[i].x + aliens[i].image.getWidth(null)) ||
						(this.x < aliens[i].x) && (this.x + this.image.getWidth(null) > aliens[i].x))
						&&
						((aliens[i].y < this.y) && (aliens[i].y + aliens[i].image.getHeight(null)> this.y) ||
						(this.y < aliens[i].y) && (this.y + this.image.getHeight(null)> aliens[i].y)))
				{
					//System.out.println("hit");
					this.isAlive = false;
					aliens[i].setIsAlive(false);
					return true;
				}
			}
		}
		return false;
	}

}
