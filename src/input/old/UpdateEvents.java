package input.old;

import input.Input.Updates;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class UpdateEvents implements IUpdateEvent{
	private List<EventMethod>[] listeners = (ArrayList<EventMethod>[]) new ArrayList[KeyEvent.KEY_LAST];
	
	public UpdateEvents(){
		
	}

	@Override
	public void addListener(EventMethod listener, int eventId) {
		listeners[eventId].add(listener);
	}

	@Override
	public void removeListener(EventMethod listener, int eventId) {
		listeners[eventId].remove(listener);
	}

	@Override
	public void sentEvent(KeyEvent e) {
		switch(e.getID()){
		case KeyEvent.KEY_PRESSED:
			for(EventMethod m : listeners[e.getID()]){
				((KeyPressed)m).keyPressed(e);
			}
		}
	}
	
}