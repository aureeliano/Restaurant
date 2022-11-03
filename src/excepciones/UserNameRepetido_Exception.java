package excepciones;

public class UserNameRepetido_Exception extends Exception {
	private String userName;

	public UserNameRepetido_Exception(String userName) {
		super();
		this.userName = userName;
	}

	public String getUserName()
	{
		return userName;
	}

	
}
