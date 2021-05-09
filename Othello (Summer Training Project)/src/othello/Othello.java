package othello;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.util.Pair;

public class Othello {
	private Board board;
	private Player player1, player2;
	private static boolean anotherGame = true;

	// Count for Total Number of Games
	public static int No_of_Games = 0;

	// Count for Number of Draw Games
	public static int Draw = 0;

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		No_of_Games++;
		System.out.println("\t\t\t OTHELLO GAME : ");
		Othello o = new Othello();
		o.startGame();
		while (anotherGame) {
			o.create_Board();
			No_of_Games++;
		}

		System.out.println("************* THANKS FOR PLAYING ************");

	}

	public void startGame() {

		// It comprises of 3 steps:

		// 1. Taking Player Information. (Player take_player_info(PLAYER_NUMBER))
		// 2. Creating Board with the Given Symbols &Conduct Game. (create_Board())
		// 3. Display Score Board. (printScoreBoard())

		player1 = take_player_info(1);
		player2 = take_player_info(2);
		while (player1.getName().compareTo(player2.getName()) == 0) {
			System.out.println("Name already taken !! Choose another name for Player 2 !!");
			System.out.println("Enter Player 2's name :");
			player2.setName(sc.nextLine());
		}
		while (player1.getSymbol() == player2.getSymbol()) {
			System.out.println("Symbol already taken !! Choose another Symbol for Player 2 !!");
			System.out.println("Enter Player 2's Symbol :");
			player2.setSymbol(sc.nextLine().charAt(0));
		}

	}

	// Taking Private information for the Players
	private Player take_player_info(int n) {
		System.out.println("Enter Player " + n + "'s name :");
		String name = sc.nextLine();
		System.out.println("Enter Player " + n + "'s Symbol :");
		char symbol = sc.nextLine().charAt(0);
		Player p = new Player(name, symbol);
		return p;
	}

	public void create_Board() {

		// Create board
		board = new Board(player1.getSymbol(), player2.getSymbol());
		System.out.println();
		System.out.println("OTHELLO Board : 8 X 8 Board");
		board.printBoard();

		// Conducting Game

		// Check for Turn
		boolean p1Turn = true;
		// Check for valid Move
		boolean validMove;

		// List containing Valid moves for Player 1
		ArrayList<Pair<Integer, Integer>> validMovesP1 = board.validMoves(player1.getSymbol());

		// List containing Valid moves for Player 1
		ArrayList<Pair<Integer, Integer>> validMovesP2 = board.validMoves(player1.getSymbol());

		int no_of_valid_Moves_P1 = validMovesP1.size();
		int no_of_valid_Moves_P2 = validMovesP2.size();

		while (!board.completeGame() && (no_of_valid_Moves_P1 > 0 || no_of_valid_Moves_P2 > 0)) {
			validMovesP1 = board.validMoves(player1.getSymbol());
			validMovesP2 = board.validMoves(player2.getSymbol());
			no_of_valid_Moves_P1 = validMovesP1.size();
			no_of_valid_Moves_P2 = validMovesP2.size();

			if (p1Turn) {
				// PLAYER 1

				// VALID MOVES EXIST.....
				if (no_of_valid_Moves_P1 > 0) {
					System.out.println("Player 1 : " + player1.getName() + "'s Turn :");
					System.out.println("Enter row & column (X,Y):");
					int x = sc.nextInt();
					int y = sc.nextInt();

					// Checking and Making move if valid.
					validMove = board.move(player1.getSymbol(), x, y);

					// VALID MOVES.....
					if (validMove == true) {
						board.printBoard();
						p1Turn = false;
					} else {

						// INVALID MOVES.....
						System.out.println("!!!!!! INVALID MOVE !!!!!");
						System.out.println("So,Do u need Hints ??? (Enter Y/N)");

						// NEED HINTS......
						sc.nextLine();
						char choice = sc.nextLine().charAt(0);
						if (choice == 'Y' || choice == 'y') {
							printHint(validMovesP1);
						}

					}
				} else {
					p1Turn = false;
					System.out.println("OOPS !!!!! NO MOVE VALID MOVES for Player 1 : " + player1.getName());
				}

			} else {
				// PLAYER 2

				// VALID MOVES EXIST.....
				if (no_of_valid_Moves_P2 > 0) {
					System.out.println("Player 2 : " + player2.getName() + "'s Turn :");
					System.out.println("Enter row & column (X,Y):");
					int x = sc.nextInt();
					int y = sc.nextInt();
					// Checking and Making move if valid.
					validMove = board.move(player2.getSymbol(), x, y);

					// VALID MOVES.....
					if (validMove == true) {
						board.printBoard();
						p1Turn = true;
					} else {

						// INVALID MOVES.....
						System.out.println("!!!!!! INVALID MOVE !!!!!");

						// NEED HINTS......
						sc.nextLine();
						System.out.println("So,Do u need Hints ??? (Enter Y/N)");
						char choice = sc.nextLine().charAt(0);
						if (choice == 'Y' || choice == 'y') {
							printHint(validMovesP2);
						}
					}
				} else {

					// VALID MOVES DOES NOT EXIST.....
					p1Turn = true;
					System.out.println("OOPS !!!!! NO MOVE VALID MOVES for Player 2 : " + player2.getName());
				}
			}
		}

		// GAME OVER
		System.out.println("!!!!! GAME OVER !!!!!");

		// Calculating Result........
		int p1 = board.countsymbol(player1.getSymbol());
		int p2 = board.countsymbol(player2.getSymbol());
		if (p1 > p2) {
			player1.setWinGames();
			System.out.println("PLAYER 1 " + player1.getName() + " WINS !!!!!!");
		} else if (p1 < p2) {
			player2.setWinGames();
			System.out.println("PLAYER 2 " + player2.getName() + " WINS !!!!!!");
		} else {
			Draw++;
			System.out.println("MATCH DRAW !!!!!");
		}

		printScoreBoard();

		System.out.println();
		sc.nextLine();

		// Another Game ????
		System.out.println("Want to play a new Game ? (Enter Y/N)");
		char choice = sc.nextLine().charAt(0);
		if (choice == 'N' || choice == 'n') {
			anotherGame = false;
		}

	}

	// Display Score Board
	private void printScoreBoard() {
		System.out.println("\tSCORE BOARD");
		System.out.println("Total number of games = " + No_of_Games);
		System.out.println(player1.getName() + " won " + player1.getWinGames() + " times");
		System.out.println(player2.getName() + " won " + player2.getWinGames() + " times");
		System.out.println("Number of tied games = " + Draw);
	}

	// Printing the Calculated Valid Moves for the required Player
	private void printHint(ArrayList<Pair<Integer, Integer>> validMoves) {
		int i = 0;
		System.out.print("Moves : { ");
		while (i < validMoves.size()) {
			System.out.print("(" + validMoves.get(i).getKey() + "," + validMoves.get(i).getValue() + ")");
			i++;
		}
		System.out.println(" }");
	}

}
