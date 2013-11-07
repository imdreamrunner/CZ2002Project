package utils;

import cui.GeneralInterface;

public abstract class Controller {
	protected GeneralInterface gi;
	public Controller() {
		gi = new GeneralInterface();
	}
	public abstract void run();
}
