package com.mycompany.a3;

import java.util.Observable;

public class GameWorldProxy extends Observable implements IGameWorld{

	/*Code here to accept and hold a GameWorld, provide implementations 
	*of all the public methods in a GameWorld, forward allowed calls to the actual
	*GameWorld, and reject calls to methods which the outside should not access in the GameWorld
	*/
	private GameWorld realGameWorld;
	
	public GameWorldProxy(GameWorld gw){
		realGameWorld = gw;
	}
	
	public void init() {
		
		//Empty in GameWorld and Proxy
	}
	
	public SpaceCollection getCollection(){
		
		return realGameWorld.getCollection();
	}

	//Setters and getters from GameWorld. Getters allowed
	//Setters are not allowed; Proxy should protect data from outside modification
	public void setHeight(int h){
		
		System.out.println("Setting Height Not Allowed! Cannot modify data from proxy.");
	}
	
	public int getHeight() {
		
		return realGameWorld.getHeight();
	}
	
	public void setWidth(int w){
		
		System.out.println("Setting Width Not Allowed! Cannot modify data from proxy.");
	}
	
	public int getWidth(){
		
		return realGameWorld.getWidth();
	}
	
	public void setScore(int s) {
		
		System.out.println("Setting Score Not Allowed! Cannot modify data from proxy.");
	}

	public int getScore() {
		
		return realGameWorld.getScore();
	}

	public void setLives(int l) {
		
		System.out.println("Setting Lives Not Allowed! Cannot modify data from proxy.");
	}

	public int getLives() {
		
		return realGameWorld.getLives();
	}

	public void setTime(int t) {
		
		System.out.println("Setting Time Not Allowed! Cannot modify data from proxy.");
	}

	public int getTime() {
		
		return realGameWorld.getTime();
	}

	public void setSound(boolean s) {
		
		System.out.println("Setting Sound Not Allowed! Cannot modify data from proxy.");
	}

	public boolean getSound() {
		
		return realGameWorld.getSound();
	}

	public void setNumMissiles(int mc) {
		
		System.out.println("Setting Missiles Not Allowed! Cannot modify data from proxy.");
	}

	public int getNumMissiles() {
		
		return realGameWorld.getNumMissiles();
	}

	public void addAsteroid() {
		
		System.out.println("Adding Asteroid Not Allowed! Cannot modify data from proxy.");
	}

	public void addSpaceStation() {
		
		System.out.println("Adding Space Station Not Allowed! Cannot modify data from proxy.");
	}

	public void addShip() {
		
		System.out.println("Adding Space Ship Not Allowed! Cannot modify data from proxy.");
	}

	public void incShipSpeed() {
		
		System.out.println("Increasing Speed Not Allowed! Cannot modify data from proxy.");
	}

	public void decShipSpeed() {
		
		System.out.println("Decreasing Speed Not Allowed! Cannot modify data from proxy.");
	}

	public void turnShipLeft() {
		
		System.out.println("Turning Left Not Allowed! Cannot modify data from proxy.");
	}

	public void turnShipRight() {
		
		System.out.println("Turning Right Not Allowed! Cannot modify data from proxy.");
	}

	public void fireMissile() {
		
		System.out.println("Firing Missile Not Allowed! Cannot modify data from proxy.");
	}

	public void hyperSpaceJump() {
		
		System.out.println("Hyperspace Jump Not Allowed! Cannot modify data from proxy.");
	}

	public void getNewMissiles() {
		
		System.out.println("New Missiles Not Allowed! Cannot modify data from proxy.");
	}

	public void missileKillAsteroid() {
		
		System.out.println("Killing Asteroid Not Allowed! Cannot modify data from proxy.");
	}

	public void shipCrash() {
		
		System.out.println("Crashing Ship Not Allowed! Cannot modify data from proxy.");
	}

	public void asteroidsCollide() {
		
		System.out.println("Extermination/Astroid Collision Not Allowed! Cannot modify data from proxy.");
	}

	public void clockTick(int elapsedMilli) {
		
		System.out.println("Ticking Clock Not Allowed! Cannot modify data from proxy.");
	}

	public void printState() {
		
		realGameWorld.printState();
	}

	public void map() {
		
		realGameWorld.map();
	}

	public void quit() {
		
		System.out.println("Quitting Game Not Allowed! Cannot modify data from proxy.");
	}

	


	
	
}//end GameWorldProxy
