import java.util.Random;

/**
Most of the functions not involving directly inputting and storing will be here
Interfaces will interact with this class instead of any other
Note: Positions start from 0,0 and go to 8,8 starting from the top left corner
	For users, 1,1 to 9,9 would be easier to use,
	so convert the user input to computer input before passing into a method.
	None of the methods will do the conversion, or be able to tell
Note: As of so far, none of the methods check for invalid input.
*/
public class SudokuGame {
	SudokuBoard board;
	boolean[][] puzgen;


	/**
	Builds a new instance of SudokuGame and initilizes the contents
	*/
	public SudokuGame() {
		board = new SudokuBoard();
		puzgen = new boolean[9][9];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				puzgen[i][j] = false;
			}
		}
	}

	/**
	Returns the values inside the board in an easily separated way
	Goes from left to right, up to bottom
	val1,val2,...
	@return a string containing all the values in an easily separated manner
	*/
	public String showBoard() {
		return board.toString();
	}


	/**
	Adds value to position
	@param x The row to insert to
	@param y The column to insert to
	@param value The value to insert (add 0 to remove)
	*/
	public void addValue(int x, int y, int value) {
		board.addVal(x,y,value);
	}

	/**
	Gets the value at a specified position
	@param x The row specified
	@param y The column specified
	@return The value at that position (0 is empty)
	*/
	public int getValue(int x, int y) {
		return board.getVal(x,y);
	}

	/**
	Clears a space, unless the value there is a puzzle starter value occupying the space
	@param x The row of the value to remove
	@param y The column of the value to remove
	*/
	public void clearSpace(int x, int y) {
		if (puzgen[x][y]) {
			return;
		}
		addValue(x,y,0);
	}

	/**
	Clears a space, regardless of what is stored
	@param x The row of the value to remove
	@param y The column of the value to remove
	*/
	public void forceClearSpace(int x, int y) {
		puzgen[x][y] = false;
		clearSpace(x,y);
	}

	/**
	Allows the user to input a custom puzzle value
	@param x The row
	@param y The column
	@param value The value
	*/
	public void userPuzzle(int x, int y, int value) {
		puzgen[x][y] = true;
		this.addValue(x,y,value);
	}

	/**
	Solves the puzzle
	@return true if the puzzle was solved, false otherwise
	*/
	public boolean solve() {
		return solve(0,0);
	}


	/**
	Recursively solves the puzzle using backtracking
	@param x The position of the next value to try
	@param y The position of the next value to try
	@return true if the puzzle was solved, false otherwise
	*/
	private boolean solve(int x, int y) {
		int newx = x;
		int newy;

		if (y == 8) {
			newx++;
			newy = 0;
		}
		else {
			newy = y+1;
		}
		if (x > 8 || y > 8) {
			return true;
		}
		if (puzgen[x][y] == true) {
			return solve(newx,newy);
		}
		for (int i = 1; i <= 9; i++) {
			if (checkValid(x,y,i) == false) {
				continue;
			}
			addValue(x,y,i);
			if (solve(newx,newy)) {
				return true;
			}
			clearSpace(x,y);
		}
		return false;
	}

	/* Generates a puzzle
	TODO
	*/
	public void generatePuzzle() {
		Random r = new Random();
		for (int i = 0; i < 17; i++) {
			int x  = r.nextInt(9);
			int y = r.nextInt(9);
			int z = r.nextInt(9)+1;
			if (checkValid(x,y,z)) {
				userPuzzle(x,y,z);
			}
			else {
				i--;

			}
		}
		solve();
		while (countVals() > 25) {
			int x = r.nextInt(9);
			int y = r.nextInt(9);
			if (getValue(x,y) != 0) {
				forceClearSpace(x,y);
			}
		}
		convPuzzle();
	}

	private int countVals() {
		int ret = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (getValue(i,j) != 0) { 
					ret++;
				}
			}
		}
		return ret;
	}

	private void convPuzzle() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (getValue(i,j) != 0) {
					puzgen[i][j] = true;
				}
			}
		}
	}

	/**
	Clears the contents of the board and resets the counter for puzzle starter numbers	
	*/
	public void resetBoard() {
		board.resetBoard();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				puzgen[i][j] = false;
			}
		}
	}

	/**
	Resets the puzzle, removing all values except the puzzle starters
	*/
	public void resetPuzzle() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				clearSpace(i,j);
			}
		}
	}

	/**
	Checks if it's valid to add a value to that space
	Only checks for immediate contradictions
	@param x The row to check
	@param y The column to check for
	@param value The value to check for
	*/
	public boolean checkValid(int x, int y, int value) {

		if (board.checkSquare(x,y,value) > 0 || board.checkRow(x,value) > 0 || board.checkCol(y,value) > 0 || puzgen[x][y]) {
			return false;
		}
		return true;
	}

	/**
	Checks if current progress is valid
	Checks for obvious contradictions
	Note: Will maybe change this to return a list of positions where there is an obvious contradiction
	*/
	public boolean checkProg() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int tem = getValue(i,j);
				if (tem == 0) {
					continue;
				}
				if (board.checkSquare(i,j,tem) > 1 || board.checkRow(i,tem) > 1 || board.checkCol(j,tem) > 1) {
					return false;
				}
			}
		}
		return true;
	}


	/** 
	Returns a string representation of the board, in a format like a board
	@return the string representation of the board
	*/
	public String toString() {
		return board.sudokuPrint();
	}

	/*@
	Used for internal testing
	@param args Just there
	*/
	public static void main (String[] args) {
		SudokuGame test = new SudokuGame();

		/*
		test.userPuzzle(0,0,5);
		test.userPuzzle(0,1,3);
		test.userPuzzle(0,4,7);
		test.userPuzzle(1,0,6);
		test.userPuzzle(1,3,1);
		test.userPuzzle(1,4,9);
		test.userPuzzle(1,5,5);
		test.userPuzzle(2,1,9);
		test.userPuzzle(2,2,8);
		test.userPuzzle(2,7,6);
		test.userPuzzle(3,0,8);
		test.userPuzzle(3,4,6);
		test.userPuzzle(3,8,3);
		test.userPuzzle(4,0,4);
		test.userPuzzle(4,3,8);
		test.userPuzzle(4,5,3);
		test.userPuzzle(4,8,1);
		test.userPuzzle(5,0,7);
		test.userPuzzle(5,4,2);
		test.userPuzzle(5,8,6);
		test.userPuzzle(6,1,6);
		test.userPuzzle(6,6,2);
		test.userPuzzle(6,7,8);
		test.userPuzzle(7,3,4);
		test.userPuzzle(7,4,1);
		test.userPuzzle(7,5,9);
		test.userPuzzle(7,8,5);
		test.userPuzzle(8,4,8);
		test.userPuzzle(8,7,7);
		test.userPuzzle(8,8,9);

		System.out.println(test);
		System.out.println(test.showBoard());
		test.solve();
		System.out.println(test);
		test.resetPuzzle();
		System.out.println(test);*/

		System.out.println("Generated Puzzle");
		test.generatePuzzle();
		System.out.println(test);
		test.solve();
		System.out.println("Solution");
		System.out.println(test);
		
	}
}