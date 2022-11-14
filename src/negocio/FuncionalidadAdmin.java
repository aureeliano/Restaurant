package negocio;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;

import excepciones.ContrasenaInvalida_Exception;
import excepciones.MozoMenorDeEdad_Exception;
import excepciones.NoPuedeHaberMasDe6Mozos_Exception;
import excepciones.NyARepetido_Exception;
import excepciones.ProductoEstaEnComanda_Exception;
import excepciones.SilasDebenSerMayoresA1CuandoNroMesaMayorA1_Exception;
import excepciones.UserNameRepetido_Exception;
import modelo.Comanda;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Pedido;
import modelo.Producto;

/**
 * Clase que agrupa las funcionalidades usuario de tipo administrador
 *
 */
public class FuncionalidadAdmin extends FuncionalidadOperarios
{

	public FuncionalidadAdmin(Operario operarioActual)
	{
		super(operarioActual);
	}

	/**
	 * Metodo que agrega un nuevo Mozo al sistema. <br>
	 * Pre: NyA no puede ser null ni vacio. <br>
	 * Pre: cantHijos debe ser mayor o igual a cero. <br>
	 * Post: Se agrega un nuevo Mozo al sistema.
	 * 
	 * @param NyA       atributo que representa nombre y apellido del mozo que se
	 *                  desea agregar.
	 * @param fecha     atributo que representa de nacimiento del mozo que se desea
	 *                  agregar.
	 * @param cantHijos atributo que representa la cantidad de hijos del mozo que se
	 *                  desea agregar.
	 * @throws NyARepetido_Exception             si ya hay un mozo registrado con el
	 *                                           mismo nombre y apellido.
	 * @throws NoPuedeHaberMasDe6Mozos_Exception si se intenta agregar un mozo
	 *                                           cuando ya hay 6 en el sistema.
	 * @throws MozoMenorDeEdad_Exception         si el mozo es menor de edad.
	 */
	public void agregaMozo(String NyA, int diaDeNacimiento, int mesDeNacimiento, int anioDeNacimiento, int cantHijos)
			throws NyARepetido_Exception, NoPuedeHaberMasDe6Mozos_Exception, MozoMenorDeEdad_Exception
	{
		if (Sistema.getInstance().getMozos().size() > 6)
			throw new NoPuedeHaberMasDe6Mozos_Exception();
		if (Sistema.getInstance().getMozos().containsKey(NyA))
			throw new NyARepetido_Exception(NyA);
		else
		{
			Date fechaDeNacimiento = new Date();
			fechaDeNacimiento.setDate(diaDeNacimiento);
			fechaDeNacimiento.setMonth(mesDeNacimiento - 1);
			fechaDeNacimiento.setYear(anioDeNacimiento - 1900);
			LocalDate nacimiento = fechaDeNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			long edad = ChronoUnit.YEARS.between(nacimiento, LocalDate.now());
			if (edad < 18)
				throw new MozoMenorDeEdad_Exception(edad);
			Sistema.getInstance().getMozos().put(NyA, new Mozo(NyA, fechaDeNacimiento, cantHijos));
		}

	}

	/**
	 * Metodo que elimina un Mozo del sistema. <br>
	 * Pre: NyA debe ser key del hashmap mozos del sistema <br>
	 * 
	 * @param NyA atributo que representa el nombre y el apellido del mozo que se
	 *            desea eliminar.
	 */
	public void eliminaMozo(String NyA)
	{
		Sistema.getInstance().getMozos().remove(NyA);
	}

