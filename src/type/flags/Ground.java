package type.flags;

import java.awt.Color;

import type.Map;
import type.Terrain;

public class Ground extends Terrain {
	private static final long serialVersionUID = -6215626891282753362L;

	@Override
	public Color getColor() {
		return Color.GRAY;
	}

	@Override
	public float getCost(){
		return 1.0f;
	}

	@Override
	public boolean blocked(Map map) {
		return false;
	}
}
