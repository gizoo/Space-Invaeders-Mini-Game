package cse212_termproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class gamePanel extends JPanel implements KeyListener , ActionListener{

	Timer timer = new Timer(15, this);
	
	public int Score = 0;
	public int LP = 20;
	
	public static boolean gameover = false;
	
	public int level = 1;
	
	public ImageIcon ship = new ImageIcon("SpaceShip.png");
	public Image shipImage = ship.getImage();
	public ImageIcon background = new ImageIcon("background.png");
	public Image backImage = background.getImage();
	public ImageIcon Enemy1 = new ImageIcon("enemy1.png");
	public Image enemy1Image = Enemy1.getImage();
	public ImageIcon bullet = new ImageIcon("bullet.png");
	public Image bulletImage = bullet.getImage();
	public ImageIcon Enemy2 = new ImageIcon("enemy2.png");
	public Image enemy2Image = Enemy2.getImage();
	public ImageIcon Enemy3 = new ImageIcon("enemy3.png");
	public Image enemy3Image = Enemy3.getImage();
	public ImageIcon over1 = new ImageIcon("over1.png");
	public Image over1Image = over1.getImage();
	
	public ArrayList<enemybullet> ebullets = new ArrayList<enemybullet>();
	public ArrayList<bullet> bullets = new ArrayList<bullet>();
	public ArrayList<enemy> enemies = new ArrayList<enemy>();
	
	int timepassed = 0;

	public int spaceShipX = 350;
	public int spaceShipY = 450;
	
	public int bulletmove = 4;
	public int enemybulletmove = 4;
	public int shipmove = 6;
	

	public gamePanel() {
		setBackground(Color.black);	
		
		addKeyListener(this);
		setFocusable(true);
		timer.start();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(backImage, 0, 0 , 800, 600, null);
		g.setColor(Color.red);
		g.drawImage(shipImage, spaceShipX, spaceShipY, 50, 50,  null );
		
		for(enemy enemy: enemies) {
			if (enemy.enemyY >=550) {
				enemies.remove(enemy);
			}
		}
		
		for(bullet bullet : bullets) {
			if(bullet.bulletY <= 0) {
				bullets.remove(bullet);
			}
		}
		
		
		for(bullet bullet: bullets) {
			for (enemy enemy: enemies) {
				if(new Rectangle(bullet.bulletX, bullet.bulletY, 15, 25 ).intersects(new Rectangle(enemy.enemyX, enemy.enemyY, 50, 50) )) {
					bullets.remove(bullet);
					enemies.remove(enemy);
					Score += 10;
				}
			}
		}
		
		for (enemy enemy : enemies) {
			if (enemy instanceof enemy1) {
				g.drawImage(enemy1Image, enemy.enemyX, enemy.enemyY, 50, 50, null);
			}
			if (enemy instanceof enemy2) {
				g.drawImage(enemy2Image, enemy.enemyX, enemy.enemyY, 50, 50, null);
			}
			if (enemy instanceof enemy3) {
				g.drawImage(enemy3Image, enemy.enemyX, enemy.enemyY, 40, 40, null);
			}
		}
		
		for(bullet bullet: bullets) {
			g.drawImage(bulletImage,bullet.bulletX, bullet.bulletY, 15, 25 , null);
		}
		
		for (enemybullet ebullet : ebullets) {
			g.setColor(Color.RED);
			g.fillOval(ebullet.ebulletX, ebullet.ebulletY , 8, 8);
		}
		

	    Iterator<enemy> enemyIterator = enemies.iterator();
	    while (enemyIterator.hasNext()) {
	        enemy enemy = enemyIterator.next();
	        Rectangle enemyBounds;
	        if (enemy instanceof enemy3) {
	            enemyBounds = new Rectangle(enemy.enemyX, enemy.enemyY, 40, 40);
	        } else {
	            enemyBounds = new Rectangle(enemy.enemyX, enemy.enemyY, 50, 50);
	        }
	        if (enemyBounds.intersects(new Rectangle(spaceShipX, spaceShipY, 50, 50))) {
	            enemyIterator.remove();
	            LP--;
	        }
	    }
		
		for (enemybullet ebullet : ebullets) {
			if(new Rectangle(spaceShipX, spaceShipX, 50, 50).intersects(new Rectangle(ebullet.ebulletX, ebullet.ebulletY, 5, 5) )) {
				LP--;
			}
		}
		
		g.setColor(Color.yellow);
	    g.drawString("Score: " + Score, 10, 20);
	    g.drawString("LP: " + LP, 10, 40);
	    g.drawString("Level: " + level, 10, 60);
	    
	    if (LP == 0) {
//			JOptionPane.showMessageDialog(this, " Game Over ",null, JOptionPane.PLAIN_MESSAGE);		
			if (gamePanel.gameover) {
				try (BufferedWriter writer = new BufferedWriter(new FileWriter("scoreboard.txt", true))) {
		            writer.write("\t" + Score);
		            writer.newLine();
		            System.out.println("Successfully wrote to the file.");
				}catch (IOException e1) {
	            System.out.println("An error occurred while writing to the file.");
	            e1.printStackTrace();
				}
			}
			g.drawImage(over1Image, 0, 0, 800, 600, null);
	    }
	    
		if (LP <= 0) {
			gameover = true;
			timer.stop();
		}
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT) {
			if (spaceShipX <= 0) {
				spaceShipX = 0;
			}
			else {
				spaceShipX -= shipmove;
			}
		}
		else if (key == KeyEvent.VK_RIGHT) {
			if (spaceShipX >= 750) {
				spaceShipX = 750;
			}
			else {
				spaceShipX += shipmove;
			}
		}
		else if (key == KeyEvent.VK_UP) {
			if (spaceShipY <= 0) {
				spaceShipY = 0;
			}
			else {
				spaceShipY -= shipmove;
			}
		}
		
		else if (key == KeyEvent.VK_DOWN) {
			if (spaceShipY >= 550) {
				spaceShipY = 550;
			}
			else {
				spaceShipY += shipmove;
			}
		}
		else if (key == KeyEvent.VK_SPACE) {
			bullets.add(new bullet(spaceShipX + 18, spaceShipY-10));
		}
		
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		timepassed ++;
		
		if (timepassed % 35 == 0 && timepassed <= 500) {
            enemies.add(new enemy1());
        }
		
		if (timepassed > 500 && timepassed <= 700 && timepassed % 77 == 0) {
			for (int i = 0 ; i<= 750 ; i+=60) {
				enemies.add(new enemy2(i));
			}
		}
		 
		if (timepassed > 500 && timepassed % 63 == 0) {
			for (int i = 50 ; i<= 700 ; i+=250) {
				enemies.add(new enemy3(i));
			}
		}
		
		for(enemy enemy : enemies) {
			if (enemy instanceof enemy1) {
				enemy.enemyX += enemy.enemymoveX;
				enemy.enemyY += enemy.enemymoveY;
				
				if (enemy.enemyX >= 750) {
					enemy.enemymoveX = - enemy.enemymoveX;
				}
				if (enemy.enemyX<=0) {
					enemy.enemymoveX = + enemy.enemymoveX;
				}
			}
			else if (enemy instanceof enemy2) {
				enemy.enemyY += enemy.enemymoveY;
			}
			else if (enemy instanceof enemy3) {
				enemy.enemyY += enemy.enemymoveY;
				enemy.enemyX += 1;
			}
		}
		
		for (enemy enemy : enemies) {
			if(enemy instanceof enemy3) {
				if (timepassed % 30 == 0) {
					ebullets.add(new enemybullet(enemy.enemyX + 18, enemy.enemyY+10));
				}
			}
		}
		
		for (enemybullet ebullet: ebullets) {
			ebullet.ebulletY += enemybulletmove;
		}
			
		for(bullet bullet: bullets) {
			bullet.bulletY -= bulletmove;
		}
		
		if (timepassed >= 600) {
			timepassed = 0;
			level++;
		}
		
		repaint();
	}
	
}
