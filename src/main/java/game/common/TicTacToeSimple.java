package game.common;

import game.controller.GameController;
import game.controller.TicTacToeController;
import game.model.GameModel;
import game.model.TicTacToeGameModel;
import game.model.TicTacToeGameRule;
import game.ui.GameConsoleUI;
import game.ui.GameUserInterface;

public class TicTacToeSimple {

	public static void main(String[] args) {
		GameModel model = new TicTacToeGameModel(3, 3, 2, new TicTacToeGameRule());
		GameController controller = new TicTacToeController(model);
		GameUserInterface ui = new GameConsoleUI(model, controller);
		ui.start();
	}

}
