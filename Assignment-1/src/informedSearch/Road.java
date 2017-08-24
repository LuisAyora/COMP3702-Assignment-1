package informedSearch;

/**
 * 
 * @author The A-Team 
 * An immutable class that stores the information about a road in a city setting.
 *
 */

public class Road {
	private String name;
    private String start;
    private String end;
    private double roadLength;
    private int nLots;
    
    /**
     * Constructor:
     * Generate a new Road object with associated junctions, length and number
     * of lots.
     * @param String name: the road's name
     *        Junction start: the starting Junction.
     *        Junction end: the end Junction.
     *        int roadLength: the length of the road.
     *        int nLots: the number of houses on that road.

     * @return A 5-tuple Road.
     */
    public Road(String name, String start, String end, double roadLength, int nLots) {
    	this.name = name;
    	this.start = start;
    	this.end = end;
    	this.roadLength = roadLength;
    	this.nLots = nLots;
    }	
    
    // Queries:
    
    /**
     * Return the street's name.
     * @return String name.
     */
    public String getName() {
    	return name;
    }
    
    /**
     * Return the street's starting Junction.
     * @return Junction start.
     */
    public String getStart() {
    	return start;
    }
    
    /**
     * Return the street's ending Junction.
     * @return Junction start.
     */

    public String getEnd() {
    	return end;
    }
    
    /**
     * Return the street's length.
     * @return double roadLength.
     */
    public double getLength() {
    	return roadLength;
    }
    
    /**
     * Return the street's number of lots.
     * @return int nLots.
     */

    public int getLots() {
    	return nLots;
    }
    
    @Override
    /**
     * String representation of the Road.
     */
    public String toString() {
    	return "[" + name + ", " + start + ", " + end + ", " + 
            Double.toString(roadLength) + ", " + Integer.toString(nLots) + "]";
    }
}
