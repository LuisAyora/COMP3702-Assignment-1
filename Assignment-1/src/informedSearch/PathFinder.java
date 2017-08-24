package informedSearch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;

public class PathFinder {
	private ArrayList<Node> queries;
	
	public PathFinder(String infoFile, String queryFile) {
		queries = setQuery(queryFile);
	}
	// Dummy main method for testing.
	public static void main(String[] args) {
		PathFinder pf = new PathFinder(args[0], args[1]);
		for(int index = 0; index < pf.getQueries().size(); index += 2) {
			Search app = new Search(args[0], pf.getQueries().get(index),
					pf.getQueries().get(index + 1));
		}
		//solution.close();
	}
	
	public ArrayList<Node> getQueries() {
		return queries;
	}
	
	private ArrayList<Node> setQuery(String queryFile) {
		// Initialise the return ArrayList<Node>
		ArrayList<Node> queryList = new ArrayList<Node>();
		try {
			// Initialise FileReader and BufferedReader objects for the query
			// file
			FileReader queryReader = new FileReader(queryFile);
			BufferedReader bf = new BufferedReader(queryReader);
			String line;
			/*
			 * Do the following:
			 * - Read each query line
			 * - Split query into initial and end nodes, separated by a ";"
			 * - Trim the whitespace from the node
			 * - Separate the nodes in each query using regex (\d)(\D)
			 * - Create root and goal Nodes objects for each query
			 */
			while((line = bf.readLine()) != null) {
				String[] info = line.split(";");
				for(int index = 0; index < info.length; index++) {
					info[index] = info[index].trim();
					String[] query = info[index].split("(?<=\\d)(?=\\D)");
					if(index == 0)
						queryList.add(new Node("root", query[1],
								Integer.parseInt(query[0]), 0, 0, null));
					else {
						queryList.add(new Node("goal", query[1],
								Integer.parseInt(query[0]), 0, 0, null));
						
					}
				}
			}
			bf.close();
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file: '" + queryFile + "'");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return queryList;
	}
}
