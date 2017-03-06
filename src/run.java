

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class run {
	private static ArrayList<Integer> start = new ArrayList<Integer>();
	
	public static void main(String args[]){
		ProblemModel pm = new ProblemModel();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to the Bridge and Torch Problem");
		System.out.println("Please type in 6 Runner Speeds Seperated by a comma");
		
		String s = sc.nextLine();
		String inputs[] = s.split(",");
		for(String num: inputs){
				s = s.replaceAll("\\s", "");  //remove spaces
				
				try{
					int speed = Integer.parseInt(num);
					start.add(speed);
				}catch(NumberFormatException e1){
						System.out.println( num + ": Invalid number Spotted, Please Try again"); 
						System.exit(0);
				}
		
		}
		
		pm.setSideStart(start);
		System.out.println("Start: " ); 
		for (Integer r : pm.getSideStart())System.out.print(" " + r);
		System.out.println("\n"+"End: " ); 
		for (Integer r : pm.getSideEnd())System.out.print(" " + r);
		System.out.println("Total Time: " + pm.getTotalTime());
		System.out.println("-----------------------------");
		
		BFSAI bf = new BFSAI(pm);
		DFSAI df = new DFSAI(pm);
		Astar1 a1 = new Astar1(pm);
		Astar2 a2 = new Astar2(pm);
		Astarboth ab = new Astarboth(pm);
		
		
		System.out.println("Please type in Strategy: \n");
		System.out.println("BFS - Breadth First");
		System.out.println("DFS - depth First, ");
		System.out.println("ASONE - A-Star one: sum of cost of current crossers' time");
		System.out.println("ASTWO - A-Star two: crossing cost(slowest crosser time)");
		System.out.println("ASBOTH - finds average of 1 and 2 ");
		
		s = sc.nextLine();
		s = s.toLowerCase();
		if(s.equals("bfs")) bf.solve();
		else if(s.equals("dfs")) df.solve();
		else if(s.equals("asone")) a1.solve();
		else if(s.equals("astwo")) a2.solve();
		else if(s.equals("asboth")) ab.solve();
		else{
			System.out.println( s + ": Invalid input, Please Try again");
			System.exit(0);
		}
	}
	

}
