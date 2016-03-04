package type;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Character extends Entity{
	private static final long serialVersionUID = 2628230286063137251L;

	@Override
	public Color getColor() {
		return Color.BLUE;
	}

}
