package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class DecShipSpeedCommand extends Command{
	
private GameWorld target;
	
	public DecShipSpeedCommand(GameWorld gw){
		
		super("Decrease Ship Speed");
		target = gw;	
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		target.decShipSpeed();
		System.out.println("Ship Speed Decreased Pressed.");
	}
}