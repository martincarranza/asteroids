package commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command{
	
	public AboutCommand(){
		
		super("About");
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
	
		Dialog.show("About", "Name: Martin Carranza\nCourse: CSC 133", "Ok", null);
		System.out.println("About Pressed!");
	}
	
	
	
}
