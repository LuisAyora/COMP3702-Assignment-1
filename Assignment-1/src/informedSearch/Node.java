package informedSearch;

import java.util.ArrayList;

/**
 * 
 * @author The A-Team
 * An mutable class representing the nodes in a search algorithm graph.
 * Each node has a name, a defining road, associated length and heuristic
 * costs. Root and goal nodes have no adjacent Roads, costs and the initial
 * root no has no parent Node.
 *
 */

public class Node {
	// Define the attributes that a Node requires:
    private String name;
    private String defRoad; // Road defined to differentiate nodes
    private int houseNumber;
    private ArrayList<Road> adjRoads;
    private Node parent;
    private double totalCost;
    private double cost;
    private double heuristic;
    
    /**
     * Constructor:
     * Instantiate the root Node object for the search
     * algorithm by giving the name of the Node and the Road, 
     * and the house number. 
     * For Node start set parent as null
     * @param road
     */
    /*Goal or Start*/
    public Node(String name, String road, int number,double costVal,Node parent) {
    	this.name = name;
    	defRoad = road;
    	houseNumber = number;
    	adjRoads = null;
    	this.parent = parent;
    	cost = costVal;
    	heuristic = 0;
    	totalCost = cost + heuristic;
    }
    
    //Overload
    /*Junction Node*/
    public Node(String name,String road, double cost, double heuristic,
    		Node parent) {
    	this.name = name;
    	defRoad = null;
    	houseNumber = 0;
    	adjRoads = new ArrayList<Road>();
    	this.cost = cost;
    	this.heuristic = heuristic;
    	totalCost = this.cost + this.heuristic;
    	
    }
    
    // Queries:
    
    /**
     * Return the name of the node.
     * @return String name: the name of the node.
     */
    public String getName() {
    	return name;
    }
    
    /**
     * Return the defining road name of the Node. 
     * @return String defRoad: the name of the road creating the Node.
     */
    public String getDefRoad() {
    	return defRoad;
    }
    
    /**
     * Return the number of the house of the node.
     * @return int houseNumber: the number of the house.
     */
    public int getHouseNumber() {
    	return houseNumber;
    }
    
    /**
     * Return the list of adjacent Roads to the Junction.
     * @return ArrayList adjRoad: the list of adjacent Roads.
     */
    public ArrayList<Road> getAdjRoads() {
    	return adjRoads;
    }
    
    /**
     * Return the parent node.
     * @return Node parent: the parent Node.
     */
    public Node getParent() {
    	return parent;
    }
    
    /**
     * Return the total cost associated with the Node.
     * @return double totalCost: the Node's cost.
     */
    public double getTotalCost() {
    	return totalCost;
    }
    
    /**
     * Return the cost associated with the Road's length.
     * @return double cost: the Road's cost.
     */
    public double getCost() {
    	return cost;
    }
    
    /**
     * Return the estimated heuristic cost of the path between the current
     * Node and the goal.
     * @return double heuristic: the expected heuristic cost.
     */
    public double getHeuristic() {
    	return heuristic;
    }
    
    /**
     * Determine if a Node is in a junction. Otherwise the Node is either
     * the root or the goal.
     * @return
     */
    public boolean isJunction() {
    	if(houseNumber == 0)
    		return true;
    	return false;
    }
    
    // Commands:
    
    public void addAdjRoad(Road road) {
    	adjRoads.add(road);
    }
}