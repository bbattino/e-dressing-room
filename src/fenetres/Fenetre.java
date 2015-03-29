package fenetres;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import pactInitial.Main;
import event.MainDansLaZoneEventProducer;
import event.TrucKinect;
import event.MainDansLaZoneEventProducer.IMainDansLaZoneListener;

public abstract class Fenetre extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	public void addHandListener(final JButton bouton) {
		if(Main.handListenerActivated){
		final MainDansLaZoneEventProducer eventProducer = new MainDansLaZoneEventProducer(new TrucKinect(bouton), 1500,
				100); // Premier int : temps à rester dans la zone pour activer,
						// 2ème : frequence de reactualisation de la position

		eventProducer.addListener(new IMainDansLaZoneListener() {
			public void mainDansLaZone(MainDansLaZoneEventProducer.Type type) {
				switch (type) {
				case ENTER: // on entre
					bouton.getModel().setArmed(true);
					bouton.getModel().setPressed(true);
					break;
				case HIT: // si la main est restée 3 secondes
					bouton.getModel().setArmed(false);
					bouton.getModel().setPressed(false);
					bouton.doClick();
					break;
				case EXIT: // si la main sort
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

	}

	public abstract void actionBouton(int numeroBouton);
	public abstract void refreshIndicateurVocal();

}
