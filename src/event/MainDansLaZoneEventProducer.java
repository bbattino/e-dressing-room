package event;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainDansLaZoneEventProducer {

	private final Rectangle zone; // la zone à observer
	private final long time; // la durée d'observation avant d'envoyer
								// l'évenement en ms
	private final long period; // la période d'observation (on teste la position
								// toutes les period ms)
	private final TrucKinect trucKinect; // la classe avec la méthode
											// getPosition()
	private AtomicBoolean started; // indique que l'observation est démarrée
	private ExecutorService executor; // pour déléguer l'exécution de l'envoi de
										// l'évenement aux écouteurs (pour
										// éviter de bloquer timer)

	private Timer timerObserver; // le timer qui sert à observer la position de
									// la main
	private Timer timerEvent; // le timer qui permet d'envoyer l'évenement au
								// bout du temps time

	private final List<IMainDansLaZoneListener> listeners; // la liste des
															// écouteurs

	private final Runnable fireEventTask; // la tâche qui envoie l'évenement

	/**
	 * 
	 * @param zone
	 *            la zone à tester
	 * @param time
	 * @param period
	 */
	public MainDansLaZoneEventProducer(TrucKinect trucKinect, Rectangle zone, long time, long period) {
		this.zone = zone;
		this.time = time;
		this.period = period;
		this.trucKinect = trucKinect;
		this.started = new AtomicBoolean(false);
		this.listeners = new ArrayList<>();
		this.fireEventTask = new Runnable() {
			
			@Override
			public void run() {
				fireEvent();				
			}
		};
	}

	/**
	 * Démarrer l'observation
	 */
	public void start() {
		if (started.compareAndSet(false, true)) {
			executor = Executors.newSingleThreadExecutor();
			timerObserver = new Timer();
			timerEvent = new Timer();
			timerObserver.schedule(new TimerTask() {

				private boolean mainDansLaZone;
				private TimerTask timerTask;

				@Override
				public void run() {
					final Point position = trucKinect.getPosition();
					if (position != null && zone.contains(position)) { // test
																		// si la
																		// main
																		// est
																		// dans
																		// la
																		// zone
						if (!mainDansLaZone) { // si on a déjà détecté, on ne
												// fait rien
							mainDansLaZone = true; // sinon on relève qu'on a
													// détecter la main dans la
													// zone
							// on lance la tâche qui attend avant d'envoyer
							// l'évenement
							timerTask = new TimerTask() { // on lance la tâche
															// qui attend que la
															// main est restée
															// suffisemment
															// longtemps

								@Override
								public void run() {
									fireEvent();
								}

								private void fireEvent() {
									executor.execute(fireEventTask);
								}

							};
							timerEvent.schedule(timerTask, time);
						}
					} else {
						if (mainDansLaZone) { // si on n'est pas dans la zone et
												// qu'on avait été dans la zone
							mainDansLaZone = false; // on relève qu'on est plus
													// dans la zone
							timerTask.cancel(); // on annule la tâche qui attend
						}
					}
				}

			}, period, period);
		}
	}

	/**
	 * Arrêter l'observation
	 */
	public void stop() {
		if (started.compareAndSet(true, false)) {
			executor.shutdownNow();
			timerEvent.cancel();
			timerObserver.cancel();
		}
	}

	/**
	 * Pour ajouter un écouteur
	 * 
	 * @param listener
	 */
	public void addListener(IMainDansLaZoneListener listener) {
		Objects.requireNonNull(listener, "Listener can't be null");
		synchronized (listeners) {
			if (!listeners.contains(listener)) {
				listeners.add(listener);
			}
		}
	}

	/**
	 * Pour enlever un écouteur
	 * 
	 * @param listener
	 */
	public void removeListener(IMainDansLaZoneListener listener) {
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}

	private void fireEvent() {
		synchronized (listeners) { // on parcourt tous les listeners et on leur
									// envoie l'événement
			for (IMainDansLaZoneListener listener : listeners) {
				listener.mainDansLaZone();
			}
		}
	}

	public interface IMainDansLaZoneListener {
		void mainDansLaZone();
	}

}