package informedSearch;

import java.io.*;
import java.util.*;
import java.math.*;

/**
 * 
 * @author A-Team
 * Map Reader Class used to read the data from an input text file containing
 * road information for the implementation of a search algorithm.
 *
 */
public class MapReader {
	private Hashtable<String,Road> roads;
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
	private Hashtable<String,Road> loadRoads(String filename) {
		Hashtable<String,Road> roadTable = new Hashtable<String,Road>();
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
				roadTable.put(road.getName(),road);
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file: '" + filename + "'");
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		return roadTable;
	}
	
	public List<Movement> get_Moves(Node curPos,Node goal) {
		ArrayList<Movement> moves=new ArrayList<Movement>();
		if (current.isJunction()){
			double heuristic=0;
			for (Enumeration<String> enumerator=roads.keys();enumerator.hasMoreElements();) {
				/*Junction is start junction in Road*/
				String i=new String(enumerator.nextElement());
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
					
					/*Checking if solution is in the road being processed*/
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
		/*If No junction node*/
		else {
			double heuristic=0;
			/*If goal in the road being processed*/
			if (goal.getDefRoad().equals(curPos.getDefRoad())) {
				String name=goal.getName();
				int numberG=goal.getHouseNumber();
				double lotWidth=2*roads.get(goal.getDefRoad()).getLength()/roads.get(goal.getDefRoad()).getLots();
				
				//Current House Number
				int numberCC;
				if (curPos.getHouseNumber()%2==0) {
					numberCC=curPos.getHouseNumber();
				}else {
					numberCC=curPos.getHouseNumber()+1;
				}
				
				int numberGG;
				//Goal House Number
				if (numberG%2==0) {
					numberGG=numberG;
				}else {
					numberGG=numberG+1;
				}
				
				double dist=lotWidth*Math.abs(numberGG-numberCC)/2;
				
				double cost=curPos.getCost()+dist;
				/*Ask to modify constructor*/
				Node nodeToAdd=new Node(name,goal.getDefRoad(),numberG,cost,curPos);
				Movement movToAdd=new Movement(nodeToAdd,nodeToAdd.getCost()+nodeToAdd.getHeuristic());
				moves.add(movToAdd);
			}
			/*If goal is not in the road being processed, expand to both end-of-road junctions*/
			else {
				double lotWidth=2*roads.get(curPos.getDefRoad()).getLength()/roads.get(curPos.getDefRoad()).getLots();
				String name1=roads.get(curPos.getDefRoad()).getStart();
				String name2=roads.get(curPos.getDefRoad()).getEnd();
				int number;

				if (curPos.getHouseNumber()%2==0) {
					number=curPos.getHouseNumber();
				}else {
					number=curPos.getHouseNumber()+1;
				}
				
				double dist1=lotWidth*(number+1)/2;
				double dist2=roads.get(curPos.getDefRoad()).getLength()-dist1;

				Node nodeToAdd1=new Node(name1,null,curPos.getCost()+dist1,heuristic,curPos);
				Movement movToAdd1=new Movement(nodeToAdd1,nodeToAdd1.getCost()+nodeToAdd1.getHeuristic());
				moves.add(movToAdd1);
				
				Node nodeToAdd2=new Node(name1,null,curPos.getCost()+dist1,heuristic,curPos);
				Movement movToAdd2=new Movement(nodeToAdd2,nodeToAdd2.getCost()+nodeToAdd2.getHeuristic());
				moves.add(movToAdd2);
				
				/**
				 * CHEEEEEECKKKK THE ARGUMENTS OF THE NODE CREATION FOR THE JUNCTIONS AND GOAL AND START
				 */
				
				
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