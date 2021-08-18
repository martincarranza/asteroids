package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.geom.Dimension;

/*
 * 
 * GameWorld must extend Observable
 * and implement IGameWorld
 * 
 * Model in MVC- Code here to hold and manipulate world objects
 */
public class GameWorld extends Observable implements IGameWorld{
	
	//theCollection holds all the GameObjects and has an iterator
	//GameWorld holds a list of observers
	private SpaceCollection theCollection;
	private Vector<Observer> myObservers; //Can add to observers and notify observers
	GameWorldProxy gwProxy = new GameWorldProxy(this);
	
	//Data for points, lives, elapsed time, and sound on/off
		//# of Missiles obtained throughout code using getShip() to return
		//the single ship object and getMissileCount()
	private int playerScore;
	private int playerLives;
	private int gameTime;
	private boolean sound;
	private int height;
	private int width;
	
	
	public GameWorld(){
		//Instantiates variables in GameWorld
		theCollection = new SpaceCollection();
		myObservers = new Vector<Observer>();
		playerScore = 0;
		playerLives = 3;
		gameTime = 0;
		sound = true;
		
	}
	
	public void init(){
		//code here to create
		//init objects and setup
		//-World initially Empty-
		
	}
	
	public SpaceCollection getCollection(){
		
		return theCollection;
	}
	
	//Setters and getters for private data: score, lives, time, sound, height, width
	public void setHeight(int h){
		
		height = h;
	}
	
	public int getHeight(){
		
		return height;
	}
	
	public void setWidth(int w){
		
		width = w;
	}
	
	public int getWidth(){
		
		return width;
	}
	
	public void setScore(int s){
		playerScore = s;
		this.notifyObservers();
	}
	
	public int getScore(){
		return playerScore;
	}
	
	public void setLives(int l){
		playerLives = l;
		this.notifyObservers();
	}
	
	public int getLives(){
		return playerLives;
	}
	
	public void setTime(int t){
		gameTime = t;
		this.notifyObservers();
	}
	
	public int getTime(){
		return gameTime;
	}
	
	public void setSound(boolean s){
		sound = s;
		this.notifyObservers();
	}
	
	public boolean getSound(){
		return sound;
	}
	
	public void setNumMissiles(int mc){
		Ship s = Ship.getShip(gwProxy);
		s.setMissileCount(mc);
		this.notifyObservers();
	}
	
	public int getNumMissiles(){
		Ship s = Ship.getShip(gwProxy);
		return s.getMissileCount();
	}
	
	
	//METHODS here to
	//manipulate world objects
	//and related game state data as mentioned in A1
	
	public void addAsteroid(){
		Asteroid myAsteroid = new Asteroid();
		theCollection.add(myAsteroid);
		this.notifyObservers();
	}
	
	public void addSpaceStation(){
		SpaceStation mySpaceStation = new SpaceStation();
		theCollection.add(mySpaceStation);
		this.notifyObservers();
	}
	
	public void addShip(){

		//Create ship if does not exist using singleton pattern in Ship class
		//if ship exits, simply returns existing ship
		//Do not add to collection if ship exists;Only 1 ship can exist
		Ship myShip = Ship.getShip(gwProxy);

			if (theCollection.contains(myShip))
					System.out.println("Ship Already Exists! Not added.");
			else{
				theCollection.add(myShip);
				myShip.setX(this.getWidth() / 2 );
				myShip.setY(this.getHeight() / 2);
			}
		this.notifyObservers();
	}
	
	//Getting old speed, adding incAmount, then passing new speed
	public void incShipSpeed(){
		Ship myShip = Ship.getShip(gwProxy);	//Returns ship if exists, creates if does not exist
		int oldSpeed = myShip.getSpeed();
		int incAmount = 1;
		myShip.changeSpeed(oldSpeed + incAmount);
	}
	
	//Getting old speed, subtracting decAmount, then passing new speed
	public void decShipSpeed(){
		Ship myShip = Ship.getShip(gwProxy);	//Returns ship if exists, creates if does not exist
		int oldSpeed = myShip.getSpeed();	
		int decAmount = 1;
		
		if(decAmount > oldSpeed){
			System.out.println("Cannot decrement speed any further!");
		}
		else{
			myShip.changeSpeed(oldSpeed - decAmount);
		}
	}
	
	//Getting old direction, subtracting from it, then passing new direction
	public void turnShipLeft(){
		Ship myShip = Ship.getShip(gwProxy);	//Returns ship if exists, creates if does not exist
		int oldDirection = myShip.getDirection();
		int leftAmount = 45; //turn left by 45 degrees
		myShip.changeDirection(oldDirection - leftAmount);
	}
	
	//Getting old direction, adding to it, then passing new direction
	public void turnShipRight(){
		Ship myShip = Ship.getShip(gwProxy);	//Returns ship if exists, creates if does not exist
		int oldDirection = myShip.getDirection();
		int rightAmount = 45; //turn right by 45 degrees
		myShip.changeDirection(oldDirection + rightAmount);	
	}
	
	//Firing missile from ship.
	//Instantiates a missile if missileCount > 0
	public void fireMissile(){
		Ship myShip = Ship.getShip(gwProxy);
		int missileCount = myShip.getMissileCount();
		
		if(missileCount <= 0){
			System.out.println("No missiles to fire!");
		}
		else{
			Missile myMissile = new Missile(myShip);	//Missile constructor sets X,Y,speed,direction based...
			theCollection.add(myMissile);					//on myShip.  Missile added to ArrayList gameObjs
			myShip.setMissileCount(missileCount - 1); 
			this.notifyObservers();						//Notify observers due to missile decrement
		}
	}
	
	//Sets ship location to center of world
	public void hyperSpaceJump(){
		Ship myShip = Ship.getShip(gwProxy);
		myShip.setX(this.getWidth() / 2);
		myShip.setY(this.getHeight() / 2);
	}
	
	//Ship reloads at spacestation
	public void getNewMissiles(){
		Ship myShip = Ship.getShip(gwProxy);
		myShip.setMissileCount(10); 	//Set to max number of missiles
		this.notifyObservers();	//Notify observers due to missile change
	}
	
	//missile destroys asteroid.  Score increased, first missile/asteroid instance removed
	public void missileKillAsteroid(){
	
		IIterator theElements = theCollection.getIterator();
		//Increment score by 100 for killing asteroid
		playerScore += 100;	
		
		
		Object myMissile = new Object();
		//If Missile(s) exists in theCollection, remove first one and only one
		while(theElements.hasNext()){
			myMissile = theElements.getNext();
			if(myMissile instanceof Missile){
				theCollection.remove(myMissile);
				break;
			}
		}
		
		//Get new iterator to reset element index
		theElements = theCollection.getIterator();
		
		//If Asteroid(s) exists in theCollection, remove first one and only one
		Object myAsteroid = new Object();
		while(theElements.hasNext()){
			myAsteroid = theElements.getNext();
			if(myAsteroid instanceof Asteroid){
				theCollection.remove(myAsteroid);
				break;
			}
		}
		this.notifyObservers();
	}//end missileKillAsteroid
	
	//Ship crash into asteroid; adjust player life, remove ship and asteroid from arraylist gameObjs
	public void shipCrash(){
		
		IIterator theElements = theCollection.getIterator();
		playerLives--; //decrease player life for dying
		
		
		
		if (playerLives == 0){
			System.out.println("Game Over! Exiting!");
			System.exit(0);
		}
		
		//Remove ship from world due to crash
		Ship myShip = Ship.getShip(gwProxy);
		theCollection.remove(myShip);		
		
		//Remove first instance of Asteroid due to crash
		Object myAsteroid = new Object();
		while(theElements.hasNext()){
			myAsteroid = theElements.getNext();
			if(myAsteroid instanceof Asteroid){
				theCollection.remove(myAsteroid);
				break;
			}
		}
		this.notifyObservers();
	}//end shipCrash
	
	//Two asteroids collide. Remove two instances of asteroids from arraylist gameObjs
	public void asteroidsCollide(){
		
		IIterator theElements = theCollection.getIterator();
		int count = 0;	//count of asteroids removed. Must remove 2.
		
		Object myAsteroid = new Object();
		while(theElements.hasNext()){
			myAsteroid = theElements.getNext();
			if(myAsteroid instanceof Asteroid){
				theCollection.remove(myAsteroid);
				count++;
			}
			
			if(count == 2)
				break;
		}
		this.notifyObservers();
	}//end asteroidsCollide
	
	//Moves relevant objects, decrements missile fuel levels,
	//and changes space stations blinking light if necessary
	public void clockTick(int elapsedMilli){
		
		//Iterate through all the elements using iterator to move/update
		IIterator theElements = theCollection.getIterator();
		Object spo = new Object();
		while( theElements.hasNext() == true){
			spo = theElements.getNext();
			
			//Test if movable, Move all movable objs
			if(spo instanceof MoveableObject){	
				MoveableObject myMoveable = (MoveableObject) spo;
				Dimension dCmpSize = new Dimension(width, height);
				myMoveable.move(elapsedMilli, dCmpSize);
				}
			
			//Decrement fuel levels, remove if necessary
			if(spo instanceof Missile){
				Missile myMissile = (Missile) spo;
				int fl = myMissile.getFuelLevel();
					if(fl == 0){
						theCollection.remove(myMissile);
						theElements.decIndex(1);
					}
					else{
						myMissile.setFuelLevel(fl-1); //remove 1 from fuel level
					}
			}
			
			//Change blinkingLight if necessary
			if(spo instanceof SpaceStation){
				SpaceStation myStation = (SpaceStation) spo;
				int br = myStation.getBlinkRate();
				if(gameTime % br == 0){
					boolean bl = myStation.getBlinkingLight();
					myStation.setBlinkingLight(!bl); 	//Toggle blinking light (T-on, F-off)
				}
			}
			
		}//endWhile
		//Check if moving caused any collisions
		theElements = theCollection.getIterator(); 
		while(theElements.hasNext()){
			ICollider curObj = (ICollider) theElements.getNext();
			
			//Check if curObj collides with any other Object
			IIterator theElements2 = theCollection.getIterator();
			
			while(theElements2.hasNext()){
				ICollider otherObj = (ICollider) theElements2.getNext();
				
				//Check for collision. Handle collision if collision exists AND has not been handled before 
				if(otherObj!=curObj){
					if(curObj.collidesWith(otherObj) && (curObj.contains(otherObj) == false) ){
						curObj.handleCollision(otherObj, this);
						//Add objects to each others collisionVector to prevent multiple handlings.
						curObj.addCollision(otherObj);
						otherObj.addCollision(curObj);
						theElements2.setIndex(-1); //Refresh iterator due to removals
						}
					else if( (curObj.collidesWith(otherObj) == false) && (curObj.contains(otherObj)) ){
						//Case: objects not colliding but otherObj in collisionVector of curObj
						curObj.removeCollision(otherObj);
						otherObj.removeCollision(curObj);
					}
				}
		
			}//end innerWhile, checking for collision w/ curObj
			
		}//end outerrWhile, iterating through all objects in game
		
		
		
		gameTime++; //clock ticks, adds 1 to elapsed gameTime
		this.notifyObservers();
	}//end clockTick()

	public void printState(){
		//get current missile count from ship
		Ship myShip = Ship.getShip(gwProxy);
		int mc = myShip.getMissileCount();
		
		//output relevant info
		System.out.println("\nPlayer Score: " + playerScore);
		System.out.println("Current Missile Count: " + mc);
		System.out.println("Elapsed Game Time: " + gameTime);
		System.out.println("Player Lives: " + playerLives);
	}//endPrintState
	
	public void map(){
		IIterator theElements = theCollection.getIterator();
		
		System.out.println("\n"); //Line break for readability
		//Print toString for all items in gameObjs arrayList
		
		while (theElements.hasNext()){
			GameObject myObject = (GameObject) theElements.getNext();
			String map = myObject.toString();
			System.out.println(map);
		}
	}//Map
	
	
	public void quit(){
		
		Boolean bOk;
		bOk = Dialog.show("Confirm Quit", "Are You Sure You Want To Quit?", "Ok", "Cancel");
		if (bOk){
			Display.getInstance().exitApplication();
		}
	}//Quit
	
	public void addObserver(Observer o){
	
		myObservers.add(o);
	}
	
	//Use the Proxy design pattern to pass a GameWorldProxy
	//that does not allow for data modifying from observers
	public void notifyObservers(){
		
		
		for (int i = 0; i < myObservers.size(); i++){
			Observer o = (Observer) myObservers.get(i);
			o.update((Observable) gwProxy, null);
		}
	}
	
	public void removeFromWorld(ICollider obj){
		theCollection.remove(obj);
	}
	
	public void removeShip(){
		Ship myShip = Ship.getShip(gwProxy);
		theCollection.remove(myShip);	
		this.notifyObservers();
	}
	
}//GameWorld
