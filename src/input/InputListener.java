package input;

import input.Input.Updates;

public interface InputListener {
	public void inputReceived(Updates up, InputEvent ie);
	public void moveReceived(Updates up, MoverEvent me);
}
