package informedSearch;

import java.util.PriorityQueue;
import java.util.Hashtable;
import java.util.Comparator;
import java.util.List;

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
	public Search(String infoFile, Node root, Node goal) {
		environment = new MapReader(infoFile);
		Comparator<Node> compare= new RoadComparator();
		queue = new PriorityQueue<Node>(1,compare);
		Hashtable<String,Node> expandedNodes=new Hashtable<String,Node>();
		
		
		/*Node root=new Node("name", "roadName", int number,double cost,double heuristic,Node parent)*/
		//this.root=new Node("1Road-1", "Road-1", 1,0,0,null);
		//this.goal=new Node("1Road-12", "Road-12", 1,0,0,null);
		this.root = root;
		this.goal = goal;
		queue.add(root);
		
		Node result;
		
		/**
		 * 		gives the root as output if no path is found
		 * 		otherwise it gives the goal Node as head of
		 * 		a linked list
		 * */
		while (true) {
			if(queue.isEmpty()) {
				result=root;
				break;
			}else {
				Node current=queue.poll();
				expandedNodes.put(current.getName(), current);
				if (goal.getName().equals(current.getName())) {
					result=current;
					break;
				}
				List<Node> children=environment.get_Moves(current, goal);
				for (Node child:children) {
					if (!expandedNodes.containsKey(child.getName()))
						queue.add(child);
				}
			}
			
		}
		
		String resToPrint=generateSolution(result,"");
		System.out.println("Cost: "+Double.toString(result.getCost()) + " - " 
				+ resToPrint);
	}
	
	/**
	 * Comparator for roads
	 */
	private class RoadComparator implements Comparator<Node>{
		@Override
		public int compare(Node a,Node b) {
			if(a.getTotalCost()<b.getTotalCost()) {
				return -1;
			}
			if(a.getTotalCost()>b.getTotalCost()) {
				return 1;
			}
			return 0;
		}
	}
	
	

	String generateSolution(Node end,String output) {

		/*For junction nodes*/
		if (end.isJunction()) {
			if(end.getParent() == null) { 
				return output;
			}
			else {
				return generateSolution(end.getParent(),end.getDefRoad()+"-"+end.getName()+"-"+output);
			}
		}
		/*For non-junction nodes*/
		else {
			/*Start*/
			if(end.getParent() == null) { 
				return output;
			}
			/*Goal*/
			else {
				return generateSolution(end.getParent(),end.getDefRoad()+output);
			}
		}

	}
}