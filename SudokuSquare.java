/*
3x3 Square in the board, 9 of these total
*/
public class SudokuSquare {
	int[][] square;

	/**
	Constructor
	Initializes the square
	*/
	public SudokuSquare() {
		square = new int[3][3];
	}

	/**
	Adds a value to a specified position
	@param x The row to add to
	@param y The column to add to
	@param value The value to add
	*/
	public void addVal(int x, int y, int value) {
		this.square[x][y] = value;
	}

	/**
	Checks if a value is in the square
	@param value The value to check for
	@return number of times the value occurs
	*/
	public int checkVal(int value) {
		int ret = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (this.square[i][j] == value) {
					ret++;
				}
			}
		}
		return ret;
	}

	/**
	Checks if a value is in a given row
	@param row The row to check
	@param value The value to check for
	@return number of times the value occurs in the row
	*/
	public int checkRow(int row, int value) {
		int ret = 0;
		for (int i = 0; i < 3; i++) {
			if (this.square[row][i] == value) {
				ret++;
			}
		}
		return ret;
	}

	/**
	Checks if a value is in a given column
	@param row The column to check
	@param value The value to check for
	@return number of times the value occurs in the column
	*/
	public int checkCol(int col, int value) {
		int ret = 0;
		for (int i = 0; i < 3; i++) {
			if (this.square[i][col] == value) {
				ret++;
			}
		}
		return ret;
	}

	/**
	Empties the contents of the square
	*/
	public void resetSquare() {
		square = new int[3][3];
	}

	/**
	String representation of the square
	Includes 0s
	@return String representation of all values inside the square in the format format (x,y): val
	*/
	public String toString() {
		String ret = "";
		String del = "";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				ret += del + "(" + i + "," + j + "): " + this.getVal(i,j);
				del = "\n";
			}
		}
		return ret;
	}

	/**
	Gets the value at a specific location
	@param x The row
	@param y The column
	@return The value at the specified row and column
	*/
	public int getVal(int x, int y) {
		return this.square[x][y];
	}

	/**
	For testing purposes only
	@param args Required, but not used
	*/
	public static void main(String[] args) {
		SudokuSquare test = new SudokuSquare();
		test.addVal(1,2,3);
		test.addVal(1,2,7);
		test.addVal(0,1,3);
		test.addVal(0,0,1);
		System.out.println(test);
	}

}