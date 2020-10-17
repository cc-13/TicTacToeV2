package game.model;

public interface GameRule {
	/**
	 * Return if the current player win the game;
	 */
	public boolean wins(GameModel model);
}
