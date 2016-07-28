/**
 * The solver class
 * @author Supratim Baruah
 * @version 2014/4/10
 */
public class Solver {

	/**
	 *Takes the input
	 * @param args the input
	 */
	public static void main(String[] args) {

		boolean verbose;

		String[] input = new String[20];
		int j = 0;
		for (int i = 3; i < 19; i++)
		{
			input[j] = args[i];
			j++;
		}

		State initialState = new State(input);

		//make goalState
		String[] temp = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"11", "12", "13", "14", "15", "0"};


		State goalState = new State(temp);

		BFS solver = new BFS(initialState, goalState);

		if (args[2].equals("V"))
		{
			verbose = true;
		}
		else
		{
			verbose =  false;
		}

		if (args[1].equals("P"))
		{
			if (args[0].equals("V"))
			{
				System.out.println("Visited States:");
				solver.prioritySearch(true, verbose);
			}
			else
			{
				solver.prioritySearch(false, false);
				solver.printSolution(verbose);
			}
		}
		else
		{
			if (args[0].equals("V"))
			{
				System.out.println("Visited States:");
				solver.fifoSearch(true, verbose);
			}
			else
			{
				solver.fifoSearch(false, false);
				solver.printSolution(verbose);
			}
		}


	}
}