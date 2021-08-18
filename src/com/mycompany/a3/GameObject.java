package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;

public abstract class GameObject {
	
	private double x, y;
	private int color;
	
	//randomly sets initial location in 1024 X 768 world
	public GameObject(){		 
		Random rn = new Random();
		x = rn.nextInt(1199); //0 to 1198 range (1199 excluded)
		y = rn.nextInt(1870); // 0 to 1869 (1870 excluded)
	}
	
	//setters and getters for X,Y, and color
	public void setX(double newX){
		x = newX;
	}
	
	public void setY(double newY){
		y = newY;
	}
	
	public double getX(){
		return x;	
	}
	
	public double getY(){
		return y;
	}
	
	public void setColor(int r, int g, int b){
		color = ColorUtil.rgb(r,g,b);
		
	}
	
	public int getColor(){
		return color;
	}

	public abstract int getSize();
	
	
}