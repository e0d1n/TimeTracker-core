package reports;

import java.util.ArrayList;

public class Taula extends ReportElement {
	
	private int nfiles;
	
	public final int getNfiles() {
		return nfiles;
	}
	
	protected final void setNfiles(final int pNfiles) {
		this.nfiles = pNfiles;
	}
	
	private int ncolumnes;
	
	public final int getNcolumnes() {
		return ncolumnes;
	}
	
	protected final void setNcolumnes(final int pNcolumnes) {
		this.ncolumnes = pNcolumnes;
	}
	
	private boolean firstRowHeader = false;
	
	protected final boolean getFirstRowHeader() {
		return this.firstRowHeader;
	}
	
	protected final void setFirstRowHeader(final boolean setTo) {
		this.firstRowHeader = setTo;
	}
	
	private boolean firstColumnHeader = false;
	
	protected final boolean getFirstColumnHeader() {
		return this.firstColumnHeader;
	}
	
	protected final void setFirstColumnHeader(final boolean setTo) {
		this.firstColumnHeader = setTo;
	}
	
	private ArrayList taula = null;
	
	public final ArrayList getTaula() {
		return taula;
	}
	
	public final void setTaula(final ArrayList pTaula) {
		this.taula = pTaula;
	}
	
	/**
	 * Method that ccrates a new table with pNfiles number of rows 
	 * and pNcolumnas number of columns
	 * 
	
     * @param pNfiles
     * @param pNcolumnes
     **/
    /**
     * @param pNfiles
     * @param pNcolumnes
     */
    @SuppressWarnings("unchecked")
    public Taula(final int pNfiles, final int pNcolumnes) {
		setNfiles(pNfiles);
		setNcolumnes(pNcolumnes);
		ArrayList t = new ArrayList();
		for (int i = 0; i < pNfiles; i++) {
			ArrayList fila = new ArrayList();
			for (int j = 0; j < pNcolumnes; j++) {
				// fila.add(new String());
				fila.add(null);
			}
			t.add(fila);
		}
		setTaula(t);
	}
	
	/**
	 * Adds a new blank row
	 */
	@SuppressWarnings("unchecked")
    public final void afegeixFila() {
		int vNcolumnes = getNcolumnes();
		ArrayList fila = new ArrayList();
		for (int j = 0; j < vNcolumnes; j++) {
			// fila.add(new String());
			fila.add(null);
		}
		getTaula().add(fila);
		setNfiles(getNfiles() + 1);
	}
	
	/**
	 * Adds a new row whith the paramtre llistaStrings
	 * @param llistaStrings
	 */
	@SuppressWarnings("unchecked")
    public final void afegeixFila(final ArrayList llistaStrings) {
		getTaula().add(llistaStrings);
		setNfiles(getNfiles() + 1);
	}
	
	@SuppressWarnings("unchecked")
    public final void setPosicio(final int fila, 
			final int columna, final String str) { // numerem de 1
		                                                        // ... n i no de
		                                                        // 0 ... n-1
		((ArrayList) getTaula().get(fila - 1)).set(columna - 1, str);
	}
	
	public final String getPosicio(final int fila, final int columna) {
		return (String) ((ArrayList) getTaula().get(fila - 1)).get(columna - 1);
	}
	
	@Override
    public final void accept(final ReportVisitor visitor) {
		visitor.visitTable(this);
	}
	
}
