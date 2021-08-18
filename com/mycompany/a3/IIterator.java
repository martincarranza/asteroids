package com.mycompany.a3;

public interface IIterator {
	
	public boolean hasNext();
	public Object getNext();
	public void decIndex(int i);
	public int getIndex();
	public int getSize();
	public void setIndex(int i);
}
