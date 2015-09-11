package logic.pathFinder;

import type.Map;

public class PathFinder extends SuperPathFinder {

	public PathFinder(Map map, int maxSearchDistance, boolean allowDiagMovement) {
		super(map, maxSearchDistance, allowDiagMovement);
	}

	public PathFinder(Map map, int maxSearchDistance, boolean allowDiagMovement, Heuristic heuristic) {
		super(map, maxSearchDistance, allowDiagMovement, heuristic);
	}

	@Override
	protected boolean blocked(int x, int y, Map map) {
		//return !map.getEntity(x, y).isEmpty();
		return map.getTerrainAt(x, y, 0).blocked(map);
	}

	@Override
	protected float getCost(int startX, int startY, int endX, int endY, Map map) {
		return map.getTerrainAt(startX, startY, 0).getCost();
	}

}
