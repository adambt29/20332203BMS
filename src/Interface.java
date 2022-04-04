
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
	public static void main() {
		ternarySearch tst=new ternarySearch("C:\\Users\\abeatty\\eclipse"
				+ "\\elclipse saved work\\algorithms and data structures 2\\"
				+ "final project\\stops.txt");//may need to change file
		edge graph=new edge();
		
	}
}
