package excepciones;

public class OperarioNoActivo_Exception extends Exception
{
	private String username;

	public OperarioNoActivo_Exception(String username)
	{
		super();
		this.username = username;
	}

	public String getUsername()
	{
		return username;
	}

}
