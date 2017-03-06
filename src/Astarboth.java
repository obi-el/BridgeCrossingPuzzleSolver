

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/**
 * 
 * @author Obinna Elobi
 *
 * A* search with average of both heuristics
 */
public class Astarboth implements AI {
	private ProblemModel rootNode;
	private Queue<ProblemModel> unvisitedPM;
	private ArrayList<ProblemModel> visited;
	
	
	public Astarboth(ProblemModel problemModel){
		rootNode = problemModel;
		
		unvisitedPM = new LinkedList<ProblemModel>();
		visited = new ArrayList<ProblemModel>();
		
		
		for(ProblemModel pm : rootNode.getSubNodes()){
		
			unvisitedPM.add(pm);
		}
		visited.add(problemModel);
		
	}
	
	
	
	// returns node with smallest fscore
	private ProblemModel findCheapestNode(){
		ProblemModel ret = unvisitedPM.peek();
		
		for(ProblemModel p : unvisitedPM){ 
			//f(s) = depth + cost to make this move
			int averageCost = (p.getCost() + p.getTotalTime()) / 2;
			int averageCost2 = (ret.getCost() + ret.getTotalTime()) / 2;
			if(averageCost + p.getDepth() <= averageCost2 + ret.getDepth()) ret = p;
		}
		unvisitedPM.remove(ret);
		return ret;
	}
	
	
	@Override
	public void solve() {
		// TODO Auto-generated method stub
		long startTime = System.nanoTime();

		StringBuilder path = new StringBuilder();
		String delimiter = "";
		while(!unvisitedPM.isEmpty()){
			ProblemModel current = findCheapestNode();
	
			for(ProblemModel pm : current.getSubNodes()){
		
				if(!visited.contains(pm) && !unvisitedPM.contains(pm)){
					
					unvisitedPM.add(pm);
				}
				
			
			}
			
			if(current.getSideStart().isEmpty()){
				System.out.print( "\n"+ "StartSide Runners: ");
				for(Integer r: current.getSideStart()){
					System.out.print( " " +r) ;
				}
				System.out.print( "\n" + "EndSide Runners: ");
				for(Integer r: current.getSideEnd()){
					System.out.print( " " + r);
				}

				printPathTaken(current);
				System.out.print( "\n" + "------------done--------------");
				long endTime = System.nanoTime();
				long duration = ((endTime - startTime)/1000000);
				System.out.print( "\n" + "-AS1 Time taken: " + duration + "ms");
				
				
				break;
			}
			
			visited.add(current);
		
		}

	}
	

	//print path taken to get to param current 
	private void printPathTaken(ProblemModel current) {

		System.out.print( "\n"+ "StartSide Runners: ");
		for(Integer r: current.getSideStart()){
			System.out.print( " " +r) ;
		}
		System.out.print( "\n" + "EndSide Runners: ");
		for(Integer r: current.getSideEnd()){
			System.out.print( " " + r);
		}
		System.out.print( "\n" + "Direction: " + ((!current.isTorch())? "Forwards":"Backwards"));
		System.out.print( "\n" + "Total Time: " + current.getTotalTime());
		System.out.print( "\n" + "Depth: " + current.getDepth());
		System.out.print( "\n" + "--------------------------");
		if(current.getPredecessor() != null)printPathTaken(current.getPredecessor());
	}

}
