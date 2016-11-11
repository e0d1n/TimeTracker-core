package core;
import java.util.Observable;
import java.util.TimerTask;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Clock extends Observable {
    
    static Logger logger = LoggerFactory.getLogger("Clock");
    
    /**
     * @uml.property name="ct"
     */
    private ClockTimer ct;

    /**
     * @uml.property name="date"
     */
    private Date date;

    /**
     * @uml.property  name="updatePeriod"
     */
    private long updatePeriod;

    /**
     * Getter of the property <tt>updatePeriod</tt>
     * @uml.property  name="updatePeriod"
     */
    public final long getUpdatePeriod() {
        return updatePeriod;
    }

    /**
     * Getter of the property <tt>date</tt>
     * @uml.property name="date"
     */
    public final Date getDate() {
        return date;
    }

    private void tick() {
        this.date = new Date();
        setChanged();
        //Notify observers with the new date
        notifyObservers(this);
    }

    /**
     * The clock it's a observable object used to control the time
     * We initialize the clock creating a new clock timer, setting the
     * updatePeriod(that we use to notify the observers) and controlling 
     * the start and the stop of the clock 
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
     * Starts the clock
     * @uml.property  name="go"
     */
    public final void start() {
        Thread th = new Thread(ct);
        th.start();
        this.go = true;
        logger.debug("Clock started");
    }

    /**
     * Stops the clock
     * @uml.property  name="go"
     */
    public final void stop() {
        this.go = false;
        logger.debug("Clock stoped");
    }

}
