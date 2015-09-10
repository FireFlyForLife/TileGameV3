package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import type.Point2D;
import type.Map;
import type.Point3D;
import type.flags.Ground;
import type.flags.PointA;
import type.flags.PointB;
import type.flags.VisualPath;
import logic.GamePanel;

public class MainMenuBar extends JMenuBar {
	GamePanel gamePanel;
	Setup parent;
	
	public MainMenuBar(GamePanel gamePanel, Setup parent){
		this.gamePanel = gamePanel;
		this.parent = parent;
		
		setupMenuBar();
	}
	
	private void setupMenuBar(){
		JMenu fileMenu = new JMenu("File");
		JMenuItem clearMap = new JMenuItem("New");
		clearMap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.VK_ALT));
		clearMap.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.map.fill(0, 0, gamePanel.map.getWidth(), gamePanel.map.getHeight(), 0, Map.MapType.TERRAIN, new Ground());
				for(int x = 0; x < gamePanel.map.getWidth(); x++){
					for(int y = 0; y < gamePanel.map.getHeight(); y++){
						for(int depth = 0; depth < gamePanel.map.getDepth(); depth++){
							gamePanel.map.setEntity(x, y, depth, null);
						}
					}
				}
				gamePanel.Tick();
			}
		});
		
		JMenuItem save = new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.VK_ALT));
		save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser("Select location to save the map.");
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int click = fc.showOpenDialog(parent);
				if(click == JFileChooser.APPROVE_OPTION){
					File file = fc.getSelectedFile();
					System.out.println("Path selected: " + file.getPath());
					try {
						gamePanel.SaveMap(file.getPath());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		JMenuItem load = new JMenuItem("load");
		load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.VK_ALT));
		load.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser("Select map to open.");
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int click = fc.showOpenDialog(parent);
				if(click == JFileChooser.APPROVE_OPTION){
					File file = fc.getSelectedFile();
					System.out.println("Path selected: " + file.getPath());
					
					try {
						gamePanel.LoadMap(file.getPath());
						gamePanel.Tick();
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		fileMenu.add(clearMap);
		fileMenu.add(save);
		fileMenu.add(load);
		JMenu pathMenu = new JMenu("Path");
			JMenuItem generatePath = new JMenuItem("Generate");
			generatePath.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					Point3D a = gamePanel.map.findEntity(new PointA());
					Point3D b = gamePanel.map.findEntity(new PointB());
					if(a == null || b == null)
						return;
					List<Point2D> l = gamePanel.map.findPath(a.getX(), a.getY(), b.getX(), b.getY());
					if(l != null){
						for(Point2D c : l){
							if(c == null)
								System.out.println("a coordinate returned by the pathfinder has a null coordinate");
							else
								gamePanel.map.addEntity(c.getX(), c.getY(), new VisualPath());
						}
						gamePanel.Tick();
					}
				}
			});
			JMenuItem clearPath = new JMenuItem("Clear");
			clearPath.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					Point3D ent = gamePanel.map.findEntity(new VisualPath());
					while(ent != null){
						gamePanel.map.setEntity(ent.getX(), ent.getY(), ent.getZ(), null);
						ent = gamePanel.map.findEntity(new VisualPath());
					}
				gamePanel.Tick();	
				}
			});
			JMenuItem pointA = new JMenuItem("Waypoint Start");
			pointA.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					gamePanel.primairyDrawFlag = new PointA();
				}				
			});
			JMenuItem pointB = new JMenuItem("Waypoint End");
			pointB.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					gamePanel.primairyDrawFlag = new PointB();
				}				
			});
		pathMenu.add(generatePath);
		pathMenu.add(clearPath);
		pathMenu.add(pointA);
		pathMenu.add(pointB);
		JMenu editMenu = new JMenu("Edit");
			JMenuItem editSomething = new JMenuItem("Edit Something");
		editMenu.add(editSomething);
		
		add(fileMenu);
		add(pathMenu);
		add(editMenu);
		add(new JSeparator(SwingConstants.VERTICAL));
		add(new JLabel("Primairy: "));
	//Get the options
		String[] s = new String[gamePanel.registery.getAll().size()];
		for(int i = 0; i < gamePanel.registery.getAll().size(); i++){
			s[i] = gamePanel.registery.getAll().get(i).toString();
		}
		JComboBox cb = new JComboBox(s);
		cb.setSelectedIndex(1);
		cb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox c = (JComboBox) e.getSource();
				gamePanel.primairyDrawFlag = gamePanel.registery.get(c.getSelectedIndex());
			}
		});
		add(cb);
		//menuBar.add(new JSeparator(SwingConstants.VERTICAL));
		add(new JLabel(" Secondairy: "));
		JComboBox cb2 = new JComboBox(s);
		cb2.setSelectedIndex(0);
		cb2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox c = (JComboBox) e.getSource();
				gamePanel.secondaryDrawFlag = gamePanel.registery.get(c.getSelectedIndex());
			}
		});
		add(cb2);
	}
}
