import java.util.ArrayList;
import java.util.Scanner;

public class SequenceSearch {

	private ArrayList<String> longestSequence;
	private ArrayList<String> shortestSequence;
	private int longestLength, shortestLength;
	private int length;

	public SequenceSearch() {
		longestLength = 0;
		shortestLength = Integer.MAX_VALUE;
	}

	public static void main(String... pumpkins) {
		SequenceSearch ls = new SequenceSearch();
		ls.run();
	}

	public void run() {
		getUserInput();
	}

	private void getUserInput() {
		Scanner keyboard = new Scanner(System.in);
		while (1 == 1) {
			System.out.print("\nEnter the length of the sequence: ");
			String input = keyboard.nextLine();
			try {
				length = Integer.parseInt(input);
				if (length < 1)
					System.out.println("Too short (at least 1)!");
				else break;
			} catch (NumberFormatException e) {
				System.out.println("'" + input + "' is not a valid number!");
			}
		}
	}
}