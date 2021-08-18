package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class KillAsteroidCommand extends Command{
	
	private GameWorld target;
	
	public KillAsteroidCommand(GameWorld gw){
		
		super("Kill Asteroid");
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		target.missileKillAsteroid();
		System.out.println("Kill Asteroid Pressed.");
	}
}
