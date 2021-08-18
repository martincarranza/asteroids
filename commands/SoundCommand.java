package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class SoundCommand extends Command{
	private GameWorld target;
	
	public SoundCommand(GameWorld gw){
		
		super("Sound");
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		//Get sound flag from GameWorld, then invert when sound command pressed
		boolean sound = target.getSound();		
		sound = !sound;
		target.setSound(sound);			
		
		//Sound True:On, False:Off
		if(sound)	
			System.out.println("Sound Option Pressed! Sound: ON");
		else
			System.out.println("Sound Option Pressed! Sound: OFF");
	}
	
}
