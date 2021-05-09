package othello;

public class Player {

	private String name;
	private char symbol;

	// Count of win games.
	private int winGame;

	// Default Constructor
	public Player() {
		name = null;
		symbol = '\0';
		winGame = 0;
	}

	// Parameterised Constructor for Creating Player
	public Player(String name, char symbol) {
		setName(name);
		setSymbol(symbol);
	}

	// Function to set name.
	public void setName(String name) {
		if (!name.isEmpty()) {
			this.name = name;
		}
	}

	// Function to set symbol.
	public void setSymbol(char symbol) {
		if (symbol != '\0') {
			this.symbol = symbol;
		}
	}

	// Function to get name.
	public String getName() {
		return this.name;
	}

	// Function to get symbol.
	public char getSymbol() {
		return this.symbol;
	}

	// Function to set count of win games.
	public void setWinGames() {
		this.winGame++;
	}

	// Function to get count of win games.
	public int getWinGames() {
		return this.winGame;
	}

}
