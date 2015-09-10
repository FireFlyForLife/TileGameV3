package type;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Flag implements Serializable{
	private static final long serialVersionUID = 7414458948479691583L;

	/*private List<Flag> internal;
	
	public Flag(){
		internal = new ArrayList<Flag>();
	}
	
	public void add(Flag flag){
		internal.add(flag);
	}
	
	public void remove(Flag flag){
		internal.remove(flag);
	}
	
	public List<Flag> get(){
		return internal;
	}
	
	public Flag getAt(int index){
		return internal.get(index);
	}*/
	
	@Override
	public String toString(){
		return this.getClass().getSimpleName();
	}
	
	public abstract void draw(int x, int y, int size, Graphics buffer);
	
	public abstract Color getColor();
	
	public boolean onAdd(Map map) {
		return true;
	}
	
	public boolean useInput(){
		return false;
	}
	
	public abstract boolean blocked(Map map);
	
	public abstract boolean override();
	
	public abstract float getCost();
}
