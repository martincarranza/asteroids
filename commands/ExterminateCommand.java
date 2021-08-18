package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class ExterminateCommand extends Command{
	
	private GameWorld target;
	
	public ExterminateCommand(GameWorld gw){
		
		super("Exterminate");
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		target.asteroidsCollide();
		System.out.println("Exterminate Pressed.");
	}
}
