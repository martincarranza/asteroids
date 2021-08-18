package com.mycompany.a3;

import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

//Ship that can fire missiles.
//Missile count decrements by 1 after firing. Max count is 10.
//Can refuel at a space station
public class Ship extends MoveableObject implements ISteerable, IDrawable,ICollider{
	private int missileCount;
	private static Ship theShip;	//Implementing singleton pattern for single ship
	private int size;
	private Vector<ICollider> collisionVector;
	
	private Ship(GameWorldProxy gw){
		missileCount = 10;	//Initially has max missileCount of 10
		size = 100;
		collisionVector = new Vector<ICollider>();
		super.setX(gw.getWidth() / 2);	//Setting ship at center of world 
		super.setY(gw.getHeight() / 2);
		super.setSpeed(0);	//initial speed 0
		super.setDirection(0); //initial dir 0, facing north
		
		//set ship color to navy
		super.setColor(0, 0, 128);
	}
	//Enforcing singleton design pattern
	public static Ship getShip(GameWorldProxy gw){
		if(theShip == null){
			theShip = new Ship(gw);
			}
		return theShip;
		
	}
	//Implementing interface ISteerable
	public void changeDirection(int newDirection){
		super.setDirection(newDirection);
		
	}
	
	
	
	public void changeSpeed(int newSpeed){
		super.setSpeed(newSpeed);
		
	}
	
	
	//Setters,getters
	public void setMissileCount(int mc){
		missileCount = mc;
		
	}

	public int getMissileCount(){
		return missileCount;
		
	}
	
	public int getSize(){
		return size;
	}
	//toString X,Y,Color,MissileCount
	public String toString(){
		int color = super.getColor();
		String ts = "Ship X: " + super.getX() + " Y: " + super.getY() + " Color: "
				+ ColorUtil.red(color) + "," + ColorUtil.green(color) + "," + ColorUtil.blue(color) 
				+ " Speed: " +super.getSpeed() + " Direction: "+ super.getDirection() +
				" Missile Count: " + missileCount;
		return ts;
	}

	public void draw(Graphics g, Point pCmpRelParent){
		
		g.setColor(this.getColor());
		//Get location, which is center of object
		int xLoc = (int) (pCmpRelParent.getX() + this.getX());
		int yLoc = (int) (pCmpRelParent.getY() + this.getY());
		int height = size;
		int width = size;
		//Calculate Top Left corner of rounded Square
		xLoc = xLoc - (width/2);
		yLoc = yLoc - (height/2);
		
		g.fillRoundRect(xLoc, yLoc, width, height, 50, 50);
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
		
		if(otherObj instanceof Asteroid){
			//Remove ship from world, dec Lives by 1, create new ship 
			gw.removeFromWorld(otherObj);
			gw.removeShip();
			gw.setLives(gw.getLives() - 1);
			if (gw.getLives() <= 0){
				Dialog.show("Game Over!", "You Have No Lives Left! Game Over!", "Ok", null);
				System.exit(0);
			}
			gw.addShip();
		}
		
		else if(otherObj instanceof SpaceStation){
			//get new missile supply
			gw.getNewMissiles();
		}
		
		else{
			System.out.println("\nShip Collide with Missile!?");
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