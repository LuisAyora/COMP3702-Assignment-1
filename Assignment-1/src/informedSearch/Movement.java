package informedSearch;

/**
 * 
 * @author A-Team
 * Defines a Movement with a final destination node and 
 * the cost from the current node which will be know
 *
 */
public class Movement {
	Node destination;
	double cost;
	
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
}
