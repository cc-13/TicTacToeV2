package game.common;

import game.model.GameModel;

public interface GameModelChangeObserver {
	public void update(GameModel model);
}
