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
	@return number of times the value occurs in the row
	*/
	public int checkRow(int row, int value) {
		int ret = 0;
		for (int i = 0; i < 9; i++) {
			if (this.getVal(row,i) == value) {
				ret++;
			}
		}
		return ret;
	}

	/**
	Checks if a value is in a given column
	@param col The column to check
	@param value The value to check for
	@return number of times the value occurs in the column
	*/
	public int checkCol(int col, int value) {
		int ret = 0;
		for (int i = 0; i < 9; i++) {
			if (this.getVal(i,col) == value) {
				ret++;
			}
		}
		return ret;
	}

	/**
	Checks if a value is in a given square
	@param x The row (in the 3x3 array of squares)
	@param y The column (in the 3x3 array of sqaures)
	@param value The value to check for
	@return true if the value is inside the given square
	*/
	public int checkSquare(int x, int y, int value) {
		return this.checkSquare(this.getSquare(x,y), value);
	}

	/**
	Checks if a value is in a given square
	@param square The square to check
	@param value The value to check for
	@return true if the value is inside the given square
	*/
	public int checkSquare(SudokuSquare square, int value) {
		return square.checkVal(value);
	}

	/**
	Returns the value at a specified location
	@param x The row
	@param y The column
	@return The value at the row and column
	*/
	public int getVal(int x, int y) {
		//System.out.println("x:"+x+" y:"+y);
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
	@param x a row inside the square
	@param y a column inside the square
	*/
	public SudokuSquare getSquare(int x, int y) {
		return board[x/3][y/3];
	}

	/**
	Returns a 9x9 array of all the board's contents
	Intended for UI display purposes
	@return a 9x9 int array of all the values inside the board
	*/
	public int[][] toArray() {
		int[][] ret = new int [9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				ret[i][j] = this.getVal(i,j);
			}
		}
		return ret;
	}

	/**
	Returns a string representation of the board's values
	@return a string representation of the values inside of the board
	*/
	public String toString() {
		String ret = "";
		String del = "";
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				/* //format (x,y): val \n
				ret += del + "(" + i + "," + j + "): " + this.getVal(i,j);
				del = "\n";
				*/

				//format: val1,val2
				ret += del + this.getVal(i,j);
				del = ",";
			}
		}

		return ret;
	}

	/**
	Returns a string representation of the board in a sudoku board-like format
	@return a string representation of the board
	*/
	public String sudokuPrint() {
		String ret = "";
		String del = "";
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

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