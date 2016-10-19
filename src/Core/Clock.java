package Core;
import java.util.Observable;
import java.util.TimerTask;
import java.util.Timer;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public class Clock extends Observable {
	
	/** Declaration of the logger
	 *  used for debugging purposes
	 */
	static Logger logger = LoggerFactory.getLogger("Clock");

	
	/**
	 * ClockTimer: Needed to run the clock
	 * @uml.property name="ct"
	 */
	private ClockTimer ct;

	/**
	 * date: Used to store the actual date
	 * @uml.property name="date"
	 */
	private Date date;

	/**
	 * Used to store the period update of the clock
	 * @uml.property  name="updatePeriod"
	 */
	private long updatePeriod;

	/**
	 * Getter of the property <tt>updatePeriod</tt>
	 * @return  Returns the updatePeriod.
	 * @uml.property  name="updatePeriod"
	 */
	public long getUpdatePeriod() {
		return updatePeriod;
	}

	/**
	 * Getter of the property <tt>date</tt>
	 * @return Returns the date.
	 * @uml.property name="date"
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * tick: Tick of the clock
	 * Update the attribute date and inform something has change
	 * Notify observers with the new date
	 */
	private void tick() {

		this.date = new Date();
		setChanged();
		notifyObservers(this);
	}

	/**
	 * The clock it's a observable object used to control the time
	 * Constructor clock: Creates a clock 
	 * We initialize the clock creating a new clock timer, setting the updatePeriod(that we use to
	 * notify the observers) and putting false to the attribute go that we use to control the
	 * start and the stop of the clock 
	 * @param updatePeriode: The update of the clock
	 */
	public Clock(int updatePeriod) {

		this.ct = new ClockTimer();
		this.updatePeriod = updatePeriod;
		this.go = false;	
	}

	private class ClockTimer extends TimerTask {
		/**
		 * @Override run: How the clocks runs
		 * When we call start at the clock, it does tick and sleep until
		 * we not call stop
		 */
		@Override
		public void run() {
			while (go) {
				tick();
				try {
					Thread.sleep(getUpdatePeriod());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @uml.property  name="go"
	 */
	private Boolean go;

	/**	 
	 * start: Initializes the clock
	 * Put go attribute to true to starts the clock
	 * @uml.property  name="go"
	 */
	public void start() {
		
		Thread th = new Thread(ct);
		th.start();
		this.go = true;
		logger.debug("Clock started");
	}

	/**
	 * stop: Put go attribute to false to stop the clock
	 * @uml.property  name="go"
	 */
	public void stop() {

		this.go = false;
		logger.debug("Clock stoped");
	}

}
