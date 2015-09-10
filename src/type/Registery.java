package type;

import java.util.ArrayList;
import java.util.List;

import type.flags.*;

public final class Registery {
	private List<Flag> closedList;
	public List<Flag> publicList;
	
	public Registery(){
		closedList = new ArrayList<Flag>();
		publicList = new ArrayList<Flag>();
		addAll(new Flag[]{new Ground(), new Rock(), new PointA(), new PointB(), new VisualPath()},false);
	}
	
	public boolean add(Flag flag, boolean invisible){
		if(!closedList.contains(flag))
			closedList.add(flag);
		else
			return false;
		if(!invisible && !publicList.contains(flag)){
			publicList.add(flag);
			return true;
		}
	return false;
	}
	
	public boolean addAll(Flag[] flags,boolean invisible){
		boolean ret = true;
		for(Flag f : flags){
			if(!add(f, invisible)){
				ret = false;
			}
		}
		return ret;
	}
	
	public Flag get(int index){
		return publicList.get(index);
	}
	
	public List<Flag> getAll(){
		return publicList;
	}
	
	public List<Flag> getAbsolutelyEverything(){
		return closedList;
	}
	
}
