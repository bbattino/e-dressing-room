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

	private final Rectangle zone; 
	private final long time, period;
	private final TrucKinect trucKinect; 
	private AtomicBoolean started; 
	private ExecutorService executor; 
	private Timer timerObserver; 
	private Timer timerEvent; 
	private final List<IMainDansLaZoneListener> listeners; 
	private final Runnable fireEventTask;
	
	public MainDansLaZoneEventProducer(TrucKinect trucKinect, Rectangle zone, long time, long period) {
		this.zone = zone;
		this.time = time;
		this.period = period;
		this.trucKinect = trucKinect;
		this.started = new AtomicBoolean(false);
		this.listeners = new ArrayList<>();
		this.fireEventTask = new Runnable() {@Override public void run() {fireEvent();}};
	}

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
					if (position != null && zone.contains(position)) { 
						if (!mainDansLaZone) { // si on a d�j� d�tect�, on ne
												// fait rien
							mainDansLaZone = true; // sinon on rel�ve qu'on a
													// d�tecter la main dans la
													// zone
							// on lance la t�che qui attend avant d'envoyer
							// l'�venement
							timerTask = new TimerTask() { // on lance la t�che
															// qui attend que la
															// main est rest�e
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
												// qu'on avait �t� dans la zone
							mainDansLaZone = false; // on rel�ve qu'on est plus
													// dans la zone
							timerTask.cancel(); // on annule la t�che qui attend
						}
					}
				}

			}, period, period);
		}
	}

	/**
	 * Arr�ter l'observation
	 */
	public void stop() {
		if (started.compareAndSet(true, false)) {
			executor.shutdownNow();
			timerEvent.cancel();
			timerObserver.cancel();}}

	public void addListener(IMainDansLaZoneListener listener) {
		Objects.requireNonNull(listener, "Listener can't be null");
		synchronized (listeners) {
			if (!listeners.contains(listener)) {
				listeners.add(listener);}}}
			

	public void removeListener(IMainDansLaZoneListener listener) {
		synchronized (listeners) {
			listeners.remove(listener);}}
	
	private void fireEvent() {
		synchronized (listeners) { // on parcourt tous les listeners et on leur
									// envoie l'�v�nement
			for (IMainDansLaZoneListener listener : listeners) {
				listener.mainDansLaZone();}}}
		
	public interface IMainDansLaZoneListener {	void mainDansLaZone();}}

