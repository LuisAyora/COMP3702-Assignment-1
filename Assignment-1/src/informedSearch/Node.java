package informedSearch;

/**
 * 
 * @author The A-Team
 * An immutable class representing the nodes in a search algorithm graph.
 *
 */

public class Node {
	private int houseLeft;
	private int houseRight;
	private Road road;
	
	/**
	 * Constructor:
	 * Instantiate a new Node with a Road and the houses associated with it.
	 * @param Road road: the road in which the node is in.
	 * @param int house: the house number where the node is in.
	 * @require that the house number is smaller than the possible amount of
	 *          houses in the street (road.getLots()).
	 * @ensure that the node will be created and will operate appropriately in
	 *         the search algorithm.
	 */
	public Node(Road road, int house) {
		this.road = road;
		if(house % 2 == 0) {
		    houseRight = house;
	        houseLeft = house - 1;
	    }
	    else {
		    houseLeft = house;
	        houseRight = house + 1;
	    }
	}
	
	/**
	 * Return the Road of the Node.
	 * @return Road road.
	 */
	public Road getRoad() {
		return road;
	}
	
	/**
	 * Return the number of the house to the right of the Node.
	 * @return int houseRight.
	 */
	public int getHouseRight() {
		return houseRight;
	}
	
	/**
	 * Return the number of the house to the left of the Node.
	 * @return int houseLeft.
	 */
	public int getHouseLeft() {
		return houseLeft;
	}
	
	/**
	 * Determine if the node is at the start junction of the road.
	 * @return True if it's at the beginning, false otherwise.
	 */
	boolean isBeginning() {
		if(houseLeft == 1)
			return true;
		return false;
	}
	
	/**
	 * Determine if the node is at the end Junction of the road.
	 * @return True if it's at the end, false otherwise.
	 */
	boolean isEnd() {
		if(houseRight == road.getLots())
			return true;
		return false;
	}
	
	@Override
	/**
	 * String representation of the Node.
	 */
	public String toString() {
		return road.getName() + " between houses " + 
	        Integer.toString(houseLeft) + " and " + 
				Integer.toString(houseRight);
	}
}
