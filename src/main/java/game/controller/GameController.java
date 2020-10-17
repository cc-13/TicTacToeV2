package game.controller;

import game.common.GameModelChangeObserver;

public interface GameController extends GameModelChangeObserver {
	public boolean setMark(int x, int y, int playerId);
}
