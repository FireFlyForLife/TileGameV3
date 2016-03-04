package type;

import java.awt.Graphics;

public abstract class Terrain extends Flag {
	
	@Override
	public void draw(int x, int y, int size, Graphics buffer){
		buffer.setColor(getColor());
		buffer.drawRect(x * size, y * size, size, size);
		buffer.setColor(buffer.getColor().brighter());
		buffer.fillRect(x * size + 1, y * size + 1, size - 1, size - 1);
	}
	
	@Override
	public boolean override(){
		return true;
	}
	
	@Override
	public void update() {	}
}
