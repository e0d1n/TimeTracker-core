package core;

import java.util.Date;
import java.io.Serializable;


@SuppressWarnings("serial")
public class Periode implements Serializable {
	
	private Date dataInici;
	private Date dataFi;
	private Long duration;
	
	/**
	 * Periode constructor
	 */
	public Periode(Date dataInici, Date dataFi){
		this.dataInici = dataInici;
		this.dataFi = dataFi;
		if (dataInici == null || dataFi == null){
			this.duration = (long) 0;
		}else{
			this.duration = (dataFi.getTime() - dataInici.getTime())/1000;
		}
		
	}
	/**
	 * Getter for dataInici
	 * @return
	 */
	public Date getDataInici(){
		return dataInici;
	}
	
	/**
	 * Setter for dataInici
	 * @param newDataInici
	 */
	public void setDataInici(Date newDataInici){
		this.dataInici = newDataInici;
	}
	
	/**
	 * Getter for dataFi
	 * @return
	 */
	public Date getDataFi(){
		return dataFi;
	}
	
	/**
	 * Setter for dataFi
	 * @param newDataFi
	 */
	public void setDataFi(Date newDataFi){
		this.dataFi = newDataFi;
	}
	
	/**
	 * Getter for duration
	 * @return
	 */
	public Long getDuration(){
		return duration;
	}

	/**
	 * Setter for duration
	 * @param newDuration
	 */
	public void setDuration(Long newDuration){
		this.duration = newDuration;
	}
	
	/**
	 * Increment duration with newIncrement
	 * @param newIncrement
	 */
	public void incrementDuration(Long newIncrement){
		this.duration += newIncrement;
	}
	
	/**
	 * Periode intersection returning a new periode with the intersected
	 * segment time
	 * @param periodeToIntersect
	 * @return
	 */
	public Periode intersect(Periode periodeToIntersect){
		
		// In case that both Periode don't touch or just touch end and start
		if ( this.dataInici.after( periodeToIntersect.getDataFi()) 
				|| this.dataInici.equals(periodeToIntersect.getDataFi())
				|| this.dataFi.before(periodeToIntersect.getDataInici())
				|| this.dataFi.equals(periodeToIntersect.getDataInici())){

			return null;
		}
		
		Date newFi = min(this.dataFi,periodeToIntersect.getDataFi());
		Date newInici = max(this.dataInici, periodeToIntersect.getDataInici());
		//Long newDuration = (newFi.getTime() - newInici.getTime())/1000;
		Periode newPeriode = new Periode(newInici,newFi);
		//newPeriode.setDataInici(newInici);
		//newPeriode.setDataFi(newFi);
		//newPeriode.setDuration(newDuration);

		return newPeriode;
		
	}
	
	private Date max(Date data1, Date data2) {
		if (data1.after(data2)){
			return data1;
		}else{
			return data2;
		}
	}
	
	private Date min(Date data1, Date data2) {
		if (data1.before(data2)){
			return data1;
		}else{
			return data2;
		}
	}
	
	
	public void print(){
		System.out.println("Inici: "+this.dataInici+" Fi: "+this.dataFi+" Duration: "+this.duration);
	}

}
