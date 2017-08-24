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
	
	public List<Node> get_Moves(Node curPos,Node goal) {
		ArrayList<Node> moves=new ArrayList<Node>();
		if (curPos.isJunction()){
			double heuristic=0;
			//int counter=0;
			for (Enumeration<String> enumerator=roads.keys();enumerator.hasMoreElements();) {
				//counter++;
				String i=new String(enumerator.nextElement());
				//System.out.println("Pevious\t"+Integer.toString(counter)+"i is:\t"+i);
				/*Junction is start junction in Road*/
				if ((curPos.getName().equals(roads.get(i).getStart()))&&(curPos.getDefRoad().equals(roads.get(i).getName())==false)) {
					/*Check if road is the goal road*/
					//System.out.println(Integer.toString(counter)+"i is:\t"+i);
					if (goal.getDefRoad().equals(roads.get(i).getName())) {
						String name=goal.getName();
						int number=goal.getHouseNumber();
						double lotWidth=2*roads.get(i).getLength()/roads.get(i).getLots();
						
						//Even
						double dist;
						if (number%2==0) {
							dist=lotWidth*(number-1)/2;
						}
						//Odd
						else { 
							dist=lotWidth*number/2;
						}
						
						double cost=curPos.getCost()+dist;
						/*Ask to modify constructor*/
						/*Create Goal Node*/
						Node nodeToAdd=new Node(name,roads.get(i).getName(),number,cost,heuristic,curPos);						
						moves.add(nodeToAdd);
					}else {
						/*Create junction node*/
						Node nodeToAdd;
						if(curPos.isJunction()) {
							nodeToAdd=new Node(roads.get(i).getEnd(),roads.get(i).getName(),curPos.getCost()+roads.get(i).getLength(),heuristic,curPos);
						}
						else {
							nodeToAdd=new Node(roads.get(i).getEnd(),curPos.getDefRoad(),curPos.getCost()+roads.get(i).getLength(),heuristic,curPos);
						}
						moves.add(nodeToAdd);
					}
				}
				/*Junction is end junction in Road*/
				else if(curPos.getName().equals(roads.get(i).getEnd())&&(curPos.getDefRoad().equals(roads.get(i).getName())==false)) {
					
					/*Checking if solution is in the road being processed*/
					if (goal.getDefRoad().equals(roads.get(i).getName())) {
						String name=goal.getName();
						int number=goal.getHouseNumber();
						double lotWidth=2*roads.get(i).getLength()/roads.get(i).getLots();
						
						
						double dist;
						//Even
						if (number%2==0) {
							dist=lotWidth*(roads.get(i).getLots()-number+1)/2;
						}
							//Odd
						else { 
							dist=lotWidth*(roads.get(i).getLots()-number+2)/2;
						}
						
						double cost=curPos.getCost()+dist;
						/*Add Goal Node*/
						Node nodeToAdd=new Node(name,roads.get(i).getName(),number,cost,heuristic,curPos);
						moves.add(nodeToAdd);
					}else if(curPos.getDefRoad().equals(roads.get(i).getName())==false){
						/*Add junction node*/
						Node nodeToAdd;
						if (curPos.isJunction()) {
							nodeToAdd=new Node(roads.get(i).getStart(),roads.get(i).getName(),curPos.getCost()+roads.get(i).getLength(),heuristic,curPos);
						}else {
							nodeToAdd=new Node(roads.get(i).getStart(),curPos.getDefRoad(),curPos.getCost()+roads.get(i).getLength(),heuristic,curPos);		
						}
						moves.add(nodeToAdd);
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
				
				/*goal Node to be added*/
				Node nodeToAdd=new Node(name,goal.getDefRoad(),numberG,cost,heuristic,curPos);
				moves.add(nodeToAdd);
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
				
				double dist1=lotWidth*(number-1)/2;
				double dist2=roads.get(curPos.getDefRoad()).getLength()-dist1;

				/*Junction nodes to be added*/
				Node nodeToAdd1=new Node(name1,curPos.getDefRoad(),curPos.getCost()+dist1,heuristic,curPos);
				moves.add(nodeToAdd1);
				
				Node nodeToAdd2=new Node(name2,curPos.getDefRoad(),curPos.getCost()+dist2,heuristic,curPos);
				moves.add(nodeToAdd2);
				
				/**
				 * CHEEEEEECKKKK THE ARGUMENTS OF THE NODE CREATION FOR THE JUNCTIONS AND GOAL AND START
				 */
				
				
			}
		}
		return moves;
	}
	
	// Queries
	
	/**
	 * Get the list of Roads obtained from the text file 
	 * @return
	 */
	Hashtable<String,Road> getRoads() {
		return roads;
	}
	
	@Override
	/**
	 * String representation of the whole list of Roads.
	 */
	public String toString() {
		String repr = new String();
		for(Enumeration<String> enumerator=roads.keys();enumerator.hasMoreElements();) 
			repr += roads.get(enumerator.nextElement()).toString() + "\n";
		return repr;
	}
	/*
	public static void main(String[] args) {		
		/*Test nodes for Simple Test data*/
		/*
		MapReader mr = new MapReader("src/Files/test-simple.txt");
		Hashtable<String,Road> rl = mr.getRoads();
		System.out.println(mr.toString()); 
		 
		Node root=new Node("2Warren", "Warren", 2,0,0,null);
		Node junction1= new Node("J1","Warren", 3, 0,
	    		root);
		Node goal=new Node("Carmody1", "Carmody", 1,0,0,null);
		
		
		MapReader mr = new MapReader("src/Files/test2.txt");
		Hashtable<String,Road> rl = mr.getRoads();
		System.out.println(mr.toString()); 
		Node root=new Node("2Road-21", "Road-21", 2,0,0,null);
		Node junction1= new Node("J17","Road-21", 1000, 0,
	    		root);
		Node goal=new Node("3Road-21", "Road-21", 3,0,0,null);
		
		List<Node> moves=mr.get_Moves(root,goal);
		
		System.out.println("The Next Moves are: \n \n");
		
		for (Node theMove:moves) {
			System.out.println(String.format("%f",theMove.getTotalCost())+"\t"+theMove.getName()+"\n");
		}
	}*/
}