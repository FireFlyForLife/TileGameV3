package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class Input implements KeyListener, MouseListener, MouseMotionListener{
	private List<InputListener> listeners = new ArrayList<InputListener>();
	private List<InputListener>[] updates = (ArrayList<InputListener>[]) new ArrayList[0];
	
	public enum Updates{
		KEY_PRESSED,
		KEY_RELEASED,
		KEY_TYPED,
		MOUSE_PRESSED,
		MOUSE_RELEASED,
		MOUSE_CLICKED,
		MOUSE_MOVED,
		MOUSE_DRAGGED,
		MOUSE_ENTERED,
		MOUSE_EXITED
	}
	
	/* The binds array contains the keybindings of the game.
	 * Object[i][0] = int keycode
	 * Object[i][1] = String name(jump etc.)
	 * Object[i][2] = additional parameter
	 * Object[i][3] = second additional parameter
	 */
	private Object[][] binds;
	public boolean[] keyState;
	private boolean useState;
	private boolean useUpdate;
	
	public Input(){
		this(getDefaultBinds(), true, true);
	}
	
	public Input(Object[][] binds){
		this(binds, true, true);
	}
	
	public Input(Object[][] binds, boolean useStates, boolean useUpdates){
		this.binds = binds;
		this.useState = useStates;
		this.useUpdate = useUpdates;
		this.keyState = new boolean[binds.length];
		for(int i = 0; i < binds.length; i++){
			keyState[i] = false;
		}
		int i = InputListener.class.getDeclaredMethods().length;
	}
	
	public static Object[][] getDefaultBinds(){
		Object[][] ret = new Object[6][4];
		ret[0][0] = KeyEvent.VK_D; ret[0][1] = "Right";
		ret[1][0] = KeyEvent.VK_A; ret[1][1] = "Left";
		ret[2][0] = KeyEvent.VK_W; ret[2][1] = "Up";
		ret[3][0] = KeyEvent.VK_S; ret[3][1] = "Down";
		ret[4][0] = KeyEvent.VK_SPACE; ret[4][1] = "Jump";
		ret[5][0] = KeyEvent.VK_SHIFT; ret[5][1] = "Run";
		return ret;
	}
	
	public boolean getState(int keyCode){
		return keyState[getIndex(keyCode)];
	}
	
	public boolean getState(String name){
		return keyState[getIndex(name)];
	}
	
	public Object[][] getBinds(){
		return binds;
	}
	
	public void setBinds(Object[][] binds){
		this.binds = binds;
	}
	
	public int getKeyCode(int index){
		return (int) binds[index][0];
	}
	
	public void setKeyCode(int index, int keyCode){
		binds[index][0] = keyCode;
	}
	
	public String getName(int index){
		return (String) binds[index][1];
	}
	
	public void setName(int index, String newName){
		binds[index][1] = newName;
	}
	
	public int getIndex(int keyCode){
		for(int index = 0; binds[index][0] != null; index++){
				if(((int)binds[index][0])==keyCode)
					return index;
		}
		return -1;
	}
	
	public int getIndex(String name){
		for(int index = 0; binds[index][1] != null; index++){
				if(((String)binds[index][1])==name)
					return index;
		}
		return -1;
	}
	
	public void addInputListener(InputListener listener){
		listeners.add(listener);
		
	}
	
	public void removeInputListener(InputListener listener){
		listeners.remove(listener);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(useState)
			keyState[getKeyCode(e.getKeyCode())] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(useState)
			keyState[getKeyCode(e.getKeyCode())] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	private class ListenerListComponent{
		public InputListener listener;
		public List updates;
	}
}
	