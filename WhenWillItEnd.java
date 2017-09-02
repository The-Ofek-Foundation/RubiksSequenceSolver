import java.util.ArrayList;
import java.util.Scanner;

public class WhenWillItEnd {

	private ArrayList<String> seq;

	public WhenWillItEnd() {
		seq = new ArrayList<String>();
	}

	public static void main(String... pumpkins) {
		WhenWillItEnd wwie = new WhenWillItEnd();
		wwie.run();
	}

	public void run() {
		getUserInput();
		int steps = calculateSteps(seq.toArray(new String[seq.size()]));
		printResult(steps);
	}

	private void printResult(int steps) {
		System.out.print("\nYour sequence (");
		for (int i = 0; i < seq.size(); i++) {
			System.out.print(seq.get(i));
			if (i != seq.size() - 1)
				System.out.print(", ");
		}
		System.out.printf(") returns back to a solved cube in %,d steps!\n",
			steps);

		System.out.println();
	}

	public static int calculateSteps(String[] seq) {
		Cube cube = new Cube();
		int steps = 0, len = seq.length;
		do {
			cube.rotate(seq[steps % len]);
			steps++;
		} while (!cube.isSolved());
		return steps;
	}

	private void getUserInput() {

		System.out.print("\nProper notations: ");
		for (int i = 0; i < Cube.NOTATIONS.length; i++) {
			System.out.print(Cube.NOTATIONS[i]);
			if (i != Cube.NOTATIONS.length - 1)
				System.out.print(", ");
		}

		System.out.println("\n");

		Scanner keyboard = new Scanner(System.in);
		while (1 == 1) {
			System.out.print("\nPlease enter a notation to add to the sequence,"
				+ " 'Q' when finished: ");
			String notation = keyboard.nextLine().toUpperCase();

			if (notation.equals("Q"))
				break;

			if (!isProperNotation(notation))
				System.out.println(notation + " is not a proper notation!");
			else seq.add(notation);
		}
	}

	private boolean isProperNotation(String notation) {
		for (String str : Cube.NOTATIONS)
			if (notation.equals(str))
				return true;
		return false;
	}
}