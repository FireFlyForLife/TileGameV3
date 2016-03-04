package type.flags;

import input.Input;

import java.awt.Color;

import type.Entity;
import type.Map;

public class PointA extends Entity {
	private static final long serialVersionUID = 1220632553959224102L;

	@Override
	public Color getColor() {
		return Color.GREEN;
	}
	
	@Override
	public boolean onAdd(Map map, Input i){
		for(int x = 0; x < map.getWidth(); x++){
			for(int y = 0; y < map.getHeight(); y++){
				for(int depth = 0; depth < map.getDepth(); depth++){
					if(map.getEntityAt(x, y, depth) != null && map.getEntityAt(x, y, depth) instanceof PointA)
						map.setEntity(x, y, depth, null);
				}
			}
		}
		return true;
	}

}
