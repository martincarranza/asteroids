package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class TickCommand extends Command{
	
	private GameWorld target;
	
	public TickCommand(GameWorld gw){
		
		super("Tick Clock");
		target = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		target.clockTick(20);
		System.out.println("Tick ClocK Pressed. Clock Ticked!");
	}
}

