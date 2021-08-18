package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class FireMissileCommand extends Command{

	private GameWorld target;
	
	public FireMissileCommand(GameWorld gw){
		
		super("Fire Missile");
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		target.fireMissile();
		System.out.println("Missile Fired Button Pressed.");
	}
}