	/**
	 * Metodo que agrega Operarios al sistema. <br>
	 * Pre: NyA, userName y password no pueden ser null ni vacio. <br>
	 * Post: Se agrega un nuevo operario al sistema. <br>
	 * 
	 * @param NyA      atributo que representa el nombre y apellido del operario que
	 *                 se desea agregar. <br>
	 * @param userName atributo que representa el userName del operario que se desea
	 *                 agregar. <br>
	 * @param password atributo que representa la password del operario que se desea
	 *                 agregar. <br>
	 * @throws UserNameRepetido_Exception si e username ya le pertenece a otro operario. <br>
	 * @throws ContrasenaInvalida_Exception si la contrasenia no cumple que contiene entre 6 y 12 caracteres con al menos 1 digito y 1 mayuscula 
	 */
	public void agregaOperario(String NyA, String username, String password)
			throws UserNameRepetido_Exception, ContrasenaInvalida_Exception
	{
		if (Sistema.getInstance().getOperarios().containsKey(username))
			throw new UserNameRepetido_Exception(username);
		else if (!PasswordValida(password))
			throw new ContrasenaInvalida_Exception();
		else
			Sistema.getInstance().getOperarios().put(username, new Operario(NyA, username, password));
	}

	private boolean PasswordValida(String password)
	{
		boolean mayuscula = false;
		boolean digito = false;
		if (password.length() > 5 && password.length() < 13)
		{
			char ch;
			int i = 0;
			while (i < password.length() && (!digito || !mayuscula))
			{
				ch = password.charAt(i);
				if (Character.isDigit(ch))
					digito = true;
				else if (Character.isUpperCase(ch))
					mayuscula = true;
				i++;
			}
		}
		return (mayuscula && digito);

	}

	/**
	 * Metodo que activa o desactiva a un operario. <br>
	 * Pre: username debe ser key del hashmap operarios del sistema <br>
	 * Post: se cambia el estado del operario con el username pasado como parametro.
	 * <br>
	 * 
	 * @param username username del oprario a modificar. <br>
	 * @param activo   se activa o desactiva el operario.<br>
	 */
	public void activaDesactivaOperario(String username, boolean activo)
	{
		Sistema.getInstance().getOperarios().get(username).setActivo(activo);
	}

	/**
	 * Metodo que elimina un operario del sistema. <br>
	 * Pre: username debe ser key del hashmap operarios del sistema <br>
	 * Post: Se elimina un operario del sistema. <br>
	 * 
	 * @param username atributo que representa el userName del operario que se desea
	 *                 eliminar.
	 */
	public void eliminaOperario(String username)
	{
		Sistema.getInstance().getOperarios().remove(username);
	}

	/**
	 * Metodo que agrega un producto al sistema. <br>
	 * Pre: nombre no puede ser null ni vacio. <br>
	 * Pre: precioCosto, precioVenta y stockInicial deben ser mayores a cero. <br>
	 * Pre: precioVenta debe ser mayor a precioCosto. <br>
	 * Post: Se agrega un nuevo producto al sistema. <br>
	 * 
	 * @param nombre       atributo que representa el nombre del producto que se
	 *                     desea agregar.
	 * @param precioCosto  atributo que representa el precio de costo del producto
	 *                     que se desea agregar.
	 * @param precioVenta  atributo que representa el precio de venta del producto
	 *                     que se desea agregar.
	 * @param stockInicial atributo que representa el stock inicial del producto que
	 *                     se dese agregar.
	 */
	public void agregaProducto(String nombre, double precioCosto, double precioVenta, int stockInicial)
	{
		Producto prod = new Producto(nombre, precioCosto, precioVenta, stockInicial);
		Sistema.getInstance().getProductos().put(prod.getIdProd(), prod);
	}

	/**
	 * metodo para modificar los stocks del producto. <br>
	 * 
	 * Pre: id debe ser key del hashmap productos del sistema <br>
	 * Pre: stockInicial debe ser mayor a 0. <br>
	 * Pre: stockActual debe ser menor o igual a stockInicial. <br>
	 * Post: se modifican los stocks del objeto pasado como parametro. <br>
	 * 
	 * @param id           id del producto a modificar. <br>
	 * @param stockInicial nuevo stock inicial. <br>
	 * @param stockActual  nuevo stock actual. <br>
	 */
	public void modificaStockDeProducto(int id, int stockInicial, int stockActual)
	{
		Producto prod = Sistema.getInstance().getProductos().get(id);
		prod.setStockInicial(stockInicial);
		prod.setStockActual(stockActual);
	}

