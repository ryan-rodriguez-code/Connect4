package game;
//class use to handle all information relating to the connect 4 game including player moves, scores, and board positions.
public class ConnectFour {
	private String[][] board;
	private int numCross;
	private boolean gravity;
	private int rounds;
	private int totalGames;
	private String p1Move;
	private int p1Score;
	private String p2Move;
	private int p2Score;
	private final String red = " X";
	private final String blue = " O";
	private final String space = " #";
//constructor called by main method which handles user responses and switches between different modes of the game.
	public ConnectFour() {
		while(true) {
			this.totalGames++;
			String input = JOP.in("Do you want to play against another player type 1; to play against the computer type 2: (You can exit by pressing cancel at any time)");
			String[] options = {"1", "2"};
			while(JOP.waitUntil(input, options)) {input = JOP.in("Do you want to play against another player type 1; to play against the computer type 2: ");}
			/*
			String input2 = JOP.in("Do you want to play normal connect type 1; to play gravity connect type 2: ");
			String[] options2 = {"1", "2"};
			while(JOP.waitUntil(input2, options2)) {input2 = JOP.in("Do you want to play normal connect type 1; to play gravity connect type 2: ");}
			*/
			
			if(input.equals("1") /*&& input2.equals("1")*/) {
				String input3 = JOP.in("How many rows do you want: ");
				while(!JOP.isInt(input3)) {input3 = JOP.in("How many rows do you want: ");}
				String input4 = JOP.in("How many columns do you want: ");
				while(!JOP.isInt(input4)) {input4 = JOP.in("How many columns do you want: ");}
				String input5 = JOP.in("How many items needed to win game: ");
				while(!JOP.isInt(input5)) {input5 = JOP.in("How many items needed to win game: ");}
				this.board = new String[Integer.valueOf(input3)][Integer.valueOf(input4)];
				for(int r = 0; r < this.board.length; r++) {for(int c = 0; c < this.board[r].length; c++) {this.board[r][c] = this.space;}}
				this.numCross = Integer.valueOf(input5);
				this.gravity = false;
				this.playGame(true);
			}
			else if(input.equals("2") /*&& input2.equals("1")*/) {
				String input3 = JOP.in("How many rows do you want: ");
				while(!JOP.isInt(input3)) {input3 = JOP.in("How many rows do you want: ");}
				String input4 = JOP.in("How many columns do you want: ");
				while(!JOP.isInt(input4)) {input4 = JOP.in("How many columns do you want: ");}
				String input5 = JOP.in("How many items needed to win game: ");
				while(!JOP.isInt(input5)) {input5 = JOP.in("How many items needed to win game: ");}
				this.board = new String[Integer.valueOf(input3)][Integer.valueOf(input4)];
				for(int r = 0; r < this.board.length; r++) {for(int c = 0; c < this.board[r].length; c++) {this.board[r][c] = this.space;}}
				this.numCross = Integer.valueOf(input5);
				this.gravity = false;
				this.playGame(false);
			}
			/*
			 * I wanted to include a gravity version of the game but never did maybe in the future
			else if(input.equals("1") && input2.equals("2")) {
				String input3 = JOP.in("How many rows do you want: ");
				while(!JOP.isInt(input3)) {input3 = JOP.in("How many rows do you want: ");}
				String input4 = JOP.in("How many columns do you want: ");
				while(!JOP.isInt(input4)) {input4 = JOP.in("How many columns do you want: ");}
				String input5 = JOP.in("How many items needed to win game: ");
				while(!JOP.isInt(input5)) {input5 = JOP.in("How many items needed to win game: ");}
				this.board = new String[Integer.valueOf(input3)][Integer.valueOf(input4)];
				for(int r = 0; r < this.board.length; r++) {for(int c = 0; c < this.board[r].length; c++) {this.board[r][c] = this.space;}}
				this.numCross = Integer.valueOf(input5);
				this.gravity = true;
				this.playGame(true);
			}
			else if(input.equals("2") && input2.equals("2")) {
				String input3 = JOP.in("How many rows do you want: ");
				while(!JOP.isInt(input3)) {input3 = JOP.in("How many rows do you want: ");}
				String input4 = JOP.in("How many columns do you want: ");
				while(!JOP.isInt(input4)) {input4 = JOP.in("How many columns do you want: ");}
				String input5 = JOP.in("How many items needed to win game: ");
				while(!JOP.isInt(input5)) {input5 = JOP.in("How many items needed to win game: ");}
				this.board = new String[Integer.valueOf(input3)][Integer.valueOf(input4)];
				for(int r = 0; r < this.board.length; r++) {for(int c = 0; c < this.board[r].length; c++) {this.board[r][c] = this.space;}}
				this.numCross = Integer.valueOf(input5);
				this.gravity = true;
				this.playGame(false);
			}
			*/
		}
	}
//handles the turn base nature of the game and reacts to all different combinations of player modes.
	private void playGame(boolean isPlayer) {
		this.rounds = 0;
		while(true) {
			this.p1MakeMove(this.gravity);
			this.updateBoard(true);
			
			if(isPlayer) {
				this.p2MakeMove(true, this.gravity);
				this.updateBoard(false);
			}
			else {
				this.p2MakeMove(false, this.gravity);
				this.updateBoard(false);
			}
			
			if(this.checkWin() == 1) {
				this.p1Score++;
				break;
			}
			else if(this.checkWin() == 2) {
				this.p2Score++;
				break;
			}
			else {
				this.rounds++;
			}
		}
	}
//makes player 1 make a move which the user must input.
	private void p1MakeMove(boolean gravity) {
		if(gravity) {
			String input = JOP.in(this.displayBoard() + "\nPlayer1: What move do you want to make type a value 0-" + (this.board[0].length - 1) + " for the colunm you want your peice to go in: \n" + this.displayScore());
			String[] options = new String[this.board[0].length]; for(int i = 0; i < this.board[0].length; i++) {options[i] = "" + i;}
			while(JOP.waitUntil(input, options)) {input = JOP.in(this.displayBoard() + "\nPlayer1: What move do you want to make type a value 0-" + (this.board[0].length - 1) + " for the colunm you want your peice to go in: \n" + this.displayScore());}
			String input2 = JOP.in(this.displayBoard() + "\nPlayer1: What direction do you want the board to move type u for up; r for right; d for down; l for left: \n" + this.displayScore());
			String[] options2 = {"u", "r", "d", "l"};
			while(JOP.waitUntil(input2, options2)) {input2 = JOP.in(this.displayBoard() + "\nPlayer1: What direction do you want the board to move type u for up; r for right; d for down; l for left: \n" + this.displayScore());}
			this.p1Move = "" + Integer.valueOf(input) + input2;
		}
		else {
			String input = JOP.in(this.displayBoard() + "\nPlayer1: What move do you want to make type a value 0-" + (this.board[0].length - 1) + " for the colunm you want your peice to go in: \n" + this.displayScore());
			String[] options = new String[this.board[0].length]; for(int i = 0; i < this.board[0].length; i++) {options[i] = "" + i;}
			while(JOP.waitUntil(input, options)) {input = JOP.in(this.displayBoard() + "\nPlayer1: What move do you want to make type a value 0-" + (this.board[0].length - 1) + " for the colunm you want your peice to go in: \n" + this.displayScore());}
			this.p1Move = "" + Integer.valueOf(input) + "n";
		}
	}
//makes player 2 make a move either the computer or a person.
	private void p2MakeMove(boolean isPlayer, boolean gravity) {
		if(gravity) {
			if(isPlayer) {
				String input = JOP.in(this.displayBoard() + "\nPlayer2: What move do you want to make type a value 0-" + (this.board[0].length - 1) + " for the colunm you want your peice to go in: \n" + this.displayScore());
				String[] options = new String[this.board[0].length]; for(int i = 0; i < this.board[0].length; i++) {options[i] = "" + i;}
				while(JOP.waitUntil(input, options)) {input = JOP.in(this.displayBoard() + "\nPlayer2: What move do you want to make type a value 0-" + (this.board[0].length - 1) + " for the colunm you want your peice to go in: \n" + this.displayScore());}
				String input2 = JOP.in(this.displayBoard() + "\nPlayer2: What direction do you want the board to move type u for up; r for right; d for down; l for left: \n" + this.displayScore());
				String[] options2 = {"u", "r", "d", "l"};
				while(JOP.waitUntil(input2, options2)) {input2 = JOP.in(this.displayBoard() + "\nPlayer2: What direction do you want the board to move type u for up; r for right; d for down; l for left: \n" + this.displayScore());}
				this.p2Move = "" + Integer.valueOf(input) + input2;
			}
			else {
				int input = (int)(Math.random() * this.board[0].length);
				int input2 = (int)(Math.random() * 4);
				String[] options = {"u", "r", "d", "l"};
				this.p2Move = "" + input + options[input2];
			}
		}
		else {
			if(isPlayer) {
				String input = JOP.in(this.displayBoard() + "\nPlayer2: What move do you want to make type a value 0-" + (this.board[0].length - 1) + " for the colunm you want your peice to go in: \n" + this.displayScore());
				String[] options = new String[this.board[0].length]; for(int i = 0; i < this.board[0].length; i++) {options[i] = "" + i;}
				while(JOP.waitUntil(input, options)) {input = JOP.in(this.displayBoard() + "\nPlayer2: What move do you want to make type a value 0-" + (this.board[0].length - 1) + " for the colunm you want your peice to go in: \n" + this.displayScore());}
				this.p2Move = "" + Integer.valueOf(input) + "n";
			}
			else {
				int input = (int)(Math.random() * this.board[0].length);
				this.p2Move = "" + input + "n";
			}
		}
	}
//checks to see who one the game or if a game is even won or tied.
	private int checkWin() {
		int num = 0;
		for(int r = 0; r < this.board.length; r++) {
			for(int c = 0; c < this.board[r].length; c++) {
				if(this.board[r][c].equals(this.blue)) {
					for(int i = 0; i < this.numCross; i++) {
						if(r - i > 0 && c + i < this.board[r - i].length && this.board[r - i][c + i].equals(this.blue)) {
							num++;
						}
						else {
							num = 0;
							break;
						}
					}
					if(num == this.numCross) {return 1;}
					for(int i = 0; i < this.numCross; i++) {
						if(c + i < this.board[r].length && this.board[r][c + i].equals(this.blue)) {
							num++;
						}
						else {
							num = 0;
							break;
						}		
					}
					if(num == this.numCross) {return 1;}
					for(int i = 0; i < this.numCross; i++) {
						if(r + i < this.board.length && c + i < this.board[r + i].length && this.board[r + i][c + i].equals(this.blue)) {
							num++;
						}
						else {
							num = 0;
							break;
						}
					}
					if(num == this.numCross) {return 1;}
					for(int i = 0; i < this.numCross; i++) {
						if(r + i < this.board.length && this.board[r + i][c].equals(this.blue)) {
							num++;
						}
						else {
							num = 0;
							break;
						}
					}
					if(num == this.numCross) {return 1;}
				}
				else if(this.board[r][c].equals(this.red)) {
					for(int i = 0; i < this.numCross; i++) {
						if(r - i < 0 && c + i < this.board[r - i].length && this.board[r - i][c + i].equals(this.red)) {
							num++;
						}
						else {
							num = 0;
							break;
						}
					}
					if(num == this.numCross) {return 2;}
					for(int i = 0; i < this.numCross; i++) {
						if(c + i < this.board[r].length && this.board[r][c + i].equals(this.red)) {
							num++;
						}
						else {
							num = 0;
							break;
						}		
					}
					if(num == this.numCross) {return 2;}
					for(int i = 0; i < this.numCross; i++) {
						if(r + i < this.board.length && c + i < this.board[r + i].length && this.board[r + i][c + i].equals(this.red)) {
							num++;
						}
						else {
							num = 0;
							break;
						}
					}
					if(num == this.numCross) {return 2;}
					for(int i = 0; i < this.numCross; i++) {
						if(r + i < this.board.length && this.board[r + i][c].equals(this.red)) {
							num++;
						}
						else {
							num = 0;
							break;
						}
					}
					if(num == this.numCross) {return 2;}
				}
			}
		}
		return 0;
	}
//updates each move to display on the board next turn.
	private void updateBoard(boolean isPlayer) {
		if(isPlayer) {
			for(int i = this.board.length - 1; i >= 0; i--) {
				if(this.board[i][Integer.valueOf(this.p1Move.substring(0, 1))].equals(this.space)) {
					this.board[i][Integer.valueOf(this.p1Move.substring(0, 1))] = this.blue;
					break;
				}
			}
		}
		else {
			for(int i = this.board.length - 1; i >= 0; i--) {
				if(this.board[i][Integer.valueOf(this.p2Move.substring(0, 1))].equals(this.space)) {
					this.board[i][Integer.valueOf(this.p2Move.substring(0, 1))] = this.red;
					break;
				}
			}
		}
	}
//displays the board to show up on the game.
	private String displayBoard() {
		String s = "";
		for(int r = 0; r < this.board.length; r++) {
			for(int c = 0; c < this.board[r].length; c++) {
				s += this.board[r][c];
			}
			s += "\n";
		}
		return s;
	}
//displays the score of the players.
	private String displayScore() {
		return "Total games : " + this.totalGames + " Player 1 score: " + this.p1Score + " Player 2 score: " + this.p2Score + " Number of rounds: " + this.rounds;
	}
}
