package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class JumpCommand extends Command{

	private GameWorld target;
	
	public JumpCommand(GameWorld gw){
		
		super("Hyperspace Jump");
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		target.hyperSpaceJump();
		System.out.println("Jumped Through Hyperspace!");
	}
}
