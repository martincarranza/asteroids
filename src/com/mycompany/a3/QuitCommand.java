package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class QuitCommand extends Command{
	
	private GameWorld target;
	
	public QuitCommand(GameWorld gw){
		
		super("Quit");
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		System.out.println("Quit Pressed!");
		target.quit();
	}
}

