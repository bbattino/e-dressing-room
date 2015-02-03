package event;

import java.awt.Point;

public interface IGestionEvenement {
	
	public Point getPositionMain();
	public boolean mainSurBoutton();
	public void launchTimer();
	public void stopTimer();
	public void action();

}
