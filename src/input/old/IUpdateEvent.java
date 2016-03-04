package input.old;

import java.awt.event.KeyEvent;

public interface IUpdateEvent {
	public void addListener(EventMethod listener, int eventId);
	public void removeListener(EventMethod listener, int eventId);
	public void sentEvent(KeyEvent e);
}
