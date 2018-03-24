package dkeep.cli;

import java.awt.EventQueue;

import dkeep.logic.Game;

public class DKInterface {
	private Game dk = new Game();
	private GraphicInterface window;
	
	public DKInterface() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new GraphicInterface(dk);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		
	}	
}
