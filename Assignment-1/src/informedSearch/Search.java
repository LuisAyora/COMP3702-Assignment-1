package informedSearch;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.*;
import java.util.Hashtable;

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
		queue = new PriorityQueue<Node>();
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