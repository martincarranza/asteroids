package com.mycompany.a3;

import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

//Missile- an object fired to destroy asteroids
//Fired from front of ship
//fuelLevel decrements over time; disappears when 0
public class Missile extends MoveableObject implements IDrawable,ICollider{
	private int fuelLevel;
	private int size;
	private Vector<ICollider> collisionVector;
	
	public Missile(){
		
	}
	
	public Missile(Ship theShip){
		fuelLevel = 10; //Initially max fuel level of 10
		size = 30; 
		collisionVector = new Vector<ICollider>();
		int speedConstant = 5; //Constant: this is how much faster missiles are than the ship
		
		//Initial Missile direction, location equal to ship's. Speed is faster
		super.setDirection(theShip.getDirection());	
		super.setSpeed(theShip.getSpeed() + speedConstant); // speed of missile is 10 faster than ship's
		super.setX(theShip.getX());
		super.setY(theShip.getY());
		
		//set missile color to grey
		super.setColor(128, 128, 128);
	}
	
	public void setSpeed(){
		System.out.println("Error. Cannot change speed after Missile fired!");
	}
	
	public void setDirection(){
		System.out.println("Error. Cannot change direction after Missile fired!");
	}
	
	public void setFuelLevel(int fl){
		fuelLevel = fl;
	}
	
	public int getFuelLevel(){
		return fuelLevel;
	}
	
	public int getSize(){
		return size;
	}
	//ToString relevant info: X,Y,Color,Speed,Dir,fuelLevel
	public String toString(){
		int color = super.getColor();
		String ts = "Missile X: " + super.getX() + " Y: " + super.getY() + " Color: "
				+ ColorUtil.red(color) + "," + ColorUtil.green(color) + "," + ColorUtil.blue(color) 
				+ " Speed: " + super.getSpeed() + " Direction: " + super.getDirection() + " Fuel Level: "
				+ fuelLevel;
		return ts;
	}
	
	public void draw(Graphics g, Point pCmpRelParent){
		
		g.setColor(this.getColor());
		//Get location, which is center of object to be drawn
		int xLoc = (int) (pCmpRelParent.getX() + this.getX());
		int yLoc = (int) (pCmpRelParent.getY() + this.getY());
		//Calculate Top left corner of Rectangle. height 75, width 10
		int width = 30;
		int height = 30;
		xLoc = xLoc - (width/2);
		yLoc = yLoc - (height/2);
		
		g.fillRect(xLoc, yLoc, width, height);
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
		//Test if collision exists
		if(distBetweenCentersSqr <= radiiSqr )
			result = true;
		
		return result;
	}

	public void handleCollision(ICollider otherObj, GameWorld gw) {
		
		if(otherObj instanceof Asteroid){
			//Remove missile and asteroid, give player points
			gw.removeFromWorld(this);
			gw.removeFromWorld(otherObj);
			gw.setScore( gw.getScore() + 100 );
		}
		else{
			System.out.println("\nMissile Colliding with something other than Asteroid!");
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