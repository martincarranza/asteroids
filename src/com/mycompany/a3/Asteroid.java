package com.mycompany.a3;
import java.util.Random;
import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

//Floating asteroid;obstacle that can crash into ship
//If collision occurs with ship, player loses life (3 max lives possible)
//If collision occurs with missile, missile and asteroid are destroyed:Player gets points
public class Asteroid extends MoveableObject implements IDrawable, ICollider{

		private int size;
		private Vector<ICollider> collisionVector;
		public Asteroid(){
			//Speed and Direction randomly generated in MoveableObject constructor
			//from 0-10 and 0-359, respectively
			
			//Randomly generate size of asteroid
			Random rn = new Random();
			size = rn.nextInt(150) + 50; //Size generated between 50 and 200
			collisionVector = new Vector<ICollider>();
			//set asteroid color to maroon
			super.setColor(128, 0, 0);
		}
		
		public int getSize(){
			return size;
		}
		
		//Prints information: X,Y,Color,Speed,Direction,Size
		public String toString(){
			int color = super.getColor();
			String ts = "Asteroid X: " + super.getX() + " Y: " + super.getY() + " Color: "
					+ ColorUtil.red(color) + "," + ColorUtil.green(color) + "," + ColorUtil.blue(color) 
					+ " Speed: " + super.getSpeed() + " Direction: " + super.getDirection() + " Size: "
					+ size;
			return ts;
		}
		
		public void draw(Graphics g, Point pCmpRelParent){
			
			g.setColor(this.getColor());
			//Get location, which is center of object
			int xLoc = (int) (pCmpRelParent.getX() + this.getX()); 
			int yLoc = (int) (pCmpRelParent.getY() + this.getY());
			//Fill circle after calculating Top Left Corner of Circle. 
			//Radius = 50, Angle = 60 degrees->to calculate Top left corner from center of circle
			//using trig
			int r = 50;
			double angle = Math.toRadians(60);
			xLoc = (int) (xLoc - (r * Math.cos(angle)));
			yLoc = (int) (yLoc - (r * Math.sin(angle)));
			g.fillArc(xLoc, yLoc, size, size, 0, 360);
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
			if(distBetweenCentersSqr <= radiiSqr ){
				//Collision occurs
				result = true;
			}
			
			return result;
		}

		public void handleCollision(ICollider otherObj, GameWorld gw) {
			
			if(otherObj instanceof Asteroid){
				//Remove both from world
				gw.removeFromWorld(this);
				gw.removeFromWorld(otherObj);
			}
			
			else if(otherObj instanceof Ship){
				//Remove ship from world, dec Lives by 1, create new ship 
				gw.removeFromWorld(this);
				gw.removeShip();
				gw.setLives(gw.getLives() - 1);
				if (gw.getLives() <= 0){
					Dialog.show("Game Over!", "You Have No Lives Left! Game Over!", "Ok", null);
					System.exit(0);
				}
				gw.addShip();
			}
			
			else if(otherObj instanceof Missile){
				//Remove asteroid and missile from world, give player 100 points for kill
				gw.removeFromWorld(this);
				gw.removeFromWorld(otherObj);
				gw.setScore(gw.getScore() + 100);
			}
			
			else{
				System.out.println("\nAsteroid Colliding with SpaceStation!");
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
