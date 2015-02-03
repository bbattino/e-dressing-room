package event;

import java.awt.Point;

import javax.swing.JButton;

public interface IGestionEvenement {
	
	public Point getPositionMain();
	public JButton mainSurBoutton();
	public void launchTimer();
	public void stopTimer();
	public void action();

}
