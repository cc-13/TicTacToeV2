package game.controller;

import game.model.GameModel;

public class TicTacToeController implements GameController {

	private GameModel model;

	public TicTacToeController(GameModel model) {
		this.model = model;
		this.model.addObserver(this);
	}

	public boolean setMark(int x, int y, int playerId) {
		return model.setMark(x, y, playerId);
	}

	public void update(GameModel model) {
		this.model = model;
	}
}
