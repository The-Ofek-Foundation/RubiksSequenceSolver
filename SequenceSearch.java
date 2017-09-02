import java.util.Scanner;
import java.util.ArrayList;

public class SequenceSearch {

	private String[] longestSequence, shortestSequence;
	private String[] currentSequence;
	private ArrayList<String[]> targetSequences;
	private int longestLength, shortestLength;
	private int length;
	private int targetLength;

	public static final String LAST_NOTATION = Cube.NOTATIONS[Cube.NOTATIONS.length - 1];

	public SequenceSearch() {
		longestLength = 0;
		shortestLength = Integer.MAX_VALUE;
		targetSequences = new ArrayList<String[]>();
	}

	public static void main(String... pumpkins) {
		SequenceSearch ls = new SequenceSearch();
		ls.run();
	}

	public void run() {
		getUserInput();

		currentSequence = new String[length];
		for (int i = 0; i < currentSequence.length; i++)
			currentSequence[i] = Cube.NOTATIONS[0];

		sequenceSearch();
		printResults();
	}

	private void printResults() {
		System.out.printf("\nFound %,d sequences matching your length (%d):\n",
			targetSequences.size(), targetLength);
		for (String[] seq : targetSequences) {
			System.out.print("\n\t");
			printSequence(seq);
		}

		System.out.printf("\n\nThe longest sequence found took %,d steps:\t",
			longestLength);
		printSequence(longestSequence);

		System.out.printf("\n\nThe shortest sequence found took %,d steps:\t",
			shortestLength);
		printSequence(shortestSequence);
		System.out.println("\n");
	}

	private void printSequence(String[] seq) {
		for (int i = 0; i < seq.length; i++) {
			System.out.print(seq[i]);
			if (i != seq.length - 1)
				System.out.print(", ");
		}
	}

	private void sequenceSearch() {
		do {
			int seqLen = WhenWillItEnd.calculateSteps(currentSequence);
			if (seqLen == targetLength)
				targetSequences.add(hardCopy(currentSequence));
			if (seqLen > longestLength) {
				longestLength = seqLen;
				longestSequence = hardCopy(currentSequence);
			}
			if (seqLen < shortestLength) {
				shortestLength = seqLen;
				shortestSequence = hardCopy(currentSequence);
			}
		} while(updateSequence());
	}

	private boolean updateSequence() {
		for (int i = currentSequence.length - 1; i >= 0; i--)
			if (currentSequence[i].equals(LAST_NOTATION))
				currentSequence[i] = Cube.NOTATIONS[0];
			else {
				currentSequence[i] = nextNotation(currentSequence[i]);
				return true;
			}
		return false;
	}

	private String nextNotation(String notation) {
		for (int i = 0; i < Cube.NOTATIONS.length; i++)
			if (Cube.NOTATIONS[i].equals(notation))
				return Cube.NOTATIONS[i + 1];
		System.err.println("This shouldn't happen");
		return null;
	}

	private void getUserInput() {
		Scanner keyboard = new Scanner(System.in);
		while (1 == 1) {
			System.out.print("\nEnter the number of notations for the sequence: ");
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

		while (1 == 1) {
			System.out.print("\nEnter the target length of the sequence: ");
			String input = keyboard.nextLine();
			try {
				targetLength = Integer.parseInt(input);
				if (length < 1)
					System.out.println("Too short (at least 1)!");
				else break;
			} catch (NumberFormatException e) {
				System.out.println("'" + input + "' is not a valid number!");
			}
		}
	}

	private String[] hardCopy(String[] list) {
		String[] copy = new String[list.length];
		for (int i = 0; i < list.length; i++)
			copy[i] = list[i];
		return copy;
	}
}