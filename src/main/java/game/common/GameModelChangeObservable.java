package game.common;

public interface GameModelChangeObservable {
	public void addObserver(
			GameModelChangeObserver observer);

	public void removeObserver(
			GameModelChangeObserver observer);

	public void notifyObserver();
}
