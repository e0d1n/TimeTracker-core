package core;

public interface Printable {

	/**
	 * Interface of the visitable pattern
	 * Needed to activities to be visited with a visitor
	 * accept: Method to accept a printer 
	 * @param printer
	 */
	void accept(Printer printer);
	
}
