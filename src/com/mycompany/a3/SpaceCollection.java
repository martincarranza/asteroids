package com.mycompany.a3;


import java.util.Vector;

public class SpaceCollection implements ICollection{
	
	private Vector<Object> theCollection;
	
	public SpaceCollection(){
		
		theCollection = new Vector<Object>();
	}
	
	public void add(Object newObject){
		
		theCollection.addElement(newObject);
	}
	
	
	
	public IIterator getIterator(){
		
		return new SpaceVectorIterator();
	}
	
	public boolean contains(Object obj) {
		
		return theCollection.contains(obj);
	}

	public void remove(Object o){
		
		theCollection.remove(o);
		
	}
	
	//Inner class to iterate through the objects in SpaceCollection
	private class SpaceVectorIterator implements IIterator{
		
		private int currElementIndex;
		
		public SpaceVectorIterator(){
			
			currElementIndex = -1;
		}
		
		public boolean hasNext(){
			
			if (theCollection.size() <= 0)
				return false;
			
			if (currElementIndex >= theCollection.size() - 1 )
				return false;
			
			return true;
		}
		
		public Object getNext(){
			
			currElementIndex++;
			return(theCollection.elementAt(currElementIndex));
		}
		
		public void decIndex(int i){
			
			currElementIndex = currElementIndex - i;
		}
		
		public int getIndex(){
			return currElementIndex;
		}
		
		public void setIndex(int i){
			currElementIndex = i;
		}
		
		public int getSize(){
			return theCollection.size();
		}
		
	}//end SpaceVectorIterator


}//end SpaceCollection
