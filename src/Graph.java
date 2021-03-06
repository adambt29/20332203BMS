import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Collections;
/*
 * essentially takes in the data from the userinterface and based on the choice
 * made by the user the data is split up in different ways
 * the private Loader file at the end uses bufffered reader to take data from file(file depends on choice)
 * and returns in an arrayList we we can then further make sense of in Stop.java and Edge.java and then sort
 * user .sort or the Tst function.
 */
public class Graph
{
	Stops[] stops;
	TST<Stops> tst;
	HashMap<Integer, Integer> Ids;

	public Graph(String stopsTxt, String stopTimesTxt, String transferTxt) 
	{
		
	    System.out.print("wait for files to load");
		Ids = new HashMap<Integer, Integer>();		
		stops = readInStopData(stopsTxt);	
		Trips(stops, stopTimesTxt);
		Transfers(stops, transferTxt);	
		tst = new TST<Stops>();
		for (int i = 0; i < stops.length; i++) 
		{
			tst.put(stops[i].getName(), stops[i]);
		}

	}
	
//taken from assignment2-going to work on last
	//should use another shortestpath algorithm?
	
	public void floydWarshall() {
		/*for(int i=0;i<currentIntersection;i++) {
    		for(int j=0;j<currentIntersection;j++) {
    			for(int x=0;x<currentIntersection;x++) {
    				if(graph[j][x]>graph[j][i]+graph[i][x]) {
    					graph[j][x]=graph[j][i]+graph[i][x]; //going through all exhaustive options.
    				}
    			}
    		}
    	}*/
	}
	
	public void findTripByTime(int time) 
	{
		ArrayList<Integer> matchingTrips = new ArrayList<Integer>();
		for (int i = 0; i < stops.length; i++) 
		{
			ArrayList<Transfers> edges = stops[i].Edges();
			for (int j = 0; j < edges.size(); j++)
			{
				Transfers currentTransfer = edges.get(j);

				if (currentTransfer.getArrivalTimeAsSeconds() == time) 
				{
					matchingTrips.add(currentTransfer.getTripID());
				}
			}
		}

		Collections.sort(matchingTrips);

		if (matchingTrips.size() == 0) 
		{
			System.out.println("\nThere were no trips with your given arrival time.");
		} 
		else 
		{
			for (int i = 0; i < matchingTrips.size(); i++) 
			{
				System.out.println("Details for trip: " + matchingTrips.get(i));

				Transfers currentEdge = stops[Ids.get(matchingTrips.get(i))].findBusEdge(matchingTrips.get(i));

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
	private void Trips(Stops[] stops, String file)
	{
		CSVLoader c = new CSVLoader(file);
		ArrayList<String> list = c.readFile();

		for (int i = 2; i < list.size(); i++)
		{
			String[] from = list.get(i - 1).split(",");
			String[] to = list.get(i).split(",");

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

				if (from.length == 9) 
				{
					fromDist = Double.parseDouble(from[from.length - 1]);
				}

				int depHour = Integer.parseInt(departureTime.replaceAll(" ", "").split(":")[0]);
				int arrHour = Integer.parseInt(arrivalTime.replaceAll(" ", "").split(":")[0]);
				//check for invalid times
				if (!(depHour > 23 || arrHour > 23)) 
				{
					stops[fromIndex].addEdge(new Transfers(tripID, stops[fromIndex], stops[toIndex], departureTime,
							arrivalTime, seq, toDist - fromDist));
				}

				//if the tripID has not been set, set it to equal the first bus stop index
				if (!Ids.containsKey(tripID))
					Ids.put(tripID, fromIndex);
			}
		}
	}
	/**
	 * Return array of bus stops that include part of the input in their name using a tst
	 * @param busStopName : string to search
	 * @return array of all possible matches
	 */
	public Stops[] searchBusStop(String busStopName) 
	{
		String[] matches = tst.keysWithPrefix(busStopName.toUpperCase());

		Stops[] stops = new Stops[matches.length];
		for (int i = 0; i < stops.length; i++) 
		{
			stops[i] = tst.get(matches[i]);
		}
		Arrays.sort(stops);
		return stops;
	}

	/**
	 * Reads in transfers file and creates edges from data and adds it to
	 * appropriate bus stop
	 * 
	 * @param file:  name of file that contains transfers
	 * @param stops: array of all bus stops
	 */
	private void Transfers(Stops[] stops, String file) 
	{
		CSVLoader c = new CSVLoader(file);
		ArrayList<String> list = c.readFile();

		for (int i = 1; i < list.size(); i++)
		{
			String[] transfer = list.get(i).split(",");
			int fromIndex = findBusStop(Integer.parseInt(transfer[0]));
			int toIndex = findBusStop(Integer.parseInt(transfer[1]));
			int type = Integer.parseInt(transfer[2]);
			int cost = 0;
			if (type != 0) 
			{
				cost = Integer.parseInt(transfer[3]);
			}
			stops[fromIndex].addEdge(new Transfers(stops[fromIndex], stops[toIndex], cost));
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
		private Stops[] readInStopData(String file) 
	{
		CSVLoader c = new CSVLoader(file);
		ArrayList<String> list = c.readFile();
		Stops[] stops = new Stops[list.size() - 1];

		for (int i = 0; i < stops.length; i++) 
		{
			String[] data = list.get(i + 1).split(",");
			int parent = -1;
			if (data.length == 10) 
			{
				parent = Integer.parseInt(data[9]);
			}
			
			int code = -1;
			if (!data[1].equals(" ")) 
			{
				code = Integer.parseInt(data[1]);
			}
			
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

			stops[i] = new Stops(Integer.parseInt(data[0]), code, busStopName, data[3], Double.parseDouble(data[4]),
					Double.parseDouble(data[5]), data[6], Integer.parseInt(data[8]), parent);
		}
		
		Arrays.sort(stops);
		for (int i = 0; i < stops.length; i++) 
		{
			stops[i].setIndex(i);
		}
		return stops;
	}

}


//uses bufffered reader to split up the files and returns as an array list- called in the graph method
class CSVLoader 
{
	private BufferedReader reader;
	
	 CSVLoader(String filename) 
	{
		try
		{
			reader = new BufferedReader(new FileReader(filename));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public ArrayList<String> readFile() 
	{
		if(reader != null)
		{
			ArrayList<String> list = new ArrayList<String>();
			try
			{
				String line;
				while ((line = reader.readLine()) != null) 
				{
					list.add(line);
				}
				reader.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				System.exit(-1);
			}
			return list;
		}
		return new ArrayList<String>();
	}


}
