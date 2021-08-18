package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class IncShipSpeedCommand extends Command{
	
private GameWorld target;
	
	public IncShipSpeedCommand(GameWorld gw){
		
		super("Increase Ship Speed");
		target = gw;	
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		target.incShipSpeed();
		System.out.println("Ship Speed Increased Pressed.");
	}
}
