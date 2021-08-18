package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AddStationCommand extends Command{

		private GameWorld target;
		
		public AddStationCommand(GameWorld gw){
			
			super("Add Space Station");
			target = gw;
		}
		
		@Override
		public void actionPerformed(ActionEvent e){
			
			target.addSpaceStation();
			System.out.println("Add Station Pressed");
		}
}
