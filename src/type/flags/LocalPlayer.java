package type.flags;

import input.Input;
import input.Input.Updates;
import input.InputEvent;
import input.InputListener;
import input.MoverEvent;
import type.Character;
import type.Map;

public class LocalPlayer extends Character implements InputListener{
	private static final long serialVersionUID = -2367314361144280392L;
	
	@Override
	public boolean onAdd(Map map, Input i) {
		i.addInputListener(this, Updates.KEY_PRESSED, Updates.KEY_RELEASED, Updates.KEY_TYPED);
		return true;
	}

	@Override
	public void inputReceived(Updates up, InputEvent ie) {
		if(up == Updates.KEY_TYPED)
			System.out.println("Key Typed");
	}

	@Override
	public void moveReceived(Updates up, MoverEvent me) {
		
	}
	
}
