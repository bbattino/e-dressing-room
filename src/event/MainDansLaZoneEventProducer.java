package event;
 
import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
 
public class MainDansLaZoneEventProducer {
 
	private final long time, period;
	private final TrucKinect trucKinect;
	private AtomicBoolean started;
	private ExecutorService executor;
	private Timer timerObserver;
	private Timer timerEvent;
	private final List<IMainDansLaZoneListener> listeners;
	private Robot robot;
 
	public enum Type {
		ENTER, HIT, EXIT
	}
 
	private Runnable fireHitEventTask = new Runnable() {
		public void run() {
			fireEvent(Type.HIT);
		}
	};
	private Runnable fireEnterEventTask = new Runnable() {
 
		public void run() {
			fireEvent(Type.ENTER);
 
		}
	};
 
	private Runnable fireExitEventTask = new Runnable() {
 
		public void run() {
			fireEvent(Type.EXIT);
 
		}
	};
 
	public MainDansLaZoneEventProducer(TrucKinect trucKinect,
			long time, long period) {
		this.time = time;
		this.period = period;
		this.trucKinect = trucKinect;
		this.started = new AtomicBoolean(false);
		this.listeners = new ArrayList<IMainDansLaZoneListener>();
		try {robot=new Robot();} catch (AWTException e) {e.printStackTrace();}
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
					Point p = trucKinect.getPositionSimu(); // A modifier par la vraie kinect !!!!
					if(p!=null)
						robot.mouseMove((int)p.getX(), (int)p.getY());
					// Le code ci dessous ne devra pas être modifié lors de l'utilisation de la vraie kinect
					final Point position = trucKinect.getPosition();
					if (position != null) {
						if (!mainDansLaZone) { 
							mainDansLaZone = true; 
							executor.execute(fireEnterEventTask);
							timerTask = new TimerTask() { 
 
								@Override
								public void run() {
									fireEvent();
								}
 
								private void fireEvent() {
									if (mainDansLaZone)
										executor.execute(fireHitEventTask);
								}
							};
							timerEvent.schedule(timerTask, time);
						}
					} else {
						if (mainDansLaZone) { 
							mainDansLaZone = false; 
													
							timerTask.cancel(); 
							executor.execute(fireExitEventTask);}
					}
				}
			}, period, period);
		}
	}
 
	
	public void stop() {
		if (started.compareAndSet(true, false)) {
			executor.shutdownNow();
			timerEvent.cancel();
			timerObserver.cancel();
		}
	}
 
	public void addListener(IMainDansLaZoneListener listener) {
		Objects.requireNonNull(listener, "Listener can't be null");
		synchronized (listeners) {
			if (!listeners.contains(listener)) {
				listeners.add(listener);
			}
		}
	}
 
	public void removeListener(IMainDansLaZoneListener listener) {
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}
 
	private void fireEvent(Type type) {
		synchronized (listeners) { // on parcourt tous les listeners et on leur
									// envoie l'événement
			for (IMainDansLaZoneListener listener : listeners) {
				listener.mainDansLaZone(type);
			}
		}
	}
 
	public interface IMainDansLaZoneListener {
		void mainDansLaZone(Type type);
	}
 
}