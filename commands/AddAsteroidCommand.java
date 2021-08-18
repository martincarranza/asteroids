package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AddAsteroidCommand extends Command{
	
	private GameWorld target;
	
	public AddAsteroidCommand(GameWorld gw){
		
		super("Add Asteroid");
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		target.addAsteroid();
		System.out.println("Add Asteroid Pressed");
		
	}
}
