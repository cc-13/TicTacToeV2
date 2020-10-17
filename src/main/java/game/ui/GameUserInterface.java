package game.ui;

import game.common.GameModelChangeObserver;

public interface GameUserInterface extends GameModelChangeObserver {
	public void start();

	public void end();
}
