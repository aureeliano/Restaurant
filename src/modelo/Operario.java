package modelo;

public class Operario
{
    private String NyA;
    private String username;
    private String password;
    private boolean activo = true;
    
	public Operario(String NyA, String username, String password)
	{
		super();
		this.NyA = NyA;
		this.username = username;
		this.password = password;
	}

	public String getNyA()
	{
		return NyA;
	}

	public void setNyA(String nyA)
	{
		NyA = nyA;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public boolean isActivo()
	{
		return activo;
	}

	public void setActivo(boolean activo)
	{
		this.activo = activo;
	}

   
    
    
}
