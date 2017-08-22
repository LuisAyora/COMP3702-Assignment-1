package informedSearch;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.*;
import java.util.Hashtable;
import java.util.Comparator;

public class Search {
	private Node root;
	private Node goal;
	private MapReader environment;
	private PriorityQueue<Node> queue;
	
	/**
	 * Constructor:
	 * Create a Search object that will take the name of two files and
	 * generate the working environment, initial and end Nodes from this
	 * information.
	 * @param String infoFile: the file to generate the environment.
	 * @param String queryFile: 
	 */
	public Search(String infoFile, String queryFile) {
		environment = new MapReader(infoFile);
		Comparator<Node> compare= new RoadComparator();
		queue = new PriorityQueue<Node>(0,compare);
		Hashtable<String,Node> expandedNodes=new Hashtable();
		
		
		/*Node root=new Node("name", "roadName", int number,double cost,double heuristic,Node parent)*/
		Node root=new Node("nameRoot", "roadName", 5,0,100,null);
		Node goal=new Node("2nameGoal", "roadName2", 2,0,0,null);
		queue.add(root);
		
		
		
		
		
	}
	
	/**
	 * Comparator for roads
	 */
	private class RoadComparator implements Comparator<Node>{
		@Override
		public int compare(Node a,Node b) {
			if(a.getTotalCost()<b.getTotalCost()) {
				return 1;
			}
			if(a.getTotalCost()>b.getTotalCost()) {
				return -1;
			}
			return 0;
		}
	}
	
	
/*
ArrayList generateSolution(Node end) {
    ArrayList<Node> sol = new ArrayList<Node>();
	if(end.getParent() == null) { 
    	sol.add(end);
    	return sol;
	}
	else {
		sol.add(end);
		generateSolution(end.getParent());
	}
}
*/
}