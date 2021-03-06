import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;


public class InvadersApplication extends JFrame implements Runnable, KeyListener{
	
	private boolean isGraphicsInitialised =false;
	private boolean inProgress = false;
	private static final Dimension WindowSize = new Dimension(1000,600);
	private BufferStrategy strategy;
	private static final int NUMALIENS = 30;
	private Alien[] AliensArray = new Alien[NUMALIENS];
	private Spaceship PlayerShip;
	private Image bulletImage;
	private int curScore = 0;
	private int topScore = 0;
	private ArrayList<PlayerBullet> bulletList = new ArrayList<PlayerBullet>();

	public static void main(String[] args) 
	{
		new InvadersApplication();
	}
	
	public InvadersApplication()
	{
		
		addKeyListener(this);
		
		this.setTitle("Space Invaders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screensize.width/2 - WindowSize.width/2;
		int y = screensize.height/2 - WindowSize.height/2;
		setBounds(x, y, WindowSize.width, WindowSize.height);
		this.getContentPane().setBackground(Color.BLACK);
		setVisible(true);
		
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
		isGraphicsInitialised = true;
		
		Thread t = new Thread(this);
		t.run();
	}

	public void run()
	{
		while(true)
		{
			if(inProgress)
			{
				boolean hitEdge = false;
				boolean swarmAlive = false;
				for (int i = 0; i < NUMALIENS; i++)
				{
					hitEdge = AliensArray[i].move() || hitEdge;
					swarmAlive = swarmAlive || AliensArray[i].getIsAlive();
				}
				if (hitEdge)
				{
					for (int j = 0; j < NUMALIENS; j++)
					{
						AliensArray[j].reverseDirection();
					}
				}
				Iterator<PlayerBullet> i = bulletList.iterator();
				while(i.hasNext())
				{
					PlayerBullet b = (PlayerBullet) i.next();
					b.move();
					if (b.collisionDetection(AliensArray))
					{
						curScore += 10;
						i.remove();
					}
				}
				if (!swarmAlive)
				{
					startNewWave((int) (Math.abs(AliensArray[0].getXSpeed())+5));
				}
			}
			
			this.repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void shootBullet(double x, double y)
	{
		//System.out.println("fire");
		PlayerBullet b = new PlayerBullet(bulletImage, WindowSize.width, (int)x, (int)y );
		bulletList.add(b);
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
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			shootBullet(PlayerShip.x, PlayerShip.y);
		}
		else
		{
			PlayerShip.setXSpeed(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (!inProgress)
		{
			startNewGame();
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) 
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
			g.setColor(Color.GREEN);
			//g.drawString("Score: "+curScore+"   Top Score: "+topScore, 250, 50);
			writeString(g, WindowSize.width/2, 50, 20,"Score: "+curScore+"   Top Score: "+topScore );
			
			if(inProgress)
			{
				
				for (int i = 0; i < NUMALIENS; i++)
				{
					AliensArray[i].paint(g);
				}
				for( PlayerBullet bullet : this.bulletList)
				{
					bullet.paint(g);
				}
				if(PlayerShip.collisionDetection(AliensArray))
				{
					gameOver(g);
				}
				PlayerShip.move();
				PlayerShip.paint(g);
				
			}
			else
			{
				ImageIcon Icon = new ImageIcon("assets/alien.png");
				Image AlienImg = Icon.getImage();
				//AlienImg = AlienImg.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
				g.drawImage(AlienImg, (WindowSize.width/2)-120 , 250 , null);
				
				writeString(g, WindowSize.width/2, 200, 70,"Space Invaders");
				writeString(g, WindowSize.width/2, 500, 20,"Press any key to play");
			}
			g.dispose();
			strategy.show();
		}
	}
	private void writeString(Graphics g, int x, int y, int fontSize, String message) {
		Font f = new Font("Consolas", Font.PLAIN, fontSize);
		g.setFont(f);
		FontMetrics fm = getFontMetrics(f);
		int width = fm.stringWidth(message);
		g.drawString(message, x-(width/2), y);
		
	}

	public void startNewWave(int alienSpeed)
	{
		bulletList.clear();
		ImageIcon Icon = new ImageIcon("assets/alien.png");
		Image AlienImg = Icon.getImage();
		AlienImg = AlienImg.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		ImageIcon Icon2 = new ImageIcon("assets/alien2.png");
		Image AlienImg2 = Icon2.getImage();
		AlienImg2 = AlienImg2.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		for (int i = 0; i < NUMALIENS; i++)
		{
			AliensArray[i] = new Alien(AlienImg, AlienImg2,(int)WindowSize.getWidth(), alienSpeed);
			AliensArray[i].setPosition(Math.floor(i/5)*60  , 100+(i%5)*50);
			//sets them in rows with 5 colomns by diving by 5 and flooring to get X offset
		}
	}
	
	public void startNewGame()
	{
		startNewWave(5); 
		
		ImageIcon PlayerIcon = new ImageIcon("assets/player_ship.png");
		Image PlayerImage = PlayerIcon.getImage();
		PlayerImage = PlayerImage.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		PlayerShip = new Spaceship(PlayerImage, (int)WindowSize.getWidth());
		
		ImageIcon BulletIcon = new ImageIcon("assets/bullet.png");
		Image BulletImage = BulletIcon.getImage();
		bulletImage = BulletImage.getScaledInstance(10, 10, Image.SCALE_DEFAULT);
		
		inProgress = true;
	}
	
	public void gameOver(Graphics g)
	{
		super.paintComponents(g);
		
		if (this.topScore < this.curScore)
		{
			this.topScore = this.curScore;
		}
		this.inProgress = false;
		this.curScore = 0;
	}
}
