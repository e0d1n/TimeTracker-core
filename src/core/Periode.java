package core;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial")
public class Periode implements Serializable {
	private static final int MIL = 1000;
	private static final int SESENTA = 60;
	private Date dataInici;
	private Date dataFi;
	private Long duration;
	
	/**
	 * Periode constructor
	 */
	public Periode(final Date pDataInici, final Date pDataFi) {
		assert pDataFi != null;
		assert pDataInici != null;
		this.dataInici = pDataInici;
		this.dataFi = pDataFi;
		if (dataInici == null || dataFi == null) {
			this.duration = (long) 0;
		} else {
			this.duration = (dataFi.getTime() - dataInici.getTime());
		}
		
	}
	
	public final String getDateAsStringFormated(final Date dateToFormat) {
		assert dateToFormat != null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateToFormat);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		return String.format(("%02d/%02d/%d, %d:%02d:%02dh"), day, month, year,
		        hour, minute, second);
	}
	
	/**
	 * Getter for dataInici
	 * 
	 * @return
	 */
	public final Date getDataInici() {
		return dataInici;
	}
	
	public final String getDataIniciAsStringFormated() {
		return getDateAsStringFormated(dataInici);
	}
	
	/**
	 * Setter for dataInici
	 * 
	 * @param newDataInici
	 */
	public final void setDataInici(final Date newDataInici) {
		assert newDataInici != null;
		this.dataInici = newDataInici;
	}
	
	/**
	 * Getter for dataFi
	 * 
	 * @return
	 */
	public final Date getDataFi() {
		return dataFi;
	}
	
	public final String getDataFiAsStringFormated() {
		return getDateAsStringFormated(dataFi);
	}
	
	/**
	 * Setter for dataFi
	 * 
	 * @param newDataFi
	 */
	public final void setDataFi(final Date newDataFi) {
		assert newDataFi != null;
		this.dataFi = newDataFi;
	}
	
	/**
	 * Getter for duration as formated String
	 */
	public final String getDurationAsStringFormated() {
		
		int seconds = (int) ((this.duration / MIL) % SESENTA);
		int minutes = (int) ((this.duration / (MIL * SESENTA)) % SESENTA);
		int hours = (int) ((this.duration / (MIL * SESENTA * SESENTA)));
		
		return String.format(("%d:%02d:%02d"), hours, minutes, seconds);
	}
	
	/**
	 * Setter for duration
	 * 
	 * @param newDuration
	 */
	public final void setDuration(final Long newDuration) {
		this.duration = newDuration;
	}
	
	/**
	 * Increment duration with newIncrement
	 * 
	 * @param newIncrement
	 */
	public final void incrementDuration(final Long newIncrement) {
		this.duration += newIncrement;
	}
	
	/**
	 * Periode intersection returning a new periode with the intersected segment
	 * time
	 * 
	 * @param periodeToIntersect
	 * @return
	 */
	public final Periode intersect(final Periode periodeToIntersect) {
		assert periodeToIntersect != null;
		// In case that both Periode don't touch or just touch end and start
		if (this.dataInici.after(periodeToIntersect.getDataFi())
		        || this.dataInici.equals(periodeToIntersect.getDataFi())
		        || this.dataFi.before(periodeToIntersect.getDataInici())
		        || this.dataFi.equals(periodeToIntersect.getDataInici())) {
			
			return null;
		}
		
		Date newFi = min(this.dataFi, periodeToIntersect.getDataFi());
		Date newInici = max(this.dataInici, periodeToIntersect.getDataInici());
		// Long newDuration = (newFi.getTime() - newInici.getTime())/1000;
		Periode newPeriode = new Periode(newInici, newFi);
		// newPeriode.setDataInici(newInici);
		// newPeriode.setDataFi(newFi);
		// newPeriode.setDuration(newDuration);
		
		return newPeriode;
		
	}
	
	private Date max(final Date data1, final Date data2) {
		assert data1 != null;
		assert data2 != null;
		if (data1.after(data2)) {
			return data1;
		} else {
			return data2;
		}
	}
	
	private Date min(final Date data1, final Date data2) {
		if (data1.before(data2)) {
			assert data1 != null;
			assert data2 != null;
			return data1;
		} else {
			return data2;
		}
	}
	
	public final void print() {
		System.out.println("Inici: " + this.dataInici + " Fi: " + this.dataFi
		        + " Duration: " + this.duration);
	}
	
}
