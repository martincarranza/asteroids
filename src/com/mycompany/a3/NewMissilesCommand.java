package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class NewMissilesCommand extends Command{
	
	private GameWorld target;
	
	public NewMissilesCommand(GameWorld gw){
		
		super("New Missiles");
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		target.getNewMissiles();
		System.out.println("Reloaded New Missiles Pressed.");
	}
}
