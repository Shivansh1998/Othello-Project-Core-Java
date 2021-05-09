package othello;

import java.util.ArrayList;

import javafx.util.Pair;

public class Board {
	private char board[][];
	private final int BOARD_SIZE = 8;
	private char p1Symbol, p2Symbol;

	// Count for Total Moves
	private int totalMoves;

	// Check for the Board is Full?????
	public boolean completeGame() {
		if (totalMoves == BOARD_SIZE * BOARD_SIZE) {
			return true;
		}
		return false;
	}

	// Calculating number of Valid Moves.....
	public int noOfValidMoves(char symbol) {
		ArrayList<Pair<Integer, Integer>> noOfMoves = validMoves(symbol);
		return noOfMoves.size();
	}

	// Constructor for creating Othello Board.....
	public Board(char p1Symbol, char p2Symbol) {
		this.p1Symbol = p1Symbol;
		this.p2Symbol = p2Symbol;
		this.board = new char[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = ' ';
			}
		}
		board[3][3] = p1Symbol;
		board[3][4] = p2Symbol;
		board[4][3] = p2Symbol;
		board[4][4] = p1Symbol;
		totalMoves += 4;
	}

	// Displaying Current Board.....
	public void printBoard() {
		String hline = "  +---+---+---+---+---+---+---+---+";
		System.out.println("X|Y 0   1   2   3   4   5   6   7");
		System.out.println(hline);
		for (int i = 0; i < BOARD_SIZE; i++) {
			System.out.print("" + i + " ");
			for (int j = 0; j < BOARD_SIZE; j++) {
				System.out.print("| " + board[i][j] + " ");
			}
			System.out.println('|');
			System.out.println(hline);
		}
	}

	// Constructing list of Valid Moves.....
	public ArrayList<Pair<Integer, Integer>> validMoves(char symbol) {
		ArrayList<Pair<Integer, Integer>> list = new ArrayList<>();
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (checkMove(symbol, i, j)) {
					Pair<Integer, Integer> pair = new Pair<>(i, j);
					list.add(pair);
				}
			}
		}
		return list;
	}

	// Checking and performing move......
	public boolean move(char symbol, int x, int y) {
		if (x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE || board[x][y] != ' ') {
			return false;
		}

		boolean ans = false;

		// Array for movement for X
		int[] xDir = { -1, -1, 0, 1, 1, 1, 0, -1 };

		// Array for movement for Y
		int[] yDir = { 0, 1, 1, 1, 0, -1, -1, -1 };

		for (int i = 0; i < xDir.length; i++) {
			int xstep = xDir[i];
			int ystep = yDir[i];
			int xnew = x + xstep;
			int ynew = y + ystep;
			int count = 0;
			while (xnew >= 0 && xnew < 8 && ynew >= 0 && ynew < 8) {
				// empty cell
				if (board[xnew][ynew] == ' ') {
					break;
				}

				else if (board[xnew][ynew] != symbol) {
					xnew += xstep;
					ynew += ystep;
					count++;
				}

				// conversion is possible
				else {
					if (count > 0) {
						ans = true;
						int convertX = xnew - xstep;
						int convertY = ynew - ystep;
						while (convertX != x || convertY != y) {
							board[convertX][convertY] = symbol;
							convertX -= xstep;
							convertY -= ystep;
						}
					}
					break;
				}
			}
		}
		if (ans) {
			board[x][y] = symbol;
			totalMoves++;
		}
		return ans;
	}

	// helper function to generate list of Valid Moves.
	public boolean checkMove(char symbol, int x, int y) {
		if (x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE || board[x][y] != ' ') {
			return false;
		}

		boolean ans = false;

		int[] xDir = { -1, -1, 0, 1, 1, 1, 0, -1 };
		int[] yDir = { 0, 1, 1, 1, 0, -1, -1, -1 };

		for (int i = 0; i < xDir.length; i++) {
			int xstep = xDir[i];
			int ystep = yDir[i];
			int xnew = x + xstep;
			int ynew = y + ystep;
			int count = 0;
			while (xnew >= 0 && xnew < 8 && ynew >= 0 && ynew < 8) {
				// empty cell
				if (board[xnew][ynew] == ' ') {
					break;
				}

				else if (board[xnew][ynew] != symbol) {
					xnew += xstep;
					ynew += ystep;
					count++;
				}

				else {
					// Move is valid
					if (count > 0)
						ans = true;
					break;
				}
			}
		}

		return ans;
	}

	// Calculating total no of given symbol in board.....
	public int countsymbol(char symbol) {
		int ans = 0;
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (board[i][j] == symbol) {
					ans++;
				}
			}
		}
		return ans;
	}
}