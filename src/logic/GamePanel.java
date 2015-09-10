package logic;

import input.Input;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.pathFinder.PathFinder;
import type.Flag;
import type.Map;
import type.Registery;
import type.SaveObject;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	private JFrame parent;
	public Map map;
	public Registery registery;
	public Renderer renderer;
	public Input input;
	public PathFinder pathFinder;
	public Flag primairyDrawFlag;
	public Flag secondaryDrawFlag;
	
	public GamePanel(JFrame parent){
		this.parent = parent;
		registery = new Registery();
		map = new Map(50, 50, registery);
		renderer = new Renderer();
		input = new Input();
		primairyDrawFlag = registery.get(1);
		secondaryDrawFlag = registery.get(0);
		addMouseListener(this);
		addMouseListener(input);
		addMouseMotionListener(this);
		addMouseMotionListener(input);
		addKeyListener(this);
		addKeyListener(input);
	}

	public void Tick() {
		parent.repaint();
		repaint();
	}
	
	public void paint(Graphics g){
		int drawS = 1;
		Graphics2D g2d = (Graphics2D) g;
		if(drawS == 1){
			DrawNice(g);
		}else if(drawS == 0){
			DrawExact(g2d);
		}
	}

	private void DrawNice(Graphics g) {
		/*List<Tile> tileMap = map.getBuffer();
		int size = 15;
		for(Tile t : tileMap){
			int x = t.x * size;
			int y = t.y * size;
			g.setColor(t.flag.getColor());
			g.drawRect(x, y, size, size);
			g.setColor(g.getColor().brighter());
			g.fillRect(x + 1, y + 1, size - 1, size - 1);
		}*/
		/*Image buffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics gh = buffer.getGraphics();*/

		int size = 15;
		for(int y = 0; y < map.getHeight(); y++){
			for(int x = 0; x < map.getWidth(); x++){
				List<Flag> flags = map.get(x, y);
				for(Flag f : flags){
					f.draw(x, y, size, g);
				}
			}
		}
		//g.drawImage(buffer, 0, 0, null);
	}
		
	private void drawTiles(int x, int y, int size, Flag[] flags, Graphics2D g){
		for(Flag f : flags){
			g.setColor(f.getColor());
			g.drawRect(x, y, size, size);
			g.setColor(g.getColor().brighter());
			g.fillRect(x + 1, y + 1, size - 1, size - 1);
		}
	}
	
	private void drawEntity(int x, int y, int size, Color color, Graphics2D g){
		g.setColor(color);
		g.fillRect(Math.min(x + 4, x + size / 2), Math.min(y + 4, y + size / 2), size - 8, size - 8);
	}

	private void DrawExact(Graphics2D g) {
		/*List<Tile> tileMap = map.getTiles();
		for(Tile t : tileMap){
			g.setColor(t.flag.getColor());
			g.drawLine(t.x, t.y, t.x, t.y);
		}*/
		
	}

	public void SaveMap(String path) throws IOException{
		FileOutputStream fos = new FileOutputStream(path);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		SaveObject obj = map.getSaveObject();
		oos.writeObject(obj);
		oos.close();
	}
	
	public void LoadMap(String path) throws IOException,  ClassNotFoundException{
		FileInputStream fis = new FileInputStream(path);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object newMap = ois.readObject();
		if(newMap instanceof Map)
			map.setMap((Map)newMap);
		else if(newMap instanceof SaveObject)
			map.setSaveObject((SaveObject)newMap);
		else
			System.out.println("Map is not a known save file type, ignoring file.");
		ois.close();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Flag flag;
		if(e.getButton() == MouseEvent.BUTTON1)
			flag = primairyDrawFlag;
		else if(e.getButton() == MouseEvent.BUTTON3)
			flag = secondaryDrawFlag;
		else
			return;
		int x = e.getX() / 15;
		int y = e.getY() / 15;
	
		if(flag.onAdd(map)){
			if(flag.override())
				map.set(x, y, 0, flag);
			else
				map.add(x, y, flag);
		}
			Tick();
			System.out.println(Integer.toString(x) + "," + Integer.toString(y) + "," + flag.toString());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
