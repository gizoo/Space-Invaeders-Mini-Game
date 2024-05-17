package cse212_termproject;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Game {
	
	public static JFrame frame = new JFrame("Space Invaders");
	public static HashMap<String, String> list = new HashMap<String, String>();
	public static boolean isregistered = false;

	public static void main(String[] args) {
		
		frame.setResizable(false);
		frame.setFocusable(false);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JMenuBar menu = new JMenuBar();
		
		JMenu fileMenu = new JMenu ("File");
		JMenuItem regItem = new JMenuItem ("Register");
		JMenuItem playItem = new JMenuItem ("Play Game");
        JMenuItem scoreItem = new JMenuItem ("High Score");
        JMenuItem quitItem = new JMenuItem ("Quit");
        fileMenu.add (regItem);
        fileMenu.add (playItem);
        fileMenu.add (scoreItem);
        fileMenu.add (quitItem);
        
        JMenu helpMenu = new JMenu ("Help");
        JMenuItem aboutItem = new JMenuItem ("About");
        helpMenu.add(aboutItem);
        
        menu.add(fileMenu);
        menu.add(helpMenu);
        
        frame.setJMenuBar(menu);
        
        
	    startPanel startPanel = new startPanel();
	    
	    CardLayout cardLayout = new CardLayout();
        frame.setLayout(cardLayout);
        
        frame.add(startPanel, "start");
        
        cardLayout.show(frame.getContentPane(), "start");
	    
	    regItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String username = JOptionPane.showInputDialog(frame, "Username", "", JOptionPane.PLAIN_MESSAGE);
		        String password = JOptionPane.showInputDialog(frame, "Password", "", JOptionPane.PLAIN_MESSAGE);
				
				list.put(username, password);
				
			}
	    });
	    
	    playItem.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String username = JOptionPane.showInputDialog(frame, "Username", "Login", JOptionPane.PLAIN_MESSAGE);
				String password = JOptionPane.showInputDialog(frame, "Password", "Login", JOptionPane.PLAIN_MESSAGE);
		        
				if(list.containsKey(username) && list.containsValue(password) ) {
		        	isregistered = true;
		        	JOptionPane.showMessageDialog(frame,"Login successful.","Success", JOptionPane.INFORMATION_MESSAGE);
		        }
				
				 try (BufferedWriter writer = new BufferedWriter(new FileWriter("scoreboard.txt"))) {
			            writer.write(username);
			            System.out.println("Successfully wrote to the file.");
			     }catch (IOException e1) {
		            System.out.println("An error occurred while writing to the file.");
		            e1.printStackTrace();
		        }
				
				if (isregistered) {
					gamePanel gamePanel = new gamePanel();
					gamePanel.requestFocus();
				    gamePanel.addKeyListener(gamePanel);
				    gamePanel.setFocusTraversalKeysEnabled(false);
				    gamePanel.setVisible(true);
				    frame.add(gamePanel, "game");
					cardLayout.show(frame.getContentPane(), "game");
					gamePanel.requestFocusInWindow();
				}
				else {
					JOptionPane.showMessageDialog(frame, " Register first ",null, JOptionPane.PLAIN_MESSAGE);			
				}
			}
	    	
	    });
	    
	    scoreItem.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				scorePanel scorePanel = new scorePanel();
				scorePanel.setVisible(true);
				frame.add(scorePanel, "score");
				cardLayout.show(frame.getContentPane(), "score");
				
			}
	    	
	    });
	    
	    quitItem.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
	    	
	    });
	    
	    aboutItem.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, " Gizem Aybar \n 20210702031 \n gizem.aybar@std.yeditepe.edu.tr ",null, JOptionPane.PLAIN_MESSAGE);		
			}
	    	
	    });
	    
	    frame.setVisible(true);
	 
	}
	

}
