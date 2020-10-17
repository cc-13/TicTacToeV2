package game.model;

import game.common.GameModelChangeObservable;

public interface GameModel extends GameModelChangeObservable {
	public final static int NO_WINNIER = -1;
	public final static int NOT_MARKED = -1;
	
	public boolean hasWinner();

	public int getWinner();

	public int getPlayerCount();

	public boolean isDraw();

	public boolean isFull();

	public int getCurrentPlayer();

	public int[][] getBoard();

	public int getNumOfRows();

	public int getNumOfColums();

	public boolean setMark(int x, int y, int playerId);
}
