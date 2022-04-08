package finalProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.HashMap;
import java.util.Collections;

public class Graph
{
	Stops[] stops;
	HashMap<Integer, Integer> Ids;

	//reading in files
	public Graph(String stopsTxt, String tripsTxt, String transferTxt) 
	{
	
		Ids = new HashMap<Integer, Integer>();		
		stops = readInStopData(stopsTxt);	
		Trips(stops, tripsTxt);
		Transfers(stops, transferTxt);	
		tst = new TST<Stops>();
		for (int i = 0; i < stops.length; i++) 
		{
			tst.put(stops[i].getName(), stops[i]);
		}

	}

	/**
	 * Finds all trips with arrival time = input time
	 * 
	 * @param time: time we want to find
	 */
	public void findTripByTime(int time) 
	{
		ArrayList<Integer> matchingTrips = new ArrayList<Integer>();
		//go through each stop, then go through each edge in that stop
		for (int i = 0; i < stops.length; i++) 
		{
			ArrayList<BusEdge> edges = stops[i].getEdges();
			for (int j = 0; j < edges.size(); j++)
			{
				BusEdge currentEdge = edges.get(j);

				//if edges arrival time == input, add it to an arraylist
				if (currentEdge.getArrivalTimeAsSeconds() == time) 
				{
					matchingTrips.add(currentEdge.getTripID());
				}
			}
		}

		Collections.sort(matchingTrips);

		//print out any details concerning the trips found
		if (matchingTrips.size() == 0) 
		{
			System.out.println("\nThere were no trips with your given arrival time.");
		} 
		else 
		{
			for (int i = 0; i < matchingTrips.size(); i++) 
			{
				System.out.println("Details for trip: " + matchingTrips.get(i));

				//get the first edge that corresponds to that trip id
				BusEdge currentEdge = stops[tripIDs.get(matchingTrips.get(i))].findBusEdge(matchingTrips.get(i));

				//go down the trip until you reach the edge which had the correct arrival time, printing details as you go
				while (currentEdge != null && currentEdge.getArrivalTimeAsSeconds() <= time) 
				{
					System.out.println(currentEdge);
					currentEdge = currentEdge.getTo().findBusEdge(matchingTrips.get(i));
				}
				System.out.println("\n");
			}
		}
	}

	/**
	 * Reads in stop_times file and creates edges from data, adding them to the
	 * approppriate bus stop
	 * 
	 * @param file:  name of file that contains trip information
	 * @param stops: Array of all bus stops
	 **/
	private void Trips(BusStop[] stops, String file)
	{
		//use csvs to create a 2d array from the file
		CSVLoader c = new CSVLoader(file);
		ArrayList<String> list = c.readFile();

		//start from i =2 bc the first two lines of the trip file are descriptions
		//create edges from the information
		for (int i = 2; i < list.size(); i++)
		{
			String[] from = list.get(i - 1).split(",");
			String[] to = list.get(i).split(",");

			//wont add a new edge if the trip changes
			if (from[0].equals(to[0])) 
			{
				int fromIndex = findBusStop(Integer.parseInt(from[3]));
				int toIndex = findBusStop(Integer.parseInt(to[3]));
				int tripID = Integer.parseInt(from[0]);
				String departureTime = from[2];
				String arrivalTime = to[1];
				int seq = Integer.parseInt(from[4]);
				double fromDist = 0;
				double toDist = Double.parseDouble(to[to.length - 1]);

				//some edges dont have distances, so must double check this
				if (from.length == 9) 
				{
					fromDist = Double.parseDouble(from[from.length - 1]);
				}

				int depHour = Integer.parseInt(departureTime.replaceAll(" ", "").split(":")[0]);
				int arrHour = Integer.parseInt(arrivalTime.replaceAll(" ", "").split(":")[0]);
				//check for invalid times
				if (!(depHour > 23 || arrHour > 23)) 
				{
					stops[fromIndex].addEdge(new BusEdge(tripID, stops[fromIndex], stops[toIndex], departureTime,
							arrivalTime, seq, toDist - fromDist));
				}

				//if the tripID has not been set, set it to equal the first bus stop index
				if (!tripIDs.containsKey(tripID))
					tripIDs.put(tripID, fromIndex);
			}
		}
	}
	

