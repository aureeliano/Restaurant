package excepciones;

public class IDRepetido_Exception extends Exception {
	
	private int id;

	public IDRepetido_Exception(int id) {
		super();
		this.id = id;
	}
	
	

}
