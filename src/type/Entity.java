package type;

import java.awt.Graphics;

public abstract class Entity extends Flag {
	@Override
	public void draw(int x, int y, int size, Graphics buffer) {
		buffer.setColor(getColor());
		buffer.drawRect(x * size + (size / 4), y * size + (size / 4), size / 2, size / 2);
		buffer.setColor(buffer.getColor().brighter());
		buffer.fillRect(x * size + (size / 4) + 1, y * size + (size / 4) + 1, size / 2 - 1, size / 2 - 1);
	}
	
	@Override
	public boolean blocked(Map map){
		return false;
	}
	
	@Override
	public boolean override(){
		return false;
	}
	
	@Override
	public float getCost(){
		return 0.0f;
	}
	
}
