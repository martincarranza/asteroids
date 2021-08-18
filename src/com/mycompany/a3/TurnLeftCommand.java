package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TurnLeftCommand extends Command{
	
private GameWorld target;
	
	public TurnLeftCommand(GameWorld gw){
		
		super("Turn Ship Left");
		target = gw;	
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		target.turnShipLeft();
		System.out.println("Turn Ship Left Pressed.");
	}
}
