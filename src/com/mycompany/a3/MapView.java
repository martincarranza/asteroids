package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class MapView extends Container implements Observer{
	
	private GameWorldProxy gwProxy;
	
	public MapView(GameWorld gw){
		
		gwProxy = new GameWorldProxy(gw);
		//added to Observer list in Game
		
	}
	
	public void update(Observable o, Object arg){
		
		/*Code here to output current map info to the console, based on data from Observable
		 *Received Observable is a GameWorldProxy and can be case to IGameWorld to access
		 *GameWorld methods.
		 */
		gwProxy = (GameWorldProxy) o;
		gwProxy.map();	//Code that prints to console.
		repaint();
	}
	
	public void paint(Graphics g){
		
		super.paint(g);
		Point pCmpRelParent = new Point(getX(),getY());
		SpaceCollection theCollection = new SpaceCollection();
		theCollection = gwProxy.getCollection();
		IIterator iterator = theCollection.getIterator();
		Object gObj; 
		
		while (iterator.hasNext()){
			
			gObj = iterator.getNext();
			((IDrawable) gObj).draw(g, pCmpRelParent);
			
		}
		
	}
	
}
