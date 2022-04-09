package finalProject;

import java.util.ArrayList;
import java.util.Scanner;

public class Interface {
public static Scanner userInput=new Scanner(System.in);

	public static void main(String[] args) {
		
boolean quit=false;
String user="";
int q=0;


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
		case 1://may need a bit of fixing
			int stop1=0;
			int stop2=0;
			while(!quit && !returnToInterface) {
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
								+"to "+stop2);
							//	+  problem1.shortestPath(stop1,stop2));
					}
				}
				else {
			  user=userInput.nextLine().strip();
			  if(user.equalsIgnoreCase("back")) {
					returnToInterface=true;
				}
				else if(user.equalsIgnoreCase("quit")) {
					quit=true;
				}
				else {
					System.out.print("invalid");
				}
				}
				break;
		}
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
				}
				else {
					stops=searchByName(user,stops);
					for(Stops stop:stops) {
						System.out.print(stop);
					
				}
				}
				break;
		}
			
		case 3:
			int hours = readIntFromUser("Type in the arrival time hours: ");
			while (hours > 23 || hours < 0) 
			{
				hours = readIntFromUser("Hours must be 0-23: ");
			}
			int minutes = readIntFromUser("Type in the arrival time minutes: ");
			while (minutes > 59 || minutes < 0) 
			{
				minutes = readIntFromUser("Minutes must be 0-59: ");
			}
			int seconds = readIntFromUser("Type in the arrival time seconds: ");
			while (seconds > 59 || seconds < 0)
			{
				seconds = readIntFromUser("Seconds must be 0-59: ");
			}
			int time = (hours * 60 * 60) + (minutes * 60) + seconds;
			graph.findTripByTime(time);
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
	public static boolean isExit(String str) 
	{
		return str.equalsIgnoreCase("exit");
	}

	public static int readIntFromUser(String question) 
	{
		System.out.print(question);
		
		String inputStr = userInput.next();
		if (isExit(inputStr))
			System.exit(0);
		while (inputStr.replaceAll("[0-9]", "").length() != 0) 
		{
			System.out.print("Input must be a positive integer: ");
			inputStr = userInput.next();
			if (isExit(inputStr))
				System.exit(0);
		}

		return Integer.parseInt(inputStr);
	}
}	
