

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Obinna Elobi
 * 
 * heuristic being used: Number of runners yet to cross 
 */
public class Astar1 implements AI {

	private Queue<ProblemModel> unvisitedPM;
	private ArrayList<ProblemModel> visited;


	public Astar1(ProblemModel problemModel){

		unvisitedPM = new LinkedList<ProblemModel>();
		visited = new ArrayList<ProblemModel>();


		for(ProblemModel pm : problemModel.getSubNodes()){

			unvisitedPM.add(pm);
		}
		visited.add(problemModel);

	}


	// returns node with smallest fscore
	private ProblemModel findCheapestNode(){
		ProblemModel ret = unvisitedPM.peek();

		for(ProblemModel p : unvisitedPM){ 
			//f(s) = depth + sum of runner costs
			if(p.getTotalTime()+ p.getDepth() <= ret.getTotalTime() + ret.getDepth()) ret = p;
		}
		unvisitedPM.remove(ret);
		return ret;
	}



	@Override
	public void solve() {
		long startTime = System.nanoTime();

		while(!unvisitedPM.isEmpty()){
			ProblemModel current = findCheapestNode();
			for(ProblemModel pm : current.getSubNodes()){		
				if(!visited.contains(pm) && !unvisitedPM.contains(pm)){					
					unvisitedPM.add(pm);
				}			
			}

			
			//end Condition: If no more runners to transport.
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

	/**
	 * @param current node/problemModel currently on
	 * 
	 * 	prints nodes visited to reach current
	 */
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
