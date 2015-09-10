package type;

import java.io.Serializable;

public class SaveObject implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Flag[][][] mapData;
	public Flag[][][] terrainData;
	public Flag[][][] entityData;
}
