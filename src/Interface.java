package finalProject;

import java.util.Scanner;

public class Interface {
	public static Scanner userInput=new Scanner;
	
	public int choice(ternarySearch string, edge weight) {
		System.out.print("choose between the folowing options/n"+
	            "1:find the shortest distance between two bus stops/n"+
				"2: to search for a bus stop/n"+
	            "3: search for trips and an arrival time"+
				"4: to quit");
		int input= userInput.nextInt();
		return input;
	}
	public void main() {
		
	}
}
