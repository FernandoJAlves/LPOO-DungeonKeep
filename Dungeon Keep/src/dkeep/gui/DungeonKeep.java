package dkeep.gui;

import java.awt.EventQueue;

import dkeep.logic.Game;

public class DungeonKeep {
	private static Game dk = new Game();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new GraphicInterface(dk);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		
	}
}
