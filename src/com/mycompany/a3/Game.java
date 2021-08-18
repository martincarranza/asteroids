package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.Toolbar;


//Game class, which acts as the Controller
public class Game extends Form implements Runnable{
	private GameWorld gw;
	private MapView mv;
	private PointsView pv;
	
	
	public Game(){
		
		gw = new GameWorld();	//Create OBSERVABLE
		pv = new PointsView(gw); //OBSERVER for points
		mv = new MapView(gw);		//OBSERVER for map
		gw.addObserver(mv);		//Register map observer
		gw.addObserver(pv);		//Register points observer
		
		
		/*Code here to create menus, Command Objects for each command,
		 * add commands to Command menu, create a control panel for buttons,
		 * add buttons to the control panel, add commands to the button, and add
		 * control panel, map view panel , points view panel to the form */
		//Creating toolbar and setting borderlayout
		Toolbar tb = new Toolbar();
		this.setToolbar(tb);
		this.setTitle("Asteroids");
		this.setLayout(new BorderLayout());
		tb.addCommandToRightBar(new Command("File"));
		tb.addCommandToLeftBar(new Command("Commands"));
		
		
		Border mapBorder = Border.createLineBorder(2, ColorUtil.MAGENTA);
		mv.getAllStyles().setBorder(mapBorder);
		Container cmdContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS)); 
		
		
		//Create buttons for commands container
		Button a = new Button("a");
		Button b = new Button("b");
		Button s = new Button("s");
		Button i = new Button("i");
		Button d = new Button("d");
		Button l = new Button("l");
		Button r = new Button("r");
		Button f = new Button("f");
		Button j = new Button("j");
		Button n = new Button("n");
		Button k = new Button("k");
		Button c = new Button("c");
		Button x = new Button("x");
		Button t = new Button("t");
		Button q = new Button("q");
		
		
		//Create commands for keys AND command menu
		AddAsteroidCommand addACmd = new AddAsteroidCommand(gw);
		AddStationCommand addStCmd = new AddStationCommand(gw);
		AddShipCommand addSCmd = new AddShipCommand(gw);
		IncShipSpeedCommand incS = new IncShipSpeedCommand(gw);
		DecShipSpeedCommand decS = new DecShipSpeedCommand(gw);
		TurnLeftCommand turnL = new TurnLeftCommand(gw);
		TurnRightCommand turnR = new TurnRightCommand(gw);
		FireMissileCommand fireM = new FireMissileCommand(gw);
		JumpCommand jmp = new JumpCommand(gw);
		NewMissilesCommand newM = new NewMissilesCommand(gw);
		KillAsteroidCommand killM = new KillAsteroidCommand(gw);
		CrashCommand crash = new CrashCommand(gw);
		ExterminateCommand ext = new ExterminateCommand(gw);
		TickCommand tick = new TickCommand(gw);
		QuitCommand qt = new QuitCommand(gw);
		
		
		//Create commands needed for overflow menu (File menu) of toolbar
		NewFileCommand nfCmd = new NewFileCommand();
		SaveFileCommand sfCmd = new SaveFileCommand();
		UndoCommand uCmd = new UndoCommand();
		SoundCommand sCmd = new SoundCommand(gw);		
		AboutCommand aboutCmd = new AboutCommand();
		
		
		//Add commands to sidemenu of toolbar
		tb.addCommandToSideMenu(addACmd);
		tb.addCommandToSideMenu(addStCmd);
		tb.addCommandToSideMenu(addSCmd);
		tb.addCommandToSideMenu(incS);
		tb.addCommandToSideMenu(decS);
		tb.addCommandToSideMenu(turnL);
		tb.addCommandToSideMenu(turnR);
		tb.addCommandToSideMenu(fireM);
		tb.addCommandToSideMenu(jmp);
		tb.addCommandToSideMenu(newM);
		tb.addCommandToSideMenu(killM);
		tb.addCommandToSideMenu(crash);
		tb.addCommandToSideMenu(ext);
		tb.addCommandToSideMenu(tick);
		tb.addCommandToSideMenu(qt);
		
		
		//add commands to overflow menu of toolbar New, Save, Undo, Sound, About, Quit
		tb.addCommandToOverflowMenu(nfCmd);
		tb.addCommandToOverflowMenu(sfCmd);
		tb.addCommandToOverflowMenu(uCmd);
		tb.addCommandToOverflowMenu(sCmd);
		tb.addCommandToOverflowMenu(aboutCmd);
		tb.addCommandToOverflowMenu(qt);
		
		
		//Attach commands to buttons using setCommand()
		a.setCommand(addACmd);
		b.setCommand(addStCmd);
		s.setCommand(addSCmd);
		i.setCommand(incS);
		d.setCommand(decS);
		l.setCommand(turnL);
		r.setCommand(turnR);
		f.setCommand(fireM);
		j.setCommand(jmp);
		n.setCommand(newM);
		k.setCommand(killM);
		c.setCommand(crash);
		x.setCommand(ext);
		t.setCommand(tick);
		q.setCommand(qt);
		
		
		//Add Key Listeners to form
		this.addKeyListener('a', addACmd);
		this.addKeyListener('b', addStCmd);
		this.addKeyListener('s', addSCmd);
		this.addKeyListener('8', incS);
		this.addKeyListener('2', decS);
		this.addKeyListener('4', turnL);
		this.addKeyListener('6', turnR);
		this.addKeyListener('f', fireM);
		this.addKeyListener('j', jmp);
		this.addKeyListener('n', newM);
		this.addKeyListener('k', killM);
		this.addKeyListener('c', crash);
		this.addKeyListener('x', ext);
		this.addKeyListener('t', tick);
		this.addKeyListener('Q', qt);
		
		
		//Add borders to Command container and buttons
		Border myCmdBorder = Border.createLineBorder(2, ColorUtil.GRAY);
		cmdContainer.getAllStyles().setBorder(myCmdBorder);
		a.getAllStyles().setBorder(myCmdBorder);
		b.getAllStyles().setBorder(myCmdBorder);
		s.getAllStyles().setBorder(myCmdBorder);
		i.getAllStyles().setBorder(myCmdBorder);
		d.getAllStyles().setBorder(myCmdBorder);
		l.getAllStyles().setBorder(myCmdBorder);
		r.getAllStyles().setBorder(myCmdBorder);
		f.getAllStyles().setBorder(myCmdBorder);
		j.getAllStyles().setBorder(myCmdBorder);
		n.getAllStyles().setBorder(myCmdBorder);
		k.getAllStyles().setBorder(myCmdBorder);
		c.getAllStyles().setBorder(myCmdBorder);
		x.getAllStyles().setBorder(myCmdBorder);
		t.getAllStyles().setBorder(myCmdBorder);
		q.getAllStyles().setBorder(myCmdBorder);
		
		
		//Add buttons to Command container
		cmdContainer.add(a);
		cmdContainer.add(b);
		cmdContainer.add(s);
		cmdContainer.add(i);
		cmdContainer.add(d);
		cmdContainer.add(l);
		cmdContainer.add(r);
		cmdContainer.add(f);
		cmdContainer.add(j);
		cmdContainer.add(n);
		cmdContainer.add(k);
		cmdContainer.add(c);
		cmdContainer.add(x);
		cmdContainer.add(t);
		cmdContainer.add(q);
		
		
		this.add(BorderLayout.NORTH, pv);
		this.add(BorderLayout.CENTER, mv);
		this.add(BorderLayout.WEST, cmdContainer);
		this.show();
		UITimer timer = new UITimer(this);
		timer.schedule(50, true, this);
		
		//Get width and height of map container. 
		gw.setHeight(mv.getHeight());
		gw.setWidth(mv.getWidth());
		
		 
		gw.init();		//Sets initial state of the game (Empty)
	}//end Game() constructor
	
	public void run(){
		//Clock ticks, which checks status of objects and moves them if necessary
		int elapsedMilli = 50;
		gw.clockTick(elapsedMilli);
		mv.repaint();
	}//end run
	
}//end Game
