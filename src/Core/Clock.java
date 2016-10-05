package Core;

import java.util.Observable;
import java.util.TimerTask;
import java.util.Date;
import java.util.Timer;


public class Clock extends Observable {
	public Clock(int updatePeriode) {
		this.ct = new ClockTimer();
		this.updatePeriode = updatePeriode;
		Thread th = new Thread(ct);
		th.start();
	}

	private class ClockTimer extends TimerTask {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				tick();
				try {
					Thread.sleep(getUpdatePeriode());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @uml.property name="ct"
	 */
	private ClockTimer ct;

	/**
	 * @uml.property name="timer"
	 */
	private Timer timer;

	/**
	 * @uml.property name="date"
	 */
	private Date date;

	/**
	 * Getter of the property <tt>date</tt>
	 * 
	 * @return Returns the date.
	 * @uml.property name="date"
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Setter of the property <tt>date</tt>
	 * 
	 * @param date
	 *            The date to set.
	 * @uml.property name="date"
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Notify observers with the new date
	 */
	private void tick() {
		this.date = new Date();
		setChanged();
		notifyObservers(this);
	}

	/**
	 * @uml.property  name="updatePeriode"
	 */
	private long updatePeriode;

	/**
	 * Getter of the property <tt>updatePeriode</tt>
	 * @return  Returns the updatePeriode.
	 * @uml.property  name="updatePeriode"
	 */
	public long getUpdatePeriode() {
		return updatePeriode;
	}

	/**
	 * Setter of the property <tt>updatePeriode</tt>
	 * @param updatePeriode  The updatePeriode to set.
	 * @uml.property  name="updatePeriode"
	 */
	public void setUpdatePeriode(long updatePeriode) {
		this.updatePeriode = updatePeriode;
	}
}
