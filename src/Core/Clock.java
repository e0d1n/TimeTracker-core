package core;

import java.util.Date;
import java.util.Observable;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Clock extends Observable {
	
	private static Logger logger = LoggerFactory.getLogger("Clock");
	
	/**
	 * @uml.property name="ct"
	 */
	private ClockTimer ct;
	
	/**
	 * @uml.property name="date"
	 */
	private Date date;
	
	/**
	 * @uml.property name="updatePeriod"
	 */
	private long updatePeriod;
	
	/**
	 * Getter of the property <tt>updatePeriod</tt>
	 * 
	 * @uml.property name="updatePeriod"
	 */
	public final long getUpdatePeriod() {
		assert updatePeriod > 0;
		return updatePeriod;
	}
	
	/**
	 * Getter of the property <tt>date</tt>
	 * 
	 * @uml.property name="date"
	 */
	public final Date getDate() {
		return date;
	}
	
	private boolean invariant(){
		
		if (this.go == null){
			return false;
		}
		
		if (this.updatePeriod <= 0){
			return false;
		}
		
		if (this.ct == null){
			return false;
		}
		
		return true;
	}
	
	private void tick() {
		this.date = new Date();
		assert this.date != null;
		setChanged();
		// Notify observers with the new date
		notifyObservers(this);
		assert invariant();
	}
	
	/**
	 * The clock it's a observable object used to control the time We initialize
	 * the clock creating a new clock timer, setting the updatePeriod(that we
	 * use to notify the observers) and controlling the start and the stop of
	 * the clock
	 */
	public Clock(final int pUpdatePeriod) {
		assert pUpdatePeriod > 0: "Update periode should be bigger than zero";
		this.ct = new ClockTimer();
		this.updatePeriod = pUpdatePeriod;
		this.go = false;
		assert invariant();
	}
	
	private class ClockTimer extends TimerTask {
		/**
		 * @Override run: How the clocks runs When we call start at the clock,
		 *           it does tick and sleep until we not call stop
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
				assert invariant();
			}
		}
	}
	
	/**
	 * @uml.property name="go"
	 */
	private Boolean go;
	
	/**
	 * Starts the clock
	 * 
	 * @uml.property name="go"
	 */
	public final void start() {
		Thread th = new Thread(ct);
		th.start();
		this.go = true;
		logger.debug("Clock started");
		assert invariant();
	}
	
	/**
	 * Stops the clock
	 * 
	 * @uml.property name="go"
	 */
	public final void stop() {
		this.go = false;
		logger.debug("Clock stoped");
		assert invariant();
	}
	
}
