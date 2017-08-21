package informedSearch;

import java.util.Enumeration;

/**
 * 
 * @author A-Team
 * Defines a Movement with a final destination node and 
 * the cost from the current node which will be know
 *
 */
public class Movement {
	private Node destination;
	private double cost;
	
	/**
	 * 
	 * Constructor
	 * @param node	destination node
	 * @param dist	distance from current
	 */
	public Movement(Node node,double dist) {
		destination=node;
		cost=dist;
	}
	
	
	/**
	 * Queries
	 * @return
	 */
	
	public Node getDestination() {
		return destination;
	}
	public double getCost() {
		return cost;
	}	
}
