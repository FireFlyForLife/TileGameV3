package type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import logic.pathFinder.PathFinder;

public class Map implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Flag[][][] mapData;
	private Flag[][][] terrainData;
	private Flag[][][] entityData;
	
	private Registery registery;
	
	public enum MapType{
		TERRAIN, ENTITY, GENERIC;
	}
	
	public Map(int width, int height, Registery reg){
		registery = reg;
		mapData = new Flag[width][height][20];
		terrainData = new Flag[width][height][20];
		entityData = new Flag[width][height][20];
		fill(0, 0, width, height, MapType.TERRAIN, registery.get(0));
	}
	
	public void fill(int startX, int startY, int width, int height, MapType type, Flag flag){
		fill(startX, startY, width, height, 0, type, flag);
	}
	public void fill(int startX, int startY, int width, int height, int depth, MapType type, Flag flag){
		for(int x = startX; x < width; x++){
			for(int y = startY; y < height; y++){
				switch(type){
				case TERRAIN:
					terrainData[x][y][depth] = flag;
					break;
				case ENTITY:
					addEntity(x, y, flag);
					break;
				case GENERIC:
					break;
				default:
					break;
				}
			}
		}
	}
	
	public void addEntity(int x, int y, Flag ent){
		int i;
		for(i = 0; entityData[x][y][i] != null; i++);
		entityData[x][y][i] = (Entity) ent;
	}
	
	public void addTerrain(int x, int y, Flag ter){
		int i;
		for(i = 0; terrainData[x][y][i] != null; i++);
		
		
		terrainData[x][y][i] = (Terrain) ter;
	}
	
	public void add(int x, int y, Flag flag){
		if(flag instanceof Terrain){
			addTerrain(x, y, flag);
		}else if(flag instanceof Entity){
			System.out.println("adding entity");
			addEntity(x, y, flag);
		}else{
			System.out.println(flag + ": Does not have a known superclass");
		}
	}
	
	public void setTerrain(int x, int y, int depth, Flag ter){
		terrainData[x][y][depth] = ter;
	}
	
	public void setEntity(int x, int y, int depth, Flag ent){
		entityData[x][y][depth] = ent;
	}
	
	public void set(int x, int y, int depth, Flag flag){
		if(flag instanceof Terrain){
			setTerrain(x, y, depth, flag);
		}else if(flag instanceof Entity){
			setEntity(x, y, depth, flag);
		}else{
			System.out.println(flag + ": Does not have a known superclass");
		}
	}
	
	public int getHeight(){
		return mapData[0].length;
	}
	
	public int getWidth(){
		return mapData.length;
	}
	
	public Flag getTerrainAt(int x, int y, int depth){
		return terrainData[x][y][depth];
	}
	
	public Flag getEntityAt(int x, int y, int depth){
		return entityData[x][y][depth];
	}
	
	public List<Flag> getEntity(int x, int y){
		List<Flag> ret = new ArrayList<Flag>();
		for(Flag f : entityData[x][y]){
			if(f != null)
				ret.add(f);
		}
		return ret;
	}
	
	public Point3D findEntity(Flag entity){
		for(int x = 0; x < getWidth(); x++){
			for(int y = 0; y < getHeight(); y++){
				for(int depth = 0; depth < getDepth(); depth++){
					if(getEntityAt(x, y, depth) != null && (getEntityAt(x, y, depth).getClass().getName() == entity.getClass().getName()))
						return new Point3D(x,y,depth);
				}
			}
		}
		return null;
	}
	
	public Point3D findTerrain(Flag terrain){
		for(int x = 0; x < getWidth(); x++){
			for(int y = 0; y < getHeight(); y++){
				for(int depth = 0; depth < getDepth(); depth++){
					if(getEntityAt(x, y, depth) != null && getEntityAt(x, y, depth) == (terrain))
						return new Point3D(x,y,depth);
				}
			}
		}
		return null;
	}
	
	public List<Flag> get(int x, int y){
		List<Flag> ret = new ArrayList<Flag>();
		if(terrainData[x][y] != null){
			for(Flag ter : terrainData[x][y]){
				if(ter != null)
					ret.add(ter);
			}
		}
		if(entityData[x][y] != null){
			for(Flag ent : entityData[x][y]){
				if(ent != null)
					ret.add(ent);
			}
		}
		/*int i;
		for(i = 0; ret.get(i) != null; i++);
		System.out.println(i);
		return ret.subList(0, i);*/
		return ret;
	}
	
	@Deprecated
	public void setMap(Map newMap) {
		mapData = newMap.mapData;
		entityData = newMap.entityData;
		terrainData = newMap.terrainData;
	}
	
	public void setSaveObject(SaveObject saveObj){
		if(saveObj.entityData != null)
			this.entityData = saveObj.entityData;
		if(saveObj.mapData != null)
			this.mapData = saveObj.mapData;
		if(saveObj.terrainData != null)
			this.terrainData = saveObj.terrainData;
	}
	
	public SaveObject getSaveObject(){
		SaveObject ret = new SaveObject();
		ret.entityData = this.entityData;
		ret.mapData = this.mapData;
		ret.terrainData = this.terrainData;
		return ret;
	}

	public int getDepth() {
		return mapData[0][0].length;
	}
	
	public List<Point2D> findPath(int xStart, int yStart, int xEnd, int yEnd){
		PathFinder localPf = new PathFinder(this, 1000, false);
		return localPf.findPath(xStart, yStart, xEnd, yEnd);
	}
}
