package cse212_termproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class scorePanel extends JPanel {
	
	public ImageIcon back = new ImageIcon("startBackground.png");
	public Image backImage = back.getImage();
	
	int y = 230;
	
	public scorePanel() {
		setVisible(true);
		setLayout(null); 
        setOpaque(false);
		
	}
	
	public void paint(Graphics g) {
		g.drawImage(backImage, 0, 0, 800, 600 , null);
		
		Font font = g.getFont().deriveFont(Font.ITALIC, 30); 
        g.setFont(font);
		g.setColor(Color.YELLOW);
		g.drawString("SCORE BOARD", 280, 130);
		
		Font font2 = g.getFont().deriveFont(Font.ITALIC, 24); 
        g.setFont(font2);
		
		
		try (BufferedReader reader = new BufferedReader(new FileReader("scoreboard.txt"))) {
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append('\n');
                g.drawString(content.toString(), 150, y );
                y += 30; 
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

}
