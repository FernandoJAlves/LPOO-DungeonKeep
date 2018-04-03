package dkeep.cli;

import java.util.Scanner;

public class IO {
	private Scanner s = new Scanner(System.in);
	public IO() {
		
	}
	
	public char read() {
		System.out.println();
		System.out.print("Move: ");
		return s.next().charAt(0);
	}
	
	public void print(char[][] map) {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		for(int i = 0; i < map.length;i++) {
			for(int j = 0; j < map[0].length;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
	public void close(){
		s.close();
	}

}
