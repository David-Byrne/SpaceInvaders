//David Byrne, 14344646
package week3;

import java.awt.*;

public class Sprite2D {
	
	private double x, y;
	private double xSpeed = 0;
	private Image myImage;
	
	public Sprite2D(Image i){
		myImage = i;
		x = 600 * Math.random();
		y = 600 * Math.random();
	}
	
	public void moveEnemy(){
		this.x = this.x + 5*(Math.random()-0.5);
		this.y = this.y + 5*(Math.random()-0.5);
		
		if (this.x < 0) x = 0;
		else if (this.x > 570) x = 570;
		
		if (this.y < 30) y = 30;
		else if (this.y > 540) y = 540;//600 - height of enemy - height of player
		
	}
	
	public void setPosition(double xx, double yy){
		this.x = xx;
		this.y = yy;
		
	}
	
	public void movePlayer(){
		if (xSpeed == 1 && x < 560) x+= 10;
		else if (xSpeed == -1 && x > 10) x-= 10;
		
	}
	
	public void setXSpeed(double dx){
		this.xSpeed = dx;
	}
	
	public void paint(Graphics g){
		g.drawImage(myImage, (int)x , (int)y , null);

	}
}
