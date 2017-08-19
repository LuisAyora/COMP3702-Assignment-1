package informedSearch;

import java.io.*;
import java.util.*;

/**
 * 
 * @author A-Team
 * Map Reader Class used to read the data from an input text file containing
 * road information for the implementation of a search algorithm.
 *
 */
public class MapReader {
	private List<Road> roads;
	/**
	 * Constructor:
	 * Instantiate a MapReader object that will extract all the information of
	 * roads in a text file.
	 * @return List<Road> roadList
	 * */
	public MapReader(String filename) {
		roads = loadRoads(filename);
	}
	
	/**
	 * Read a text file that has the Road information for the environment in
	 * which the informed search algorithm will be implemented.
	 * @param filename: the string name of the text file that will be read.
	 * @return roadList: the list of roads with all the required information.
	 */
	private List<Road> loadRoads(String filename) {
		List<Road> roadList = new ArrayList<Road>();
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while((line = bufferedReader.readLine()) != null) {
				String[] info = line.split(";");
				int index;
				for (index=0;index<info.length;index++) {
					info[index]=info[index].trim();
				}
				Road road = new Road(info[0],info[1],info[2],
						Double.parseDouble(info[3]),Integer.parseInt(info[4]));
				roadList.add(road);
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file: '" + filename + "'");
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		return roadList;
	}
	
	// Queries
	
	/**
	 * Get the list of Roads obtained from the text file 
	 * @return
	 */
	List<Road> getRoads() {
		return roads;
	}
	
	@Override
	/**
	 * String representation of the whole list of Roads.
	 */
	public String toString() {
		String repr = new String();
		for(Road r : roads) 
			repr += r.toString() + "\n";
		return repr;
	}
	
	// Dummy main method for testing.
	public static void main(String[] args) {
		MapReader mr = new MapReader("src/Files/test2.txt");
		List<Road> rl = mr.getRoads();
		System.out.println(mr.toString());
		System.out.println(rl.get(0).toString());
	}	
}