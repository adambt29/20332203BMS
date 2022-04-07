package finalProject;

import java.util.Scanner;  
/*main program- asks the user questions, 
takes input and then bring the program
relevant method to give user the correct
outout*/
public class Interface {
	
	/*public int choice(ternarySearch string, edge weight) {
		System.out.print("choose between the folowing options/n"+
	            "1:find the shortest distance between two bus stops/n"+
				"2: to search for a bus stop/n"+
	            "3: search for trips and an arrival time"+
				"4: to quit");
		int input= userInput.nextInt();
		return input;
	}*/
	public static void main(String args[]) {
	/*	ternarySearch tst=new ternarySearch("C:\\Users\\abeatty\\eclipse"
				+ "\\elclipse saved work\\algorithms and data structures 2\\"
				+ "final project\\stops.txt");//may need to change file
		edge graph=new edge();
		**/
		Scanner userInput=new Scanner(System.in);
		boolean quit=false;
		String user="";
		int q=0;
		shortestPath problem1=new shortestPath();
		ternarySearch problem2=new ternarySearch(user);
		function3 problem3=new function3(); //temp name change when set up
		
		while(quit=false) {
			user="";
			q=0; //just to reset 
			System.out.print("choose between the folowing options/n"+
		            "1:find the shortest distance between two bus stops/n"+
					"2: to search for a bus stop/n"+
		            "3: search for trips and an arrival time"+
					"4: to quit");
			if(userInput.hasNextInt()) {
				q=userInput.nextInt();
				boolean returnToInterface=false;
				switch(q) {
				case 1:
					int stop1=0;
					int stop2=0;
					while(!exit && !returnToInterface) {
						System.out.print("enter the stop you are starting at\n"
								//+ "and the stop you wish to finish \n"
								+ "or enter quit/n"
								+ "or enter return to go back to the main menu");
						if(userInput.hasNextInt()) {
							stop1=userInput.nextInt();
							System.out.print("now enter the stop you which to finish at");
							if(userInput.hasNextInt())	{
								stop2=userInput.nextInt();
								System.out.print("the shortest path from "+stop1
										+"to "+stop2
										+ problem1.shortestPath(stop1,stop2));
							}
						}
						else {
					  user=userInput.nextLine().strip();
					  if(user.equalsIgnoreCase("back")) {
							returnToInterface=true;
						}
						else if(user.equalsIgnoreCase("quit")) {
							quit=true;
						
						else {
							System.out.print("invalid");
						}
						}
					}
				}break;
				case 2:
					while(!quit && !returnToInterface) {
						System.out.print("enter back to return to main menu\n"
								+ "or enter quit\n"
								+ "or enter the bus stop you are looking for");
						user=userInput.nextLine().strip();
						if(user.equalsIgnoreCase("back")) {
							returnToInterface=true;
						}
						else if(user.equalsIgnoreCase("quit")) {
							quit=true;
						
						else {
							problem2=new ternarySearch(user);
							ArrayList<String>fullLine=problem2.getFullLine();
							System.out.print(getFullLine.size()+" matches were found");
							for(int i=0;i<getFullLine.size;i++) {
								System.out.print(getFullLine.get(i));
							}
						}
					}
				}break;
				case 3:
					while(!quit && !returnToInterface) {
						System.out.print("enter back to return to main menu\n"
								+ "or enter quit\n"
								+ "or enter the arrival time you are looking for");
						user=userInput.nextLine().strip();
						if(user.equalsIgnoreCase("back")) {
							returnToInterface=true;
						}
						else if(user.equalsIgnoreCase("quit")) {
							quit=true;
						}
					else if(problem3.checkRealTime()) {
						for(int i=0;i<getGivenTimes.size();i++) {
						 System.out.print(getGivenTimes(user).get(i));
						}
					}
					else {
						System.out.print("invalid");
					}
					}
					break;
				case 4:
					quit=true;
					break;
				}
			}
			else {
				System.out.print("invalid: user must enter an interger please try again");
				userInput.nextLine();
			}
		}
	}
}
