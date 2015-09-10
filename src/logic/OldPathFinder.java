package logic;


import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.SortedSet;

import type.Flag;
import type.Map;
import type.Tile;

public class OldPathFinder {
	/** The set of nodes that have been searched through */
	private List closed = new ArrayList();
	/** The set of nodes that we do not yet consider fully searched */
	private SortedList open = new SortedList();//could be a java.util.SortedSet;

	/** The map being searched */
	private Map map;

	/** The complete set of nodes across the map */
	private Node[][] nodes;
	/** True if we allow diaganol movement */
	private boolean allowDiagMovement;

	public OldPathFinder(Map map){
		this.map = map;
		allowDiagMovement = false;
		nodes = new Node[map.getWidth()][map.getHeight()];
		for (int x=0;x<map.getWidth();x++) {
			for (int y=0;y<map.getHeight();y++) {
				nodes[x][y] = new Node(x,y);
			}
		}
	}

	public List<Tile> findPath(Tile start, Tile end){
		/*List<Tile> openPath = new ArrayList();

		if(start == null || end == null){
			return null;
		}
		openPath.add(start);
		List<Tile> closedPath = new ArrayList();
		List<Tile> returnPath = new ArrayList();
		while(!(openPath.get(openPath.size()).x == end.x && openPath.get(openPath.size()).y == end.y)){
			Tile current = openPath.get(0);


		}

		return returnPath;*/

		if (map.get(end.x, end.y).isEmpty()) {
			return null;
		}

		// initial state for A*. The closed group is empty. Only the starting
		nodes[start.x][start.y].cost = 0;
		nodes[start.x][start.y].depth = 0;
		// tile is in the open list and it'e're already there
		
		closed.clear();
		open.clear();
		open.add(nodes[start.x][start.y]);
		
		nodes[start.x][start.y].parent = null;
		// while we haven'n't exceeded our max search depth
		int maxDepth = 0;
		int maxSearchDistance = 1000;
		while ((maxDepth < maxSearchDistance) && (open.size() != 0)) {
			// pull out the first node in our open list, this is determined to 

			// be the most likely to be the next step based on our heuristic

			Node current = (Node) open.first();
			if (current == nodes[end.x][end.y]) {
				break;
			}

			open.remove(0);
			closed.add(current);

			// search through all the neighbours of the current node evaluating

			// them as next steps

			for (int x=-1;x<2;x++) {
				for (int y=-1;y<2;y++) {
					// not a neighbour, its the current tile

					if ((x == 0) && (y == 0)) {
						continue;
					}

					// if we're not allowing diaganol movement then only 

					// one of x or y can be set

					if (!allowDiagMovement) {
						if ((x != 0) && (y != 0)) {
							continue;
						}
					}

					// determine the location of the neighbour and evaluate it

					int xp = x + current.x;
					int yp = y + current.y;

					//if (isValidLocation(start.x, start.y, xp, yp)) {
					if(true){
						// the cost to get to this node is cost the current plus the movement

						// cost to reach this node. Note that the heursitic value is only used

						// in the sorted open list

						float nextStepCost = current.cost + map.getTerrainAt(xp, yp, 0).getCost();//  getMovementCost(current.x, current.y, xp, yp);
						Node neighbour = nodes[xp][yp];

						// if the new cost we've determined for this node is lower than 

						// it has been previously makes sure the node hasn'e've
						// determined that there might have been a better path to get to

						// this node so it needs to be re-evaluated

						if (nextStepCost < neighbour.cost) {
							if (open.contains(neighbour)) {
								open.remove(neighbour);
							}
							if (closed.contains(neighbour)) {
								closed.remove(neighbour);
							}
						}

						// if the node hasn't already been processed and discarded then

						// reset it's cost to our current cost and add it as a next possible

						// step (i.e. to the open list)

						if (!open.contains(neighbour) && !(closed.contains(neighbour))) {
							neighbour.cost = nextStepCost;
							neighbour.heuristic = getHeuristicCost(xp, yp, x, y);
							maxDepth = Math.max(maxDepth, neighbour.setParent(current));
							open.add(neighbour);
						}
					}
				}
			}
		}
		return null;
	}
	private float getHeuristicCost(int xp, int yp, int x, int y) {
		// TODO Auto-generated method stub
		return 0;
	}
	private class SortedList {
		/** The list of elements */
		private ArrayList list = new ArrayList();
		
		/**
		 * Retrieve the first element from the list
		 *  
		 * @return The first element from the list
		 */
		public Object first() {
			return list.get(0);
		}
		
		/**
		 * Empty the list
		 */
		public void clear() {
			list.clear();
		}
		
		/**
		 * Add an element to the list - causes sorting
		 * 
		 * @param o The element to add
		 */
		public void add(Object o) {
			list.add(o);
			Collections.sort(list);
		}
		
		/**
		 * Remove an element from the list
		 * 
		 * @param o The element to remove
		 */
		public void remove(Object o) {
			list.remove(o);
		}
	
		/**
		 * Get the number of elements in the list
		 * 
		 * @return The number of element in the list
 		 */
		public int size() {
			return list.size();
		}
		
		/**
		 * Check if an element is in the list
		 * 
		 * @param o The element to search for
		 * @return True if the element is in the list
		 */
		public boolean contains(Object o) {
			return list.contains(o);
		}
	}
	
	private class Node implements Comparable {
		/** The x coordinate of the node */
		private int x;
		/** The y coordinate of the node */
		private int y;
		/** The path cost for this node */
		private float cost;
		/** The parent of this node, how we reached it in the search */
		private Node parent;
		/** The heuristic cost of this node */
		private float heuristic;
		/** The search depth of this node */
		private int depth;

		/**
		 * Create a new node
		 * 
		 * @param x The x coordinate of the node
		 * @param y The y coordinate of the node
		 */
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		/**
		 * Set the parent of this node
		 * 
		 * @param parent The parent node which lead us to this node
		 * @return The depth we have no reached in searching
		 */
		public int setParent(Node parent) {
			depth = parent.depth + 1;
			this.parent = parent;

			return depth;
		}

		/**
		 * @see Comparable#compareTo(Object)
		 */
		public int compareTo(Object other) {
			Node o = (Node) other;

			float f = heuristic + cost;
			float of = o.heuristic + o.cost;

			if (f < of) {
				return -1;
			} else if (f > of) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
