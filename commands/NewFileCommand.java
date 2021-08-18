package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class NewFileCommand extends Command{
	
	public NewFileCommand(){
		
		super("New");
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		System.out.println("New File Pressed!");
	}
	
}