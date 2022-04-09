import java.util.Scanner;

public class Main 
{
	private Graph graph;
	private Scanner userInput;
	public static String user = "";

	
	public static void main(String[] args) 
	{
		//starts the program
		new Main(user);
	}

	Main(String user) 
	{
		graph = new Graph(user + "C:\\Users\\Owner\\git\\20332203BMS\\stops.txt", user + 
				"C:\\Users\\Owner\\git\\20332203BMS\\stop_times.txt", 
				user + "C:\\Users\\Owner\\git\\20332203BMS\\transfers.txt");
		userInput = new Scanner(System.in);
		run();
	}

	/**
	 * Run the program
	 */
	private void run() 
	{
		String s = "";
		Stops[] stops = null;
		boolean quit=false;
		//main interface loop, runs until user exits
		while (!quit) 
		{
			System.out.print("choose between the folowing options\n"+
		            "1:find the shortest distance between two bus stops\n"+
					"2: to search for a bus stop\n"+
		            "3: search for trips and an arrival time\n"+
					"4: to quit  ");

			s = userInput.next();
			if (isExit(s))
				System.exit(0);

			while (s.length() != 1)
			{
				System.out.print("Incorrect input, try again: ");
				s = userInput.next();
				if (isExit(s))
					System.exit(0);
			}

			switch (s.toLowerCase()) 
			{
			case "1":
				graph.floydWarshall();
				break;
			case "2":
				stops = searchByName("Search for bus stop by name: ", stops, s);
				if (stops.length == 0) 
				{
					System.out.println("No results found.");
				} 
				else 
				{
					for (Stops stop : stops) 
					{
						System.out.println(stop);
					}
				}
				break;
			case "3":
				int hours = readInt("Type in the arrival time hours: ");
				while (hours > 23 || hours < 0) 
				{
					hours = readInt("Hours must be 0-23: ");
				}
				int minutes = readInt("Type in the arrival time minutes: ");
				while (minutes > 59 || minutes < 0) 
				{
					minutes = readInt("Minutes must be 0-59: ");
				}
				int seconds = readInt("Type in the arrival time seconds: ");
				while (seconds > 59 || seconds < 0)
				{
					seconds = readInt("Seconds must be 0-59: ");
				}
				int time = (hours * 60 * 60) + (minutes * 60) + seconds;
				graph.findTripByTime(time);
				break;
			case "4":
				quit=true;
				break;
			default:
				break;

			}
		}
	}

	/**
	 * checks if the subarray of busstops has an index in it
	 * @param index : index to search for
	 * @param stops :subarray of busstops
	 * @return true if yes, false otherwise
	 */
	private boolean checkIndex(int index, Stops[] stops) 
	{
		for (Stops stop : stops) 
		{
			if (index == stop.getIndex()) 
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Calls the search method if input isnt exit
	 * @param str : prompt for the user
	 * @param stops : array of stops
	 * @param inputLine : user will write to, search by this
	 * @return array of bus stops which contain that prompt
	 */
	private Stops[] searchByName(String str, Stops[] stops, String inputLine) 
	{
		inputLine = readString(str);
		if (isExit(inputLine))
			System.exit(0);
		return graph.searchBusStop(inputLine);
	}

	/**
	 * Prompts user to enter a bus stop name, then gets the user to pick a bus stop 
	 * @param str : prompt for the user
	 * @param stops : array of bus stops
	 * @param inputLine : user will write to, search by this
	 * @return bus stop the user wanted
	 */
	public Stops getStopFromUser(String str, Stops[] stops, String inputLine) 
	{
		while(true)
		{
			//uses tst to see which stops contain the string
			stops = searchByName(str, stops, inputLine);
			for (Stops stop : stops)
			{
				System.out.println(stop);
			}
			if (stops.length == 0) 
			{
				System.out.println("No results were found for your input, please try again.");
			}
			else
				break;
		}

		//prompts the user to pick an option from the array of viable bus stops
		int index = readInt("Please choose a bus stop by typing in the number X from [X]: ");
		while (!checkIndex(index, stops)) 
		{
			index = readInt("Please choose a valid number: ");
		}
		return graph.stops[index];
	}

	/**
	 * Checks if input is exit
	 * @param str ; input to check
	 * @return true if yes, false otherwise
	 */
	public boolean isExit(String str) 
	{
		return str.equalsIgnoreCase("exit");
	}

	/**
	 * Prompts the user to enter an int, checks if input is an integer
	 * @param question : prompt for the user
	 * @return correctly entered int
	 */
	public int readInt(String q) 
	{
		System.out.print(q);
		
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

	/**
	 * prompts the user for a string, checks if input is non-empty and returns
	 * @param question :  prompt for the user
	 * @return entered string
	 */
	public String readString(String q)
	{
		System.out.print(q);
		String s = "";
		while (s.length() == 0) 
		{
			s = userInput.nextLine();
		}
		return s;
	}
}