	/**
	 * Reads in transfers file and creates edges from data and adds it to
	 * appropriate bus stop
	 * 
	 * @param file:  name of file that contains transfers
	 * @param stops: array of all bus stops
	 */
	private void Transfers(BusStop[] stops, String file) 
	{
		//Use a csv loader to create an arraylist of strings of data from the file
		CSVLoader c = new CSVLoader(file);
		ArrayList<String> list = c.readFile();

		//create transfer edges from the file
		for (int i = 1; i < list.size(); i++)
		{
			//split the strings to create a array of data
			String[] transfer = list.get(i).split(",");
			int fromIndex = findBusStop(Integer.parseInt(transfer[0]));
			int toIndex = findBusStop(Integer.parseInt(transfer[1]));
			int type = Integer.parseInt(transfer[2]);
			int cost = 0;
			//If transfer type is 1 (non-immediate) read in the associated cost
			if (type != 0) 
			{
				cost = Integer.parseInt(transfer[3]);
			}
			stops[fromIndex].addEdge(new BusEdge(stops[fromIndex], stops[toIndex], cost));
		}
	}

	/**
	 * Takes in id of bus stop and returns the index in the array of that stop
	 * 
	 * @param id:    id of bus stop we want to find
	 * @param stops: array of all bus stops
	 * @return index of bus stop with the given array
	 **/
	public int findBusStop(int id) 
	{
		//binary search to find bus stop
		int low = 0;
		int high = stops.length - 1;

		while (low <= high) 
		{
			int middle = low + ((high - low) / 2);
			int midID = stops[middle].getID();

			if (midID == id)
				return middle;
			else if (midID > id)
				high = middle - 1;
			else
				low = middle + 1;
		}
		return -1;
	}

	/**
	 * Reads in stops file and creates an array of bus stop classes from the data
	 * 
	 * @param fileName: name of file that contains the stops
	 * @return array of all bus stops
	 */
	private BusStop[] readInStopData(String file) 
	{
		//use a csv loader to create an arraylist of string from the file
		CSVLoader c = new CSVLoader(file);
		ArrayList<String> list = c.readFile();
		BusStop[] stops = new BusStop[list.size() - 1];

		//create a busstop from the given data
		for (int i = 0; i < stops.length; i++) 
		{
			String[] data = list.get(i + 1).split(",");
			int parent = -1;
			//some busstops dont have parents, need to check length to see if they do
			if (data.length == 10) 
			{
				parent = Integer.parseInt(data[9]);
			}
			
			int code = -1;
			//some busstops have empty codes, need to check if given spot is not empty
			if (!data[1].equals(" ")) 
			{
				code = Integer.parseInt(data[1]);
			}
			
			//edit name to put extra tokens at the end of the string
			String token = data[2].split(" ")[0];
			String busStopName = data[2];
			if (token.length() == 2 && token.charAt(1) == 'B')
			{
				busStopName = data[2].substring(3) + " " + token;
			}
			token = data[2].split(" ")[1];
			if (token.length() == 1
					&& (token.equals("N") || token.equals("E") || token.equals("S") || token.equals("W"))) 
			{
				busStopName = busStopName.substring(2) + " " + token;
			}

			stops[i] = new BusStop(Integer.parseInt(data[0]), code, busStopName, data[3], Double.parseDouble(data[4]),
					Double.parseDouble(data[5]), data[6], Integer.parseInt(data[8]), parent);
		}
		
		//sort the stops by trip id
		Arrays.sort(stops);
		//store the index of each busstop in the array in that busstop
		for (int i = 0; i < stops.length; i++) 
		{
			stops[i].setIndex(i);
		}
		return stops;
	}

	/**
	 * Sort the edges 
	 */
	private void sortEdges()
	{
		for (BusStop stop : stops)
		{
			stop.sortEdges();
		}
	}

}
