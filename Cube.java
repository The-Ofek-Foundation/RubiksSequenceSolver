public class Cube {

	public static final String[] NOTATIONS = {
		"U", "U'", "L", "L'", "F", "F'", "R", "R'", "B", "B'", "D", "D'"
	};

	private int[][][] cube;
	//  1
	// 2345
	//  6

	public Cube() {
		cube = new int[6][3][3];

		// initialize cube
		for (int i = 0; i < cube.length; i++)
			for (int[] row : cube[i])
				for (int a = 0; a < row.length; a++)
					row[a] = i + 1;
	}

	public boolean isSolved() {
		for (int i = 0; i < cube.length; i++)
			for (int[] row : cube[i])
				for (int a = 0; a < row.length; a++)
					if (row[a] != i + 1)
						return false;
		return true;
	}

	public void scramble() { scramble(30); }

	public void scramble(int numRotations) {
		for (int i = 0; i < numRotations; i++)
			rotate(NOTATIONS[(int)(Math.random() * NOTATIONS.length)]);
	}

	public void rotate(String notation) {
		switch (notation.toUpperCase()) {
			case "U":
				swap(0, true, false, 4, 3, 2, 1);
				swapFace(0, true);
				break;
			case "U'":
				swap(0, true, false, 1, 2, 3, 4);
				swapFace(0, false);
				break;
			case "L":
				swap(new int[] {0, 0, 0, 2}, false,
					new boolean[] {false, false, true, true}, 0, 2, 5, 4);
				swapFace(1, true);
				break;
			case "L'":
				swap(new int[] {2, 0, 0, 0}, false,
					new boolean[] {true, false, false, true}, 4, 5, 2, 0);
				swapFace(1, false);
				break;
			case "F":
				swap(new int[] {2, 0, 0, 2},
					new boolean[] {true, false, true, false},
					new boolean[] {false, true, false, true}, 0, 3, 5, 1);
				swapFace(2, true);
				break;
			case "F'":
				swap(new int[] {2, 0, 0, 2},
					new boolean[] {false, true, false, true},
					new boolean[] {false, true, false, true}, 1, 5, 3, 0);
				swapFace(2, false);
				break;
			case "R":
				swap(new int[] {0, 2, 2, 2}, false,
					new boolean[] {true, false, false, true}, 4, 5, 2, 0);
				swapFace(3, true);
				break;
			case "R'":
				swap(new int[] {2, 2, 2, 0}, false,
					new boolean[] {false, false, true, true}, 0, 2, 5, 4);
				swapFace(3, false);
				break;
			case "B":
				swap(new int[] {0, 2, 2, 0},
					new boolean[] {false, true, false, true},
					new boolean[] {false, true, false, true}, 1, 5, 3, 0);
				swapFace(4, true);
				break;
			case "B'":
				swap(new int[] {0, 2, 2, 0},
					new boolean[] {true, false, true, false},
					new boolean[] {false, true, false, true}, 0, 3, 5, 1);
				swapFace(4, false);
				break;
			case "D":
				swap(2, true, false, 1, 2, 3, 4);
				swapFace(5, true);
				break;
			case "D'":
				swap(2, true, false, 4, 3, 2, 1);
				swapFace(5, false);
				break;
		}
	}

	private void swapFace(int fN, boolean clock) {
		int[][] face = new int[3][3];
		for (int i = 0; i < face.length; i++)
			for (int a = 0; a < face[i].length; a++)
				face[i][a] = cube[fN][clock ? 2 - a:a][clock ? i:2 - i];

		for (int i = 0; i < face.length; i++)
			for (int a = 0; a < face[i].length; a++)
				cube[fN][i][a] = face[i][a];
	}

	private void swap(int rIndex, boolean row, boolean swap, int... sides) {
		swap(new int[] {rIndex, rIndex, rIndex, rIndex},
			new boolean[] {row, row, row, row},
			new boolean[] {swap, swap, swap, swap}, sides);
	}

	private void swap(int[] rIndex, boolean row, boolean swap, int... sides) {
		swap(rIndex, new boolean[] {row, row, row, row},
			new boolean[] {swap, swap, swap, swap}, sides);
	}

	private void swap(int[] rIndex, boolean row, boolean[] swap, int... sides) {
		swap(rIndex, new boolean[] {row, row, row, row}, swap, sides);
	}

	private void swap(int[] rIndex, boolean[] row, boolean[] swap, int... sides) {
		int[] tmp = getRow(rIndex[3], row[3], swap[3], sides[3]);
		for (int i = 3; i > 0; i--)
			setRow(rIndex[i], row[i], sides[i],
				getRow(rIndex[i - 1], row[i - 1], swap[i - 1], sides[i - 1]));
		setRow(rIndex[0], row[0], sides[0], tmp);
	}

	private int[] getRow(int rIndex, boolean row, boolean swap, int side) {
		int[] r = new int[3];
		for (int i = 0; i < 3; i++)
			r[swap ? 2 - i:i] = cube[side][row ? rIndex:i][row ? i:rIndex];
		return r;
	}

	private void setRow(int rIndex, boolean row, int side, int[] r) {
		for (int i = 0; i < 3; i++)
			cube[side][row ? rIndex:i][row ? i:rIndex] = r[i];
	}

	@Override
	public String toString() {
		String str = "";

		// Add 1
		for (int[] row : cube[0]) {
			str += "\n\t        ";
			for (int i = 0; i < row.length; i++)
				str += row[i] + " ";
		}
		str += "\n\t       -------";


		// Add 2, 3, 4, 5
		for (int i = 0; i < 3; i++) {
			str += "\n\t";
			for (int a = 1; a < 5; a++) {
				for (int b = 0; b < 3; b++)
					str += cube[a][i][b] + " ";
				if (a != 4)
					str += "| ";
			}
		}
		str += "\n\t       -------";


		// Add 6
		for (int[] row : cube[5]) {
			str += "\n\t        ";
			for (int i = 0; i < row.length; i++)
				str += row[i] + " ";
		}

		return str + '\n';
	}

	public static void main(String... pumpkins) {
		Cube c = new Cube();
		while (1 == 1) {
			System.out.println(c);
			c.rotate(new java.util.Scanner(System.in).nextLine());
		}
		// c.rotate("D");
		// System.out.println(c);
		// c.rotate("D'");
		// c.scramble();
		// System.out.println(c);
	}
}