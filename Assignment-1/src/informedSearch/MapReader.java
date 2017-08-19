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
	
	public List<Movement> get_Moves(Node curPos,Node goal) {
		ArrayList<Movement> moves=new ArrayList<Movement>();
		if (current.isJunction()){
			int i;
			double heuristic=0;
			for (i=0;i<roads.size();i++) {
				/*Junction is start junction in Road*/
				if (curPos.getName().equals(roads.get(i).getStart())) {
					/*Check if road is the goal road*/
					if (goal.getDefRoad().equals(roads.get(i).getName())) {
						String name=goal.getName();
						int number=goal.getHouseNumber();
						double lotWidth=2*roads.get(i).getLength()/roads.get(i).getLots();
						
						//Even
						if (number%2==0) {
							double dist=lotWidth*(number-1)/2;
						}
							//Odd
						else { 
							double dist=lotWidth*number/2;
						}
						
						double cost=curPos.getCost()+dist;
						/*Ask to modify constructor*/
						Node nodeToAdd=new Node(name,roads.get(i).getName(),number,cost,curPos);
						Movement movToAdd=new Movement(nodeToAdd,nodeToAdd.getCost()+nodeToAdd.getHeuristic());
						moves.add(movToAdd);
					}else {
						Node nodeToAdd=new Node(roads.get(i).getEnd(),null,curPos.getCost()+roads.get(i).getLength(),heuristic,curPos);
						Movement movToAdd=new Movement(nodeToAdd,nodeToAdd.getCost()+nodeToAdd.getHeuristic());
						moves.add(movToAdd);
					}
				}
				/*Junction is end junction in Road*/
				else if(curPos.getName().equals(roads.get(i).getEnd())) {
					if (goal.getDefRoad().equals(roads.get(i).getName())) {
						String name=goal.getName();
						int number=goal.getHouseNumber();
						double lotWidth=2*roads.get(i).getLength()/roads.get(i).getLots();
						
						//Even
						if (number%2==0) {
							double dist=lotWidth*(roads.get(i).getLots()-number+1)/2;
						}
							//Odd
						else { 
							double dist=lotWidth*(roads.get(i).getLots()-number+2)/2;
						}
						
						double cost=curPos.getCost()+dist;
						/*Ask to modify constructor*/
						Node nodeToAdd=new Node(name,roads.get(i).getName(),number,cost,curPos);
						Movement movToAdd=new Movement(nodeToAdd,nodeToAdd.getCost()+nodeToAdd.getHeuristic());
						moves.add(movToAdd);
					}else {
						Node nodeToAdd=new Node(roads.get(i).getStart(),null,curPos.getCost()+roads.get(i).getLength(),heuristic,curPos);
						Movement movToAdd=new Movement(nodeToAdd,nodeToAdd.getCost()+nodeToAdd.getHeuristic());
						moves.add(movToAdd);
					}
				}
			}
		}
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