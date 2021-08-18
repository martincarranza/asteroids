package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AddShipCommand extends Command{

	private GameWorld target;
	
	public AddShipCommand(GameWorld gw){
		
		super("Add Space Ship");
		target = gw;	
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		target.addShip();
		System.out.println("Add Space Ship Pressed.");
	}
}
