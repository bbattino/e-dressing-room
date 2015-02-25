package fenetres;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import event.MainDansLaZoneEventProducer;
import event.TrucKinect;
import event.MainDansLaZoneEventProducer.IMainDansLaZoneListener;

public abstract class Fenetre extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	/*public void addHandListener(final JButton bouton) {
		addHandListener(bouton,(int)bouton.getLocation().getX(),
				(int)bouton.getLocation().getY(),
				bouton.getHeight(),
				bouton.getWidth());
	}*/

	public void addHandListener(final JButton bouton) {
		final MainDansLaZoneEventProducer eventProducer = new MainDansLaZoneEventProducer(new TrucKinect(bouton),
				 3000, 100);

		eventProducer.addListener(new IMainDansLaZoneListener() {
			public void mainDansLaZone(MainDansLaZoneEventProducer.Type type) {
				switch (type) {
				case ENTER: // on entre 
					// System.out.println("enter");
					bouton.getModel().setArmed(true);
					bouton.getModel().setPressed(true);
					break;
				case HIT: // si la main est restée 3 secondes
					// System.out.println("hit");
					bouton.getModel().setArmed(false);
					bouton.getModel().setPressed(false);
					bouton.doClick(); // je clique le bouton
					break;
				case EXIT: // si la main sort 
					// System.out.println("exit");
					bouton.getModel().setArmed(false);
					bouton.getModel().setPressed(false);
					break;
				}
			}
		});

		eventProducer.start();

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				eventProducer.stop();
			}
		});

	}

	/*public void addHandListener(final JButton bouton, int x) {
		addHandListener(bouton, 0, 0, 1366, 800);
	}*/

}
