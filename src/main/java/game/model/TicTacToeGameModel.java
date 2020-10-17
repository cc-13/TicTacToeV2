package game.model;

import java.io.Serializable;
import java.util.LinkedList;

import game.common.GameModelChangeObserver;

public class TicTacToeGameModel implements GameModel, Serializable {

	private static final long serialVersionUID = 1L;

	private int playerCount;
	private int stepCount;
	private int numOfRow;
	private int numOfColumn;
	private int winner;
	private int[][] board;

	transient GameRule rule;

	LinkedList<GameModelChangeObserver> observers = new LinkedList<GameModelChangeObserver>();

	public TicTacToeGameModel(int numOfRow, int numOfColumn, int playerCount,
			GameRule rule) {
		this.numOfRow = numOfRow;
		this.numOfColumn = numOfColumn;
		this.board = new int[numOfRow][numOfColumn];
		this.playerCount = playerCount;
		this.stepCount = 0;
		this.rule = rule;
		this.winner = NO_WINNIER;
		for (int i = 0; i < numOfRow; i++)
			for (int j = 0; j < numOfColumn; j++) {
				board[i][j] = NOT_MARKED;
			}
	}

	public boolean hasWinner() {
		return !(winner == NO_WINNIER);
	}

	public int getWinner() {
		return winner;
	}

	public int getPlayerCount() {
		return playerCount;
	}

	public boolean isDraw() {
		return isFull() && (winner == NO_WINNIER);
	}

	public boolean isFull() {
		return stepCount == numOfRow * numOfColumn;
	}

	public int getCurrentPlayer() {
		return stepCount % playerCount;
	}

	
	public int[][] getBoard() {
		int[][] copyOfBoard = new int[numOfRow][numOfColumn];
		for (int i = 0; i < numOfRow; i++)
			for (int j = 0; j < numOfColumn; j++)
				copyOfBoard[i][j] = board[i][j];
		return copyOfBoard;
	}

	private boolean isAvailable(int x, int y) {
		if (x < 0 || x > numOfRow || y < 0 || y > numOfColumn
				|| board[x][y] != NOT_MARKED) {
			return false;
		}
		return true;
	}

	private void applyChanges() {
		if (rule.wins(this)) {
			winner = stepCount % playerCount;
		}
		stepCount++;
	}

	public boolean setMark(int x, int y, int playerId) {
		if (stepCount % playerCount == playerId && isAvailable(x, y)) {
			// right, this player's turn
			board[x][y] = playerId;
			applyChanges();
			notifyObserver();
			return true;
		}
		return false;
	}

	public int getNumOfRows() {
		return numOfRow;
	}

	public int getNumOfColums() {
		return numOfColumn;
	}

	public void addObserver(GameModelChangeObserver observer) {
		observers.add(observer);

	}

	public void removeObserver(GameModelChangeObserver observer) {
		observers.remove(observer);
	}

	public void notifyObserver() {
		for (GameModelChangeObserver observer : observers) {
			observer.update(this);
		}
	}

}
