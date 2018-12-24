import java.util.*;

/*
A small interface meant to run on terminals and command lines.
Should only do input and output functions
Should be minimal to be changed into a better interface later

--------------------------------------------------------------------------------------------
All return types and parameters are temporary and subject to change when actual coding starts
All functions are just ideas and subject to changes
*/
public class CommandLinePrompt {
	SudokuGame game;
	boolean quit;
	/* Constructor
	TODO
	*/
	public CommandLinePrompt() {
		game = new SudokuGame();
		quit = false;
		setup();
		while (!checkFinished()) {
			if (quit) {
				return;
			}
			turn();
		}

		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		showBoard();
		System.out.println("You finished the puzzle!");
	}

	private void setup() {
		Scanner input = new Scanner(System.in);
		while(true) {
			System.out.println("Would you like to randomly generate a puzzle (0), or enter your own? (1)");
			if (input.hasNextInt()) {
				int op = input.nextInt();
				if (op == 0) {
					generatePuzzle();
					break;
				}
				else if (op == 1) {
					userPuzzle();
					break;
				}
				else {
					System.out.println("Please enter 0 for a random puzzle, or 1 to create your own puzzle.");
					//input.next();
				}
			}
			else {
				System.out.println("Please enter 0 for a random puzzle, or 1 to create your own puzzle.");
				input.next();
			}
		}
	}

	private void turn() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		showBoard();
		Scanner input = new Scanner(System.in);

		System.out.println("What would you like to do?");
		System.out.println("Options: solve, check, add, reset, quit.");
		while(true) {
			String in = input.nextLine();
			in = in.toLowerCase();
			if (in.equals("solve")) {
				solve();
				break;
			}
			if (in.equals("check")) {
				checkProg();
				break;
			}
			if (in.equals("add")) {

				System.out.println("Please enter the x coordinate of your value, or type 'done' to finish.");
				if (input.hasNextInt()) {
					int x = input.nextInt();
					if (x < 1 || x > 9) {
						System.out.println("Please enter a value between and including 1 and 9.\n");
						//input.next();
						break;
					}

					System.out.println("Please enter the y coordinate of your value.");
					if (input.hasNextInt()) {
						int y = input.nextInt();
						if (y < 1 || y > 9) {
							System.out.println("Please enter a value between and including 1 and 9.\n");
							//input.next();
							break;
						}

						System.out.println("Please enter the value to insert.");
						if (input.hasNextInt()) {
							int z = input.nextInt();
							if (z < 1 || z > 9) {
								System.out.println("Please enter a value between and including 1 and 9.\n");
								//input.next();
								break;
							}
							if (game.isPuz(x-1,y-1)) {
								System.out.println("Whoops, that number is part of the original puzzle.");
								input.nextLine();
								break;
							}
							game.addValue(x-1,y-1,z);
							System.out.println("Inserted.\n");
							input.nextLine();
							break;
						}
						else {
							System.out.println("Invalid input, resetting.\n");
						}

					}
					else {
						System.out.println("Invalid input, resetting.\n");
					}
				}

				break;
			}
			if (in.equals("reset")) {
				resetPuzzle();
				break;
			}
			if (in.equals("quit")) {
				this.quit = true;
				break;
			}

		}

		System.out.println("Press Enter to continue");
		input.nextLine();

	}

	/* Prints Board to command line
	TODO
	*/
	private void showBoard() {
		System.out.println(game);
	}

	/*
	Adds value to position
	TODO
	*/
	private void addValue(int x, int y, int value) {
		game.addValue(x-1,y-1,value);
	}	

	/* Solves the puzzle
	TODO
	*/
	private void solve() {
		game.solve();
	}

	/* Generates a puzzle
	TODO
	*/
	private void generatePuzzle() {
		game.generatePuzzle();
	}

	private void userPuzzle() {
		Scanner input = new Scanner(System.in);
		while (true) {
			showBoard();
			System.out.println("Please enter the x coordinate of your value, or type 'done' to finish.");
			if (input.hasNextInt()) {
				int x = input.nextInt();
				if (x < 1 || x > 9) {
					System.out.println("Please enter a value between and including 1 and 9.\n");
					//input.next();
					continue;
				}

				System.out.println("Please enter the y coordinate of your value.");
				if (input.hasNextInt()) {
					int y = input.nextInt();
					if (y < 1 || y > 9) {
						System.out.println("Please enter a value between and including 1 and 9.\n");
						//input.next();
						continue;
					}

					System.out.println("Please enter the value to insert.");
					if (input.hasNextInt()) {
						int z = input.nextInt();
						if (z < 1 || z > 9) {
							System.out.println("Please enter a value between and including 1 and 9.\n");
							//input.next();
							continue;
						}
						game.userPuzzle(x-1,y-1,z);
						System.out.println("Inserted.\n");
						continue;
					}
					else {
						System.out.println("Invalid input, resetting.\n");
					}

				}
				else {
					System.out.println("Invalid input, resetting.\n");
				}
			}

			String entered = input.nextLine();
			if (entered.toLowerCase().equals("done")) {
				System.out.println("Puzzle complete. Exiting.\n");
				break;
			}

		}
	}

	/*Empties the board
	TODO
	*/
	private void resetBoard() {
		game.resetBoard();
	}

	/* Resets the puzzle (removes all user inputted values)
	TODO
	*/
	private void resetPuzzle() {
		game.resetPuzzle();
	}

	/* Checks if current progress is valid
	Checks for obvious contradictions
	Also used to check if solution works
	TODO
	*/
	private void checkProg() {
		if (game.checkProg()) {
			System.out.println("So far, so good.");
		}
		else {
			System.out.println("You've made a mistake somewhere.");
		}
	}

	private boolean checkFinished() {
		return game.checkFinished();
	}

	public static void main(String[] args) {
		CommandLinePrompt test = new CommandLinePrompt();
	}

}