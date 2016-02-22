package week4;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import javax.swing.*;


public class InvadersApplication extends JFrame implements Runnable, KeyListener{
	
	private boolean isGraphicsInitialised =false;
	private static final Dimension WindowSize = new Dimension(800,600);
	private BufferStrategy strategy;
	private static final int NUMALIENS = 30;
	private Alien[] AliensArray = new Alien[NUMALIENS];
	private Spaceship PlayerShip;

	public static void main(String[] args) 
	{
		new InvadersApplication();
	}
	
	public InvadersApplication()
	{
		ImageIcon Icon = new ImageIcon("C:\\Users\\David\\OneDrive\\Documents\\Second Year\\CT255 Next Generation Technology\\Game dev\\space_invader.png");
		Image AlienImg = Icon.getImage();
		AlienImg = AlienImg.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		for (int i = 0; i < NUMALIENS; i++)
		{
			AliensArray[i] = new Alien(AlienImg, (int)WindowSize.getWidth());
			AliensArray[i].setPosition(Math.floor(i/5)*60  , 100+(i%5)*50);
			//sets them in rows with 5 colomns by diving by 5 and flooring to get X offset
		}
		
		ImageIcon PlayerIcon = new ImageIcon("C:\\Users\\David\\OneDrive\\Documents\\Second Year\\CT255 Next Generation Technology\\Game dev\\player_ship.png");
		Image PlayerImage = PlayerIcon.getImage();
		PlayerImage = PlayerImage.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		PlayerShip = new Spaceship(PlayerImage, (int)WindowSize.getWidth());
		
		addKeyListener(this);
		
		this.setTitle("Space Invaders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screensize.width/2 - WindowSize.width/2;
		int y = screensize.height/2 - WindowSize.height/2;
		setBounds(x, y, WindowSize.width, WindowSize.height);
		this.getContentPane().setBackground(Color.BLACK);
		setVisible(true);
		
		isGraphicsInitialised = true;
		
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
		Thread t = new Thread(this);
		t.run();
	}

	public void run()
	{
		while(true)
		{
			boolean hitEdge = false;
			for (int i = 0; i < NUMALIENS; i++)
			{
				hitEdge = AliensArray[i].move() || hitEdge;
				/*Order important, either use this order or use a single pipe OR.
					If not: after the first alien hit the edge in a turn statement would evaluate to true
					on the first part so it would never call the move method for the remaining aliens.*/
			}
			if (hitEdge)
			{
				for (int j = 0; j < NUMALIENS; j++)
				{
					AliensArray[j].reverseDirection();
				}
			}
			this.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			PlayerShip.setXSpeed(-1);
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			PlayerShip.setXSpeed(1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		PlayerShip.setXSpeed(0);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			PlayerShip.setXSpeed(-1);
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			PlayerShip.setXSpeed(1);
		}
	}
	
	public void paint(Graphics g)
	{
		if(isGraphicsInitialised)
		{
			g = strategy.getDrawGraphics();
			super.paintComponents(g);
			for (int i = 0; i < NUMALIENS; i++)
			{
				AliensArray[i].paint(g);
			}
			PlayerShip.move();
			PlayerShip.paint(g);
			g.dispose();
			strategy.show();
		}
	}

}
