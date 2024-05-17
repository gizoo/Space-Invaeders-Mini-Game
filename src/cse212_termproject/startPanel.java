package cse212_termproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class startPanel extends JPanel  {
	
	public ImageIcon space = new ImageIcon("spaceinvaders.png");
	public Image spaceImage = space.getImage();
	public ImageIcon back = new ImageIcon("startBackground.png");
	public Image backImage = back.getImage();
	
	private Clip clip;
	
	
	public startPanel() {
		setLayout(null); 
        setOpaque(false);
        
        try {
			
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("menuTheme.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			
		} catch (Exception e) {
			System.out.println("Error loading music: " + e.getMessage());
		}
     
	}
	
	public void paint(Graphics g) {
		g.drawImage(backImage, 0, 0, 800, 600 , null);
		g.drawImage(spaceImage, 200, 100, 400, 200 , null);
		Font font = g.getFont().deriveFont(Font.BOLD, 20); 
        g.setFont(font);
		g.setColor(Color.YELLOW);
		g.drawString("Register to start game", 280, 390);
	
	}
	public void removeNotify() {
		super.removeNotify();
		if (clip != null) {
			clip.stop();
			clip.close();
		}
	}
}
