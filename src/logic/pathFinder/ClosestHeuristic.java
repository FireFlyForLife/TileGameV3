package logic.pathFinder;

import type.Map;

public class ClosestHeuristic implements Heuristic{

	@Override
	public float getCost(Map map, int startX, int startY, int endX, int endY) {
		float dx = endX - startX;
		float dy = endY - startY;
		
		float result = (float) (Math.sqrt((dx*dx)+(dy*dy)));
		
		return result;
	}

}
