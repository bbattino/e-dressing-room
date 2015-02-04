package event;

import event.MainDansLaZoneEventProducer.IMainDansLaZoneListener;

public class Listener implements IMainDansLaZoneListener{

	@Override
	public void mainDansLaZone() {
		// TODO Auto-generated method stub

		 
		// ici on fait le code qu'on veut faire quand la main entre dans la zone
		System.out.println("Main dans la zone pendant 3 seconde(s)%");

		MainDansLaZoneTest.panel.setMainDansLaZone(true);
		new Thread(){
			public void run() {
				try {
					Thread.sleep(1000);
					MainDansLaZoneTest.panel.setMainDansLaZone(false);
				} catch (InterruptedException e) {
				}
			}
		}.start();
	}

}
