package com.mycompany.a3;

public abstract class FixedObject extends GameObject{
	
	private int id;	//unique identification number for each instantiation 
	private static int numFixedObjs = 0; //Variable belongs to class, Maintains number of fixedObjects
	
	public FixedObject(){
		numFixedObjs++;		//Increments upon instantiation to record num of fixed objects
		id = numFixedObjs;	//Id begins at 1, changes by 1 with each instantiation
		
	}
	
	//ID setters and getters
	public void setId(){
		System.out.println("Error. Not possible to set Id after creation.");
	}
	
	public int getId(){
		return id;
	}
	
	//Override parent setX,SetY to prevent changing location
	public void setX(){
		System.out.println("Error. Fixed Object: Not possible to set X.");
	}
	
	public void setY(){
		System.out.println("Error. Fixed Object: Not possible to set Y.");
	}
	
}
