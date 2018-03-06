package dkeep.cli;

import java.util.Scanner;

public class Input {
	private Scanner s = new Scanner(System.in);
	public Input() {
		
	}
	
	char read() {
		System.out.println();
		System.out.print("Move: ");
		return s.next().charAt(0);
	}
	
	public void close(){
		s.close();
	}

}
