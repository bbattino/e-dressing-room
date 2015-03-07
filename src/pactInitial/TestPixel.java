package pactInitial;

import java.awt.Frame;
import java.awt.Point;

import javax.swing.JFrame;

public class TestPixel extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	
	public TestPixel(){
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	public static void main(String[] args){
		TestPixel t =new TestPixel();
		int x=0;
		int y=0;
		while(true){
			Point p = t.getMousePosition();
			if(p!=null){
			if(p.x>x) {x=p.x;System.out.println("x="+x);}
			if(p.y>y) {y=p.y;System.out.println("y="+y);}}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
}

