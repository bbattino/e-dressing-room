package event;

import event.MainDansLaZoneEventProducer.IMainDansLaZoneListener;

public class Listener implements IMainDansLaZoneListener{

	public void mainDansLaZone() {
		 
		// ici on fait le code qu'on veut faire quand la main entre dans la zone
		System.out.println("Main dans la zone pendant pdt 1 sec");

		MainDansLaZoneTest.button.setMainDansLaZone(true);
		new Thread(){
			public void run() {
				try {
					Thread.sleep(1000);
					MainDansLaZoneTest.button.setMainDansLaZone(false);
				} catch (InterruptedException e) {}
			}
		}.start();
	}

}
