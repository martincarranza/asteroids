package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class JumpCommand extends Command{

	private GameWorld target;
	
	public JumpCommand(GameWorld gw){
		
		super("Hyperspace Jump");
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		target.hyperSpaceJump();
		System.out.println("Jumped Through Hyperspace!");
	}
}
