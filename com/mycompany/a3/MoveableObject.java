package com.mycompany.a3;

import java.lang.Math;
import java.util.Random;

import com.codename1.ui.geom.Dimension;

public abstract class MoveableObject extends GameObject implements IMoveable{
	private int speed;
	private int direction;
	
	//Randomly generates moveable objects speed and direction
	public MoveableObject(){
		Random rn = new Random();
		speed = rn.nextInt(11);//Range 0 to 10 (11 excluded from nextInt range)
		direction = rn.nextInt(360); //Range 0 to 359 (360 excluded from nextInt range)
	}
	
	//Setters and getters for speed, direction
	public void setSpeed(int newSpeed){
		speed = newSpeed;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public void setDirection(int newDirection){
		direction = newDirection;
	}
	
	public int getDirection(){
		return direction;
	}
	
	//Method for moving.  Over time, objects move and their X and Y update
	public void move(int elapsedMilli, Dimension dCmpSize){
		//Get values x,y, direction, and speed from GameObject 
		double x = super.getX();
		double y = super.getY();
		
		//Calculate theta, deltaX, and deltaY to update X and Y values
		double theta = 90 - direction; //Direction is in degrees. Formula: 90 - (0-359 degrees)
		double distance = speed * (elapsedMilli/10);
		double deltaX = Math.cos(Math.toRadians(theta)) * distance; //Formula dX: Cos(theta) * speed 
		double deltaY = Math.sin(Math.toRadians(theta)) * distance; //Formula dY: Sin(theta) * speed
		
		//update X and Y values (movement)
		double updatedX = Math.round(x + deltaX);
		double updatedY = Math.round(y + deltaY);
		
		if ( (updatedX >= dCmpSize.getWidth()) || (updatedX < 0) || (updatedY >= dCmpSize.getHeight()) 
				|| (updatedY<0) )
			direction = (direction + 135) % 360; //Do 90 turn, object head in diff direction
		
		super.setX(updatedX);
		super.setY(updatedY);
	}
	
	
}