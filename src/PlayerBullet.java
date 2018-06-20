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
	
	public boolean collisionDetection(Alien[] aliens)
	{
		if (this.isAlive)
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
		}
		return false;
	}
}
