package game.model;

public class TicTacToeGameRule implements GameRule {

	public boolean wins(GameModel model) {
		int[][] board = model.getBoard();
		int size = model.getNumOfColums();
		int currentPlayerId = model.getCurrentPlayer();

		// row equals
		for (int i = 0; i < size; i++) {
			boolean rowEqual = true;
			for (int j = 0; j < size; j++) {
				if (board[i][j] != currentPlayerId) {
					rowEqual = false;
					break;
				}
			}
			if (rowEqual) {
				return true;
			}
		}
		// column equals
		for (int j = 0; j < size; j++) {
			boolean columnEqual = true;
			for (int i = 0; i < size; i++) {
				if (board[i][j] != currentPlayerId) {
					columnEqual = false;
					break;
				}
			}
			if (columnEqual) {
				return true;
			}
		}
		// cross equal
		boolean crossEqual1 = true;
		boolean crossEqual2 = true;
		for (int i = 0; i < size; i++) {
			if (board[i][i] != currentPlayerId) {
				crossEqual1 = false;
			}
			if (board[i][size - 1 - i] != currentPlayerId) {
				crossEqual2 = false;
			}
		}
		if (crossEqual1 || crossEqual2) {
			return true;
		}
		return false;
	}
}
