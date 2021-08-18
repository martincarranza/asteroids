package com.mycompany.a3;
import java.util.Random;
import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

//Class is a fixed SpaceStation
//Holds an unlimited amount of missiles; can resupply ships 
public class SpaceStation extends FixedObject implements IDrawable,ICollider{
	
	private int blinkRate; //specifies period: on for X seconds, off for X seconds
	private boolean blinkingLight; //True is 'on', False is 'off'
	private int size;
	private Vector<ICollider> collisionVector;
	
	public SpaceStation(){
		Random rn = new Random();
		blinkRate = rn.nextInt(10) + 1; //blinkRate generated between 1 and 10 (11 excluded from range)
		blinkingLight = true;		//lights on
		size = 100;
		collisionVector = new Vector<ICollider>();
		//set spacestation color to yellow
		super.setColor(255, 255, 0);
	}
	
	public int getBlinkRate(){
		return blinkRate;
	}
	
	public void setBlinkRate(int br){
		blinkRate = br;
	}
	
	public boolean getBlinkingLight(){
		return blinkingLight;
	}
	
	public void setBlinkingLight(boolean bl){
		blinkingLight = bl;
	}
	
	public int getSize(){
		return size;
	}
	
	public String toString(){
		int color = super.getColor();
		//Print if blinkinglight on or off
		String bl;
		if (blinkingLight == true){
			bl = "On!";
		}
		else{
			bl = "Off!";
		}
		
		//X,Y,Color,Id,BlinkRate,BlinkLight status
		String ts = "SpaceStation X: " + super.getX() + " Y: " + super.getY() + " Color: "
				+ ColorUtil.red(color) + "," + ColorUtil.green(color) + "," + ColorUtil.blue(color) 
				+ " Id: " + super.getId() + " BlinkRate: " + blinkRate + " BlinkingLight: "
				+ bl;
		return ts;
	}
	
	public void draw(Graphics g, Point pCmpRelParent){
		
		g.setColor(this.getColor());
		//Get location; center of object
		int xLoc = (int) (pCmpRelParent.getX() + this.getX());
		int yLoc = (int) (pCmpRelParent.getY() + this.getY());
		//Get Top Left Corner of Square. Width, Height = 100
		xLoc = xLoc - (size/2);
		yLoc = yLoc - (size/2);
		
		if (this.getBlinkingLight()){
			g.fillRect(xLoc, yLoc, size, size);
		}
		else{
			g.drawRect(xLoc, yLoc, size, size);
		}
		
	}

	public boolean collidesWith(ICollider otherObj) {
		
		//Using Bounding Circles
		boolean result = false;
		//Get object locations, which are the objects center
		int thisCenterX = (int) this.getX();
		int thisCenterY = (int) this.getY();
		int otherCenterX = (int) ((GameObject) otherObj).getX();
		int otherCenterY = (int) ((GameObject) otherObj).getY();
		//Find distance between centers
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx*dx + dy*dy);
		
		//Find square of (Radius1 + Radius2)
		int thisRadius = size/2;
		int otherRadius = ((GameObject) otherObj).getSize() / 2;
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + 
				otherRadius*otherRadius);
		//Test for collision
		if(distBetweenCentersSqr <= radiiSqr )
			result = true;
		
		return result;
	}

	public void handleCollision(ICollider otherObj, GameWorld gw) {
		
		if(otherObj instanceof Ship){
			//Give ship new missile supply
			gw.getNewMissiles();
		}
		else{
			System.out.println("\nSpace Station Colliding with something other than Ship!");
		}
		
		gw.notifyObservers();
	}//end handleCollision
	
	public void addCollision(ICollider otherObj){
		collisionVector.addElement(otherObj);
	}
	
	public void removeCollision(ICollider otherObj){
		collisionVector.remove(otherObj);
	}
	
	public boolean contains(ICollider otherObj){
		if (collisionVector.contains(otherObj))
			return true;
		else
			return false;
	}
	
}
