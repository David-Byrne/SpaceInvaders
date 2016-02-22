//David Byrne, 14344646
package week3;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import week2.GameObject;


public class InvadersApplication extends JFrame implements Runnable, KeyListener{
	
	private static final Dimension WindowSize = new Dimension(600,600);
	private static final int NUMALIENS = 30;
	private Sprite2D[] AliensArray = new Sprite2D[NUMALIENS];
	private Sprite2D PlayerShip;
	private Thread t;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new InvadersApplication();
	}
	
	public InvadersApplication()
	{
		ImageIcon Icon = new ImageIcon("C:\\Users\\David\\OneDrive\\Documents\\Second Year\\CT255 Next Generation Technology\\Game dev\\space_invader.png");
		Image AlienImg = Icon.getImage();
		AlienImg = AlienImg.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		for (int i = 0; i < NUMALIENS; i++)
		{
			AliensArray[i] = new Sprite2D(AlienImg);
		}
		
		ImageIcon PlayerIcon = new ImageIcon("C:\\Users\\David\\OneDrive\\Documents\\Second Year\\CT255 Next Generation Technology\\Game dev\\player_ship.png");
		Image PlayerImage = PlayerIcon.getImage();
		PlayerImage = PlayerImage.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		PlayerShip = new Sprite2D(PlayerImage);
		PlayerShip.setPosition(285, 570);
		
		addKeyListener(this);
		
		this.setTitle("Space Invaders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screensize.width/2 - WindowSize.width/2;
		int y = screensize.height/2 - WindowSize.height/2;
		setBounds(x, y, WindowSize.width, WindowSize.height);
		this.getContentPane().setBackground(Color.BLACK);
		setVisible(true);
		t = new Thread(this);
		t.run();
	}

	public void run()
	{
		while(true)
		{
			for (int i = 0; i < NUMALIENS; i++)
			{
				AliensArray[i].moveEnemy();
			}
			this.repaint();
			try {
				t.sleep(20);
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
		// TODO Auto-generated method stub
		PlayerShip.setXSpeed(0);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void paint(Graphics g)
	{
		super.paintComponents(g);
		for (int i = 0; i < NUMALIENS; i++)
		{
			AliensArray[i].paint(g);
		}
		PlayerShip.movePlayer();
		PlayerShip.paint(g);
	}

}
