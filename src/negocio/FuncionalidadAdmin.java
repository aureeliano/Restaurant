package negocio;

import java.util.GregorianCalendar;

import excepciones.NyARepetido_Exception;
import excepciones.UserNameRepetido_Exception;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
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
	 * Pre: NyA no puede ser null ni vacio, el mozo debe ser mayor de 18 anios y
	 * cantHijos debe ser mayor o igual a cero. <br>
	 * Post: Se agrega un nuevo Mozo al sistema.
	 * 
	 * @param NyA       atributo que representa nombre y apellido del mozo que se
	 *                  desea agregar.
	 * @param fecha     atributo que representa de nacimiento del mozo que se desea
	 *                  agregar.
	 * @param cantHijos atributo que representa la cantidad de hijos del mozo que se
	 *                  desea agregar.
	 * @param estado    atributo que representa el estado del mozo que se desea
	 *                  agregar.
	 * @throws NyARepetido_Exception si ya hay un mozo registrado con el mismo
	 *                               nombre y apellido
	 */
	public void agregaMozo(String NyA, GregorianCalendar fecha, int cantHijos) throws NyARepetido_Exception
	{
		if (Sistema.getInstance().getMozos().containsKey(NyA))
			throw new NyARepetido_Exception(NyA);
		else
			Sistema.getInstance().getMozos().put(NyA, new Mozo(NyA, fecha, cantHijos));

	}

	/**
	 * Metodo que elimina un Mozo del sistema. <br>
	 * Pre: NyA no puede ser null ni vacio. <br>
	 * 
	 * @param NyA atributo que representa el nombre y el apellido del mozo que se
	 *            desea eliminar.
	 * @throws NoExisteMozo_Exception no existe mozo con ese nombre y apellido
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
	 *                 se desea agregar.
	 * @param userName atributo que representa el userName del operario que se desea
	 *                 agregar.
	 * @param password atributo que representa la password del operario que se desea
	 *                 agregar.
	 */
	public void agregaOperario(String NyA, String username, String password) throws UserNameRepetido_Exception
	{
		if (Sistema.getInstance().getOperarios().containsKey(username))
			throw new UserNameRepetido_Exception(username);
		else
			Sistema.getInstance().getOperarios().put(username, new Operario(NyA, username, password));
	}

	/**
	 * Metodo que elimina un operario del sistema. <br>
	 * Pre: userName no puede ser null ni vacio. <br>
	 * Post: Se elimina un operario del sistema. <br>
	 * 
	 * @param userName atributo que representa el userName del operario que se desea
	 *                 eliminar.
	 * @throws NoExisteOperario_Exception no existe operario con ese userName.
	 */
	public void eliminaOperario(String userName)
	{
		Sistema.getInstance().getOperarios().remove(userName);
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
		Producto prod = new Producto(nombre, precioCosto,precioVenta,stockInicial);
		Sistema.getInstance().getProductos().put(prod.getIdProd(), prod);
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
	 * Post: Se elimina un producto del sistema. <br>
	 * 
	 * @param idProd atributo que representa el id del producto que se desea
	 *               eliminar del sistema.
	 */
	public void eliminaProducto(int idProd)
	{
		Sistema.getInstance().getProductos().remove(idProd);
	}

	/**
	 * Metodo que agrega una mesa al sistema. <br>
	 * Pre: cantSillas debe ser mayor a cero. <br>
	 * Post: Se agrega una mesa al sistema. <br>
	 * 
	 * @param cantSillas atributo que representa la cantidad de sillas que tiene la
	 *                   mesa que se desea agregar.
	 * @param libre      atributo que representa el estado de la mesa que se desea
	 *                   agregar.
	 */
	public void agregaMesa(int cantSillas)
	{
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
	 * Pre: numeroMesa debe ser mayor igual a cero. <br>
	 * Post: Se elimina una mesa del sistema. <br>
	 * 
	 * @param numeroMesa atributo que representa la mesa que se desea eliminar.
	 */
	public void eliminaMesa(int nroMesa)
	{
		Sistema.getInstance().getMesas().remove(nroMesa);
	}

}