	/*
	 * Pau: si no se hace lista desplegable o botones, o sea ingreso por teclado el
	 * id, hay que agregar una excepcion porque puede ser que se ingrese un id de
	 * producto inexistente Tambien habria que agregar una excepcion de que no se
	 * puede eliminar el producto porque este est� en una comanda PREGUNTAR SI LA
	 * COMANDA TIENE QUE ESTAR ABIERTA O KHEEE sino medio que nunca se podria
	 * eliminar un producto porque es altamente probable de que este en una comanda
	 */

	/**
	 * Metodo que elimina un producto del sistema. <br>
	 * Pre: idProd debe ser key del hashmap productos del sistema <br>
	 * Post: Se elimina un producto del sistema. <br>
	 * 
	 * @param idProd atributo que representa el id del producto que se desea
	 *               eliminar del sistema.
	 * @throws ProductoEstaEnComanda_Exception si el producto que se quiere elminar
	 *                                         se encuentra en una comanda.
	 */
	public void eliminaProducto(int idProd) throws ProductoEstaEnComanda_Exception
	{
		Producto prod = Sistema.getInstance().getProductos().get(idProd);
		boolean estaEnComanda = false;
		Iterator<Entry<Integer, Comanda>> it = Sistema.getInstance().getComandas().entrySet().iterator();
		Entry<Integer, Comanda> entry = null;
		while (it.hasNext() && (estaEnComanda == false))
		{
			entry = it.next();
			ArrayList<Pedido> pedidos = entry.getValue().getPedidos();
			int i = 0;
			while (i < pedidos.size() && estaEnComanda == false)
			{
				if (pedidos.get(i).getProducto().equals(prod))
					estaEnComanda = true;
				i++;
			}

		}
		if (estaEnComanda == true)
			throw new ProductoEstaEnComanda_Exception(prod.getNombre());
		Sistema.getInstance().getProductos().remove(idProd);
	}

	/**
	 * Metodo que agrega una mesa al sistema. <br>
	 * Pre: cantSillas debe ser mayor a cero. <br>
	 * Post: Se agrega una mesa al sistema. <br>
	 * 
	 * @param cantSillas atributo que representa la cantidad de sillas que tiene la
	 *                   mesa que se desea agregar.
	 * @throws SilasDebenSerMayoresA1CuandoNroMesaMayorA1_Exception si no se cumple
	 *                                                              que la cantidad
	 *                                                              de comensales es
	 *                                                              > =2 cuando el
	 *                                                              nro de mesa es >
	 *                                                              1
	 */
	public void agregaMesa(int cantSillas) throws SilasDebenSerMayoresA1CuandoNroMesaMayorA1_Exception
	{
		if (Mesa.getSiguienteNroMesa() + 1 > 1 && cantSillas < 2)
			throw new SilasDebenSerMayoresA1CuandoNroMesaMayorA1_Exception();
		Mesa mesa = new Mesa(cantSillas);
		Sistema.getInstance().getMesas().put(mesa.getNroMesa(), mesa);
	}

	/*
	 * Pau: si no se hace lista desplegable o botones, o sea ingreso por teclado el
	 * id, hay que agregar una excepcion porque puede ser que se ingrese un nro de
	 * mesa de inexistente se puede eliminar la barra???
	 */

	/**
	 * Metodo que elimina una mesa del sistema. <br>
	 * Pre: nroMesqa debe ser key del hashmap mesas del sistema <br>
	 * Post: Se elimina una mesa del sistema. <br>
	 * 
	 * @param numeroMesa atributo que representa la mesa que se desea eliminar.
	 */
	public void eliminaMesa(int nroMesa)
	{
		Sistema.getInstance().getMesas().remove(nroMesa);
	}

}
