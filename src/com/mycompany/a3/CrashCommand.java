package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CrashCommand extends Command{
	
	private GameWorld target;
	
	public CrashCommand(GameWorld gw){
		
		super("Crash Ship");
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		target.shipCrash();
		System.out.println("Crash Ship Pressed.");
	}
}
