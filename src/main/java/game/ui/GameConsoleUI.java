package game.ui;

import java.util.Scanner;

import game.controller.GameController;
import game.model.GameModel;

public class GameConsoleUI implements GameUserInterface {

	private final static String POSITION_PATTERN = "[%d,%d]";
	private final static String[] PLAYER_MARK = { "  X  ", "  O  " };
	private final static String INPUT_PATTERN = "Input invalid. Please re-enter [1-%d]:";
	private final static String REENTER_POSITION = "The position has already been marked. Please re-enter.";
	private final static String SPLIT_ROW = " ";
	private final static String SPLIT_COL = "|";
	
	private final static String WAIT_FOR_INPUT = " Wait for Player-%d's input.";
	private final static String ROW = " Row:";
	private final static String COLUMN = " Column:";
	private final static String WELCOME = "WELCOME to the TIC-TAC-TOE";
	private final static String BYE = "BYE";
	private final static String RESULT_WIN = "Player-%d WIN!";
	private final static String RESULT_DRAW = "DRAW";

	private Scanner scan;
	private GameModel model;
	private GameController controller;

	public GameConsoleUI(GameModel model, GameController controller) {
		scan = new Scanner(System.in);
		this.model = model;
		this.model.addObserver(this);
		this.controller = controller;
	}

	private void printLine(String str) {
		System.out.println(str);
	}

	private void print(String str) {
		System.out.print(str);
	}

	public void displayBoard() {
		if (model != null) {
			int[][] board = model.getBoard();
			int size = model.getNumOfRows();
			for (int i = 0; i < size; i++) {
				printLine("");
				printLine(SPLIT_ROW);
				for (int j = 0; j < size; j++) {
					if (board[i][j] == GameModel.NOT_MARKED) {
						print(SPLIT_COL);
						print(String.format(POSITION_PATTERN, i + 1, j + 1));
						print(SPLIT_COL);
					} else {
						print(SPLIT_COL);
						print(PLAYER_MARK[board[i][j] % PLAYER_MARK.length]);
						print(SPLIT_COL);
					}
				}
			}
			printLine("");
			printLine(SPLIT_ROW);
		}
	}

	public void getPlayerInput() {
		if (model != null) {
			do {
				int currentPlayer = model.getCurrentPlayer();
				int maxRow = model.getNumOfRows();
				int maxColumn = model.getNumOfColums();

				printLine(String.format(WAIT_FOR_INPUT, currentPlayer + 1));
				print(ROW);
				int row = getInputInteger(maxRow) - 1;
				print(COLUMN);
				int column = getInputInteger(maxColumn) - 1;
				boolean success = controller.setMark(row, column,
						currentPlayer);
				if (!success) {
					System.out.print(REENTER_POSITION);
				} else {
					return;
				}
			} while (true);
		}
	}

	private int getInputInteger(int limit) {
		do {
			try {
				String input = scan.next();
				int value = Integer.parseInt(input);
				if (value <= 0 || value > limit) {
					System.out.println(String.format(INPUT_PATTERN, limit));
				} else {
					return value;
				}
			} catch (Exception e) {
				System.out.println(String.format(INPUT_PATTERN, limit));
			}
		} while (true);
	}

	public void start() {
		printLine(WELCOME);
		displayBoard();
		getPlayerInput();
	}

	public void end() {
		printLine("");
		printLine(BYE);
		if (scan != null) {
			scan.close();
		}
	}

	public void update(GameModel model) {
		this.model = model;
		if (model.hasWinner()) {
			printLine(String.format(RESULT_WIN, model.getWinner()+1));
			end();
		}else if(model.isDraw()) {
			printLine(RESULT_DRAW);
			end();
		}else {
			 displayBoard();
			 getPlayerInput();
		}
	}
}
