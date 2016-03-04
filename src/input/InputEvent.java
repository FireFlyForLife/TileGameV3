package input;

public class InputEvent {
	public final int keyEvent;
	public final boolean keyState;
	
	public InputEvent(int keyEvent, boolean keyState){
		this.keyEvent = keyEvent;
		this.keyState = keyState;
	}
}
