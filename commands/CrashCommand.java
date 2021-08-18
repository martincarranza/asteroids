package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CrashCommand extends Command{
	
	private GameWorld target;
	
	public CrashCommand(GameWorld gw){
		
		super("Crash Ship");
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		target.shipCrash();
		System.out.println("Crash Ship Pressed.");
	}
}
