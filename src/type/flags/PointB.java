package type.flags;

import java.awt.Color;

import type.Entity;
import type.Map;

public class PointB extends Entity {
	private static final long serialVersionUID = -3608166999372683126L;

	@Override
	public Color getColor() {
		return Color.RED;
	}
	
	@Override
	public boolean onAdd(Map map){
		for(int x = 0; x < map.getWidth(); x++){
			for(int y = 0; y < map.getHeight(); y++){
				for(int depth = 0; depth < map.getDepth(); depth++){
					if(map.getEntityAt(x, y, depth) != null && map.getEntityAt(x, y, depth) instanceof PointB)
						map.setEntity(x, y, depth, null);
				}
			}
		}
		return true;
	}
}
