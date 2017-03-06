


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author Obinna
 *
 * Breadth First search algorithm to find 
 */
public class BFSAI implements AI{
	
	
	private Queue<ProblemModel> unvisitedPM;
	private ArrayList<ProblemModel> visited;
	
	
	public BFSAI(ProblemModel problemModel){
		
		//sort runners on start so fastest runner is picked first
//		Collections.sort(problemModel.getSideEnd());
//		Collections.sort(problemModel.getSideStart());
		unvisitedPM = new LinkedList<ProblemModel>();
		visited = new ArrayList<ProblemModel>();
		
		
		for(ProblemModel pm : problemModel.getSubNodes()){
//			Collections.sort(pm.getSideEnd());
//			Collections.sort(pm.getSideStart());
			unvisitedPM.add(pm);
	
		}
		visited.add(problemModel);
		
	}
	
	
	
	@Override
	public void solve() {
		long startTime = System.nanoTime();

		while(!unvisitedPM.isEmpty()){
			ProblemModel current = unvisitedPM.remove();
	
			for(ProblemModel pm : current.getSubNodes()){
				if(!visited.contains(pm) && !unvisitedPM.contains(pm)){
//					Collections.sort(pm.getSideEnd());
//					Collections.sort(pm.getSideStart());
					unvisitedPM.add(pm);
				}
				
			
			}
			
			//end condition
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
				System.out.print( "\n" + "-BFS Time taken: " + duration + "ms");
				
				
				break;
			}
			
			visited.add(current);
			
		}
		
		
	}




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
		//System.out.print( "\n" + "Current Node HashCOde: " + current.hashCode());
		System.out.print( "\n" + "--------------------------");
		if(current.getPredecessor() != null)printPathTaken(current.getPredecessor());
	}
		
	}



