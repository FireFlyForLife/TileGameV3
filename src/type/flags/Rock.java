package type.flags;

import java.awt.Color;

import type.Map;
import type.Terrain;

public class Rock extends Terrain {
	private static final long serialVersionUID = 1032170865906228809L;

	@Override
	public Color getColor() {
		return Color.BLACK;
	}
	
	@Override
	public float getCost(){
		return 100000.0f;
	}

	@Override
	public boolean blocked(Map map) {
		return true;
	}

}
