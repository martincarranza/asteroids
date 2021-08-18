package com.mycompany.a3;

public interface ICollider {

	public boolean collidesWith(ICollider otherObj);
	public void handleCollision(ICollider otherObj,GameWorld gw);
	public void addCollision(ICollider otherObj);
	public void removeCollision(ICollider otherObj);
	public boolean contains(ICollider otherObj);
}
