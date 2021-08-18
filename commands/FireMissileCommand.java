package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

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
