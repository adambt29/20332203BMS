//takes in data from stops.txt file
//returns specific data that the user has implied they are looking for.
import java.util.ArrayList;
import java.util.Collections;
// very similar idea to edges.java from textbook just taking in all the headings from the stop.txt file
public class Stops implements Comparable<Stops> 
{
	//variable names can be seen in stops.txt fuile which will help to full understand what they all do.
    private int id;
    private int code;
    private String name;
    private String desc;
    private double lat;
    private double lon;
    private String zoneID;
    private int locType;
    private int parent;

    private ArrayList<Transfers> edges;
    private int i;

    public Stops(int id, int code, String name, String desc, double lat, double lon, String zoneID, int locType,
            int parent) 
    {
        this.id = id;
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.lat = lat;
        this.lon = lon;
        this.zoneID = zoneID;
        this.locType = locType;
        this.parent = parent;

        edges = new ArrayList<Transfers>();
        i = -1;
    }

    /**
     * Returns data of bus stop as a string
     * 
     * @return string of bus stop data
     */
    @Override
    public String toString() 
    {
    	 
        return "[" + i + "] " + id + ", " + code + ", " + name + ", "
                + desc;
    }
    
 

    /**
     * Compares bus stop to another busstop based on ids
     * 
     * @param stop: bus stop to be compared to
     * @return 1 if >, -1 if <, 0 if equal
     */
    @Override
    public int compareTo(Stops stop) 
    {
        if (this.id == ((Stops) stop).id) 
        {
            return 0;
        } 
        else if (this.id < ((Stops) stop).id) 
        {
            return -1;
        }
        return 1;
    }

    /**
     * Find a busedge with a given tripID
     * @param id : id we want to find
     * @return busedge with that trip id, null if it doesnt exist
     */
     public Transfers findBusEdge(int id) 
    {
    	//uses binary search
        int low = 0;
        int high = edges.size() - 1;

        while (low <= high) 
        {
            int middle = low + ((high - low) / 2);
            int midID = edges.get(middle).getTripID();

            if (midID == id)
                return edges.get(middle);
            else if (midID > id)
                high = middle - 1;
            else
                low = middle + 1;
        }
        return null;
    }

   
    public int getID() 
    {
        return id;
    }

   
    public int getCode()
    {
        return code;
    }

   
    public String getName()
    {
        return name;
    }

    
    public String getDesc()
    {
        return desc;
    }

    
    public Double getLat() 
    {
        return lat;
    }

   
    public Double getLon() 
    {
        return lon;
    }

   
    public String getZoneID() 
    {
        return zoneID;
    }

   
    public int getLocType() 
    {
        return locType;
    }

    
    public int getParent()
    {
        return parent;
    }

   
    public ArrayList<Transfers> Edges() 
    {
        return edges;
    }

    
    public boolean addEdge(Transfers edge) 
    {
        return edges.add(edge);
    }

   //sort using collections- helped by geeksforgeeks website.
    public void sortEdges()
    {
        Collections.sort(edges);
    }

   
    public int getIndex()
    {
        return i;
    }

    
    public void setIndex(int index) 
    {
        this.i = index;
    }

}
