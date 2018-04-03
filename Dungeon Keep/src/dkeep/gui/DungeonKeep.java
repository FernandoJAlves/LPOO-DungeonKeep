package dkeep.gui;

import java.awt.EventQueue;

import dkeep.logic.Game;
import dkeep.storage.DataStorage;

public class DungeonKeep {
	private static Game dk = new Game();
	private static GraphicInterface window;
	private static DataStorage ds = new DataStorage();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setWindow(new GraphicInterface(dk));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		
	}

	public static GraphicInterface getWindow() {
		return window;
	}

	public static void setWindow(GraphicInterface window) {
		DungeonKeep.window = window;
	}

	public static DataStorage getDS() {
		return ds;
	}
}
