package logic.pathFinder;

import type.Map;

public interface Heuristic {
	
	public float getCost(Map map, int startX, int startY, int endX, int endY);
}
