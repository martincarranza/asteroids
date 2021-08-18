package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class TurnRightCommand extends Command{
	
private GameWorld target;
	
	public TurnRightCommand(GameWorld gw){
		
		super("Turn Ship Right");
		target = gw;	
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		target.turnShipRight();
		System.out.println("Turn Ship Right Pressed");
	}
}
