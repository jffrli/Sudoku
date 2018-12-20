/*
A 3x3 board of squares

--------------------------------------------------------------------------------------------
All return types and parameters are temporary and subject to change when actual coding starts
All functions are just ideas and subject to changes
*/
public class SudokuBoard {
	SudokuSquare[][] board;

	/**
	Initializes the entire board
	*/
	public SudokuBoard() {
		board = new SudokuSquare[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = new SudokuSquare();
			}
		}
	}


	/**
	Adds value to specified position
	@param x The row to add to
	@param y The column to add to
	@param value The value to add
	*/
	public void addVal(int x, int y, int value) {
		board[x/3][y/3].addVal(x%3,y%3, value);
	}


	/**
	Checks if a value is in a given row
	@param row The row to check
	@param value The value to check for
	@return true if the value is in the given row
	*/
	public boolean checkRow(int row, int value) {
		for (int i = 0; i < 9; i++) {
			if (this.getVal(row,i) == value) {
				return true;
			}
		}
		return false;	
	}

	/**
	Checks if a value is in a given column
	@param col The column to check
	@param value The value to check for
	@return true if the value is in the given column
	*/
	public boolean checkCol(int col, int value) {
		for (int i = 0; i < 9; i++) {
			if (this.getVal(i,col) == value) {
				return true;
			}
		}
		return false;
	}

	/**
	Returns the value at a specified location
	@param x The row
	@param y The column
	@return The value at the row and column
	*/
	public int getVal(int x, int y) {
		return board[x/3][y/3].getVal(x%3,y%3);
	}

	/**
	Resets every space in the board
	*/
	public void resetBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = new SudokuSquare();
			}
		}
	}

	/**
	Returns a specific 3x3 square (usually for testing purposes)
	@param x The row (in the array of squares, not spaces)
	@param y The column (in the array of squares, not spaces)
	*/
	public SudokuSquare getSquare(int x, int y) {
		return board[x][y];
	}

	/**
	Returns a 9x9 array of all the board's contents
	Intended for UI display purposes
	@return a 9x9 int array of all the values inside the board
	*/
	public int toArray() {
		int[][] ret = new int [9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				ret[i][j] = this.getVal(i,j);
			}
		}
	}

	/**
	Returns a string representation of the board
	Note: when more of the project is written, this will likely change into the same format as the squares
	@return a string representation of the board in a format like a real sudoku
	*/
	public String toString() {
		String ret = "";
		String del = "";
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				//ret += del + "(" + i + "," + j + "): " + this.getVal(i,j);
				//del = "\n";
				ret += del + this.getVal(i,j);
				
				if (j%3 == 2) {
					del = " | ";
				}
				else {
					del = " ";
				}
			}
			if (i%3 == 2 && i!= 8) {
				ret += "\n---------------------\n";
			}
			else {
				ret += "\n";
			}
			del = "";
		}

		return ret;
	}

	/**
	Used for testing only
	@param args Not used
	*/
	public static void main(String[] args) {
		SudokuBoard test = new SudokuBoard();
		test.addVal(3,8,5);
		test.addVal(1,2,6);
		test.addVal(4,7,2);
		System.out.println(test);
	}

}