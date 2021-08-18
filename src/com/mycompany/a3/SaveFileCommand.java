package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SaveFileCommand extends Command{
	
	public SaveFileCommand(){
		
		super("Save");
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		System.out.println("Save File Pressed!");
	}
	
}
