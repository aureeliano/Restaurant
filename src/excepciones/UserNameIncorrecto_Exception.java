package excepciones;

public class UserNameIncorrecto_Exception extends Exception{
	private String userName;

	public UserNameIncorrecto_Exception(String userName) {
		super();
		this.userName = userName;
	}

	public String getUserName()
	{
		return userName;
	}
	
	
}