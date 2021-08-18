package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

import commands.SoundCommand;

public class PointsView extends Container implements Observer{
	
	private Label pts;
	private Label missiles;
	private Label lives;
	private Label time;
	private CheckBox soundCheck;
	
	public PointsView(GameWorld gw){
		
		//added to Observer list in Game
		
		this.setLayout(new FlowLayout(Component.CENTER));
		
		//create labels and CheckBox with initial settings of Game as text
		pts = new Label("Points: 0");
		missiles = new Label("Missiles: 10");
		lives = new Label("Lives: 3");
		time = new Label("Time: 0");
		soundCheck = new CheckBox("Sound: ");
		SoundCommand sCmd = new SoundCommand(gw);
		
		
		//set paddings
		pts.getAllStyles().setPaddingLeft(4);
		pts.getAllStyles().setPaddingRight(4);
		missiles.getAllStyles().setPaddingLeft(4);
		missiles.getAllStyles().setPaddingRight(4);
		lives.getAllStyles().setPaddingLeft(4);
		lives.getAllStyles().setPaddingRight(4);
		time.getAllStyles().setPaddingLeft(4);
		time.getAllStyles().setPaddingRight(4);
		soundCheck.getAllStyles().setPaddingLeft(4);
		soundCheck.getAllStyles().setPaddingRight(4);
		
		//Add SoundCommand to check box, initial to sound ON
		soundCheck.setCommand(sCmd);
		sCmd.putClientProperty("SideComponent", soundCheck);
		soundCheck.setSelected(true); 	
		
		//Add check and labels to container
		this.add(soundCheck);
		this.add(pts);
		this.add(missiles);
		this.add(lives);
		this.add(time);
		
		
	}
	
	public void update(Observable o, Object arg){
	
		//code here to update labels from data in the GameWorldProxy observable
		
		//Get ship object for missileCount, instantiate variables for labels
		
		
		int playerScore;
		int missileCount; 
		int playerLives;
		int	gameTime;
		boolean sound;
		
		GameWorldProxy gwProxy = (GameWorldProxy) o;
		Ship myShip = Ship.getShip(gwProxy);
		playerLives = gwProxy.getLives();
		missileCount = myShip.getMissileCount();
		playerScore = gwProxy.getScore();
		gameTime = gwProxy.getTime();
		sound = gwProxy.getSound();
		
		pts.setText("Points: " + playerScore);
		missiles.setText("Missiles: " + missileCount);
		lives.setText("Lives: " + playerLives);
		time.setText("Time: " + gameTime);
		
		if (sound)
			soundCheck.setSelected(true);
		else
			soundCheck.setSelected(false);
	}
	
	
	
}//end PointsView
