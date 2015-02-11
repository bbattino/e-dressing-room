package fenetres;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import event.MainDansLaZoneEventProducer;
import event.TrucKinect;
import event.MainDansLaZoneEventProducer.IMainDansLaZoneListener;

public abstract class Fenetre extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	/*public Fenetre(){
		setUndecorated(true);
		setVisible(true); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);


	}*/

	public void addHandListener(final JButton bouton) {
		final MainDansLaZoneEventProducer eventProducer = new MainDansLaZoneEventProducer(new TrucKinect(bouton),
				new Rectangle(bouton.getLocationOnScreen(), bouton.getSize()), 1000, 100); 
																							

		// alternative 1 : classe anonyme
		eventProducer.addListener(new IMainDansLaZoneListener() {
			public void mainDansLaZone(MainDansLaZoneEventProducer.Type type) {
				switch (type) {
				case ENTER: // on entre la zone
					//System.out.println("enter");
					bouton.getModel().setArmed(true);
					bouton.getModel().setPressed(true);
					break;
				case HIT: // si la main est restée 3 secondes
					//System.out.println("hit");
					bouton.getModel().setArmed(false);
					bouton.getModel().setPressed(false);
					bouton.doClick(); // je clique le bouton
					break;
				case EXIT: // si la main sort (attention avec ce que j'ai fait,
							// elle sort toujours, même si elle est restée 3
							// secondes...
					//System.out.println("exit");
					bouton.getModel().setArmed(false);
					bouton.getModel().setPressed(false);
					break;
				}
			}
		});

		// ou alors alternative 2 : classe concrete Listener
		// eventProducer.addListener(new Listener(bouton));

		eventProducer.start();

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				eventProducer.stop();
			}
		});

	}
}
