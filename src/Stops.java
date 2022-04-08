package finalProject;
import java.util.ArrayList;
import java.util.Collections;

public class Stops implements Comparable<Stops> 
{
    private int ID;
    private int code;
    private String name;
    private String desc;
    private double lat;
    private double lon;
    private String zoneID;
    private int locType;
    private int parent;

    
    private int index;

    public Stops(int ID, int code, String name, String desc, double lat, double lon, String zoneID, int locType,
            int parent) 
    {
        this.ID = ID;
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.lat = lat;
        this.lon = lon;
        this.zoneID = zoneID;
        this.locType = locType;
        this.parent = parent;

        
        index = -1;
    }

    /**
     * Returns data of bus stop as a string
     * 
     * @return string of bus stop data
     */
    
    public String convertToString() 
    {
    	//Don't print out long/lat/ any pointless data 
        return "[" + index + "] " + ID + ", " + code + ", " + name + ", "
                + desc;
    }
    
    /**
     * Checks if there exists a transfer between current busstop and input busstop
     * @param dest : busstop to check if there is a transfer to
     * @return true if yes, false otherwise
     */ 
    public boolean existsTransfer(Stops dest) 
    {
        for (Edge edge : edges) 
        {
            
                return true;
            
        }
        return false;
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
        
            return 0;
      
    }

    /**
     * Find a busedge with a given tripID
     * @param id : id we want to find
     * @return busedge with that trip id, null if it doesnt exist
     */
    public Edge findBusEdge(int id) 
    {
    
        return null;
    }

    /**
     * @return bus stop id
     */
    public int getID() 
    {
        return ID;
    }

    /**
     * @return bus stop code
     */
    public int getCode()
    {
        return code;
    }

    /**
     * @return bus stop name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return bus stop desc
     */
    public String getDesc()
    {
        return desc;
    }

    /**
     * @return bus stop lat
     */
    public Double getLat() 
    {
        return lat;
    }

    /**
     * @return bus stop lon
     */
    public Double getLon() 
    {
        return lon;
    }

    /**
     * @return bus stop zoneID
     */
    public String getZoneID() 
    {
        return zoneID;
    }

    /**
     * @return bus stop loctype
     */
    public int getLocType() 
    {
        return locType;
    }

 
 
 

    /**
     * @return bus stop index in bus stop array
     */
    public int getIndex()
    {
        return index;
    }

    /**
     * @param index: new bus stop index
     */
    public void setIndex(int index) 
    {
        this.index = index;
    }

}


