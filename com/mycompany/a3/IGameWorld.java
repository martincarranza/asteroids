package com.mycompany.a3;

public interface IGameWorld {
	//specifications here for all GameWorld methods 
	public void init();
	
	//Setters and getters for private data: score, lives, time, sound
	public void setHeight(int h);
	public int getHeight();
	public void setWidth(int w);
	public int getWidth();
	public void setScore(int s);
	public int getScore();
	public void setLives(int l);
	public int getLives();
	public void setTime(int t);
	public int getTime();
	public void setSound(boolean s);
	public boolean getSound();
	public void setNumMissiles(int mc);
	public int getNumMissiles();
	
	//METHODS here to
	//manipulate world objects
	//and related game state data as mentioned in A1
	public void addAsteroid();
	public void addSpaceStation();
	public void addShip();
	public void incShipSpeed();
	public void decShipSpeed();
	public void turnShipLeft();
	public void turnShipRight();
	public void fireMissile();
	public void hyperSpaceJump();
	public void getNewMissiles();
	public void missileKillAsteroid();
	public void shipCrash();
	public void asteroidsCollide();
	public void clockTick(int elapsedMilli);
	public void printState();
	public void map();
	public void quit();
	
}//end IGameWorld
