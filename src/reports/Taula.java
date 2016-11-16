package reports;

import java.util.ArrayList;

public class Taula extends ReportElement {

	private int nfiles;

	public int getNfiles() {
		return nfiles;
	}

	protected void setNfiles(int nfiles) {
		this.nfiles = nfiles;
	}

	private int ncolumnes;

	public int getNcolumnes() {
		return ncolumnes;
	}

	protected void setNcolumnes(int ncolumnes) {
		this.ncolumnes = ncolumnes;
	}
	
	private boolean firstRowHeader = false;
	
	protected boolean getFirstRowHeader(){
		return this.firstRowHeader;
	}
	
	protected void setFirstRowHeader(boolean setTo){
		this.firstRowHeader = setTo;
	}
	
	private boolean firstColumnHeader = false;
	
	protected boolean getFirstColumnHeader(){
		return this.firstColumnHeader;
	}
	
	protected void setFirstColumnHeader(boolean setTo){
		this.firstColumnHeader = setTo;
	}

	private ArrayList taula = null;

	public ArrayList getTaula() {
		return taula;
	}

	public void setTaula(ArrayList taula) {
		this.taula = taula;
	}
	
	public Taula(int nfiles, int ncolumnes) {
		setNfiles(nfiles);
		setNcolumnes(ncolumnes);
		ArrayList t = new ArrayList();
		for (int i=0 ; i<nfiles ; i++) {
			ArrayList fila = new ArrayList();
			for (int j=0; j<ncolumnes ; j++) {
				// fila.add(new String());
				fila.add(null);
			}
			t.add(fila);
		}
		setTaula(t);
	}
	
	public void afegeixFila() {
		int ncolumnes = getNcolumnes();
		ArrayList fila = new ArrayList();
		for (int j=0; j<ncolumnes ; j++) {
			// fila.add(new String());
			fila.add(null);
		}
		getTaula().add(fila);		
		setNfiles(getNfiles()+1);
	}

	public void afegeixFila(ArrayList llistaStrings) {
		getTaula().add(llistaStrings);		
		setNfiles(getNfiles()+1);
	}
	
	public void setPosicio(int fila, int columna, String str) { // numerem de 1 ... n i no de 0 ... n-1
		((ArrayList) getTaula().get(fila-1)).set(columna-1,str);
	}

	public String getPosicio(int fila, int columna) {
		return (String) ((ArrayList) getTaula().get(fila-1)).get(columna-1);
	}
	
	
	@Override
	public void accept(ReportVisitor visitor) {
		visitor.visitTable(this);
	}

}
