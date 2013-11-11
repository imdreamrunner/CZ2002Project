package utils;

import views.GeneralInterface;

public abstract class Controller {
	protected GeneralInterface gi;
	public Controller() {
		gi = new GeneralInterface();
	}
	public abstract void run();
}
