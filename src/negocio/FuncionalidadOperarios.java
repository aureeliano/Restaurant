package negocio;

import excepciones.NroMesaRepetido_Exception;
import excepciones.NyARepetido_Exception;
import excepciones.UserNameRepetido_Exception;
import modelo.Comanda;
import modelo.Enumerados;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Producto;
import modelo.PromocionProd;

public class FuncionalidadOperarios
{

	private Operario operarioActual;

	public FuncionalidadOperarios(Operario operarioActual)
	{
		super();
		this.operarioActual = operarioActual;
	}

	/**
	 * Metodo utilizado para modificar el o los campos que se deseen del operario
	 * que inició sesión. <br>
	 * 
	 * Pre: el atributo operarioActual de FuncionalidadOperarios debe ser distinto
	 * de null. <br>
	 * Pre: NyA debe ser distinto de null y vacio. <br>
	 * Pre: userName debe ser distinto de null y vacio. <br>
	 * Pre: password debe ser distinto de null y vacio. <br>
	 * Post: se modifican los atributos del objeto pasado como parametro. <br>
	 * 
	 * @param NyA      nombre y apellido nuevo.
	 * @param userName nuevo nombre de usuario.
	 * @param password nueva contrasenia.
	 * @param activo   nuevo estado del Operario.
	 * @throws UserNameRepetido_Exception Se lanza si el nuevo nombre de usuario ya
	 *                                    existe en el sistema.
	 */
	public void modificaOperario(String NyA, String username, String password, boolean activo)
			throws UserNameRepetido_Exception
	{

		if (!this.operarioActual.getUsername().equals(username)
				&& Sistema.getInstance().getOperarios().containsKey(username))
			throw new UserNameRepetido_Exception(username);
		else
		{
			Sistema.getInstance().getOperarios().remove(this.operarioActual.getUsername());
			this.operarioActual.setNyA(NyA);
			this.operarioActual.setUsername(username);
			this.operarioActual.setPassword(password);
			this.operarioActual.setActivo(activo);
			Sistema.getInstance().getOperarios().put(username, this.operarioActual);
		}
	}

	/**
	 * Metodo utilizado para eliminar el operario que inició sesión. <br>
	 * 
	 * Pre: el atributo operarioActual de FuncionalidadOperarios debe ser distinto
	 * de null. <br>
	 * Post: se elimina operarioActual del sistema. <br>
	 */
	public void eliminaOperario()
	{
		Sistema.getInstance().getOperarios().remove(this.operarioActual.getUsername());
		this.operarioActual = null;
	}

	/**
	 * metodo para modificar el/los atributos del mozo que se desee. <br>
	 * 
	 * Pre: NyA debe ser key del hashmap mozos del sistema <br>
	 * Pre: nuevoNyA debe ser distinto de null y vacio. <br>
	 * Pre: cantHijos debe ser igual a 0 o mayor. <br>
	 * Post: se modifican los atributos del objeto pasado como parametro. <br>
	 * 
	 * @param NyA       nombre y apellido del mozo a modificar.
	 * @param nuevoNyA  nombre y apellido nuevo.
	 * @param cantHijos nueva cantidad de hijos.
	 * @throws NyARepetido_Exception se lanza cuando se intenta cambiar el nombre de
	 *                               un mozo por uno que ya posee otro mozo.
	 */
	public void modificaMozo(String NyA, String nuevoNyA, int cantHijos) throws NyARepetido_Exception
	{
		Mozo mozo = Sistema.getInstance().getMozos().get(NyA);
		if (!mozo.getNyA().equals(nuevoNyA) && Sistema.getInstance().getMozos().containsKey(nuevoNyA))
			throw new NyARepetido_Exception(nuevoNyA);
		else
		{
			Sistema.getInstance().getMozos().remove(mozo.getNyA());
			mozo.setNyA(nuevoNyA);
			mozo.setCantHijos(cantHijos);
			Sistema.getInstance().getMozos().put(nuevoNyA, mozo);
		}
	}

	/**
	 * metodo que cambia el estado de un mozo. <br>
	 * Pre: NyA debe ser key del hashmap mozos del sistema <br>
	 * Pre: nuevoEstado debe ser disitinto de null. <br>
	 * Post: se cambia el atributo estado del mozo. <br>
	 * 
	 * @param NyA         nombre y apellido del mozo al que se le cambia el estado.
	 *                    <br>
	 * @param nuevoEstado nuevo estado que se le cambia el mozo. <br>
	 */
	public void cambiaEstadoMozo(String NyA, Enumerados.estadoMozo nuevoEstado)
	{
		Mozo mozo = Sistema.getInstance().getMozos().get(NyA);
		mozo.setEstado(nuevoEstado);
	}

	/**
	 * metodo para modificar el/los atributos que se deseen del producto. <br>
	 * 
	 * Pre: id debe ser key del hashmap productos del sistema <br>
	 * Pre: nombre debe ser distinto de null y vacio. <br>
	 * Pre: precioCosto debe ser mayor a 0 y menor que precioVenta. <br>
	 * Pre: precioVenta debe ser mayor a 0 y mayor que precioCosto. <br>
	 * Pre: stockInicial debe ser mayor a 0. <br>
	 * Post: se modifican los atributos del objeto pasado como parametro. <br>
	 * 
	 * @param id           id del producto a modificar. <br>
	 * @param nombre       nuevo nombre del producto. <br>
	 * @param precioCosto  nuevo precio de costo. <br>
	 * @param precioVenta  nuevo precio de venta. <br>
	 * @param stockInicial nuevo stock inicial. <br>
	 * @throws IDRepetido_Exception se lanza cuando se intenta cambiar el id de un
	 *                              producto por uno que ya posee otro producto .
	 */
	public void modificaProducto(int id, String nombre, double precioCosto, double precioVenta, int stockInicial)
	{
		Producto prod = Sistema.getInstance().getProductos().get(id);
		prod.setNombre(nombre);
		prod.setPrecioCosto(precioCosto);
		prod.setPrecioVenta(precioVenta);
		prod.setStockInicial(stockInicial);
	}

	/**
	 * metodo para modificar el/los atributos que se deseen de la mesa. <br>
	 * 
	 * Pre: nroMesa debe ser key del hashmap mesas del sistema <br>
	 * Pre: cantSillas debe ser mayor a 0. <br>
	 * Post: se modifican los atributos del objeto pasado como parametro. <br>
	 * 
	 * 
	 * @param nroMesa    numero de la mesa a modificar. <br>
	 * @param cantSillas cantidad de personas que ocuparan la mesa. <br>
	 * @param libre      estado de la mesa. <br>
	 * @throws NroMesaRepetido_Exception Se lanza si se intenta asignar un numero de
	 *                                   mesa existente.
	 */

	public void modificaMesa(int nroMesa, int cantSillas, Enumerados.estadoMesa estado) throws NroMesaRepetido_Exception
	{
		Mesa mesa = Sistema.getInstance().getMesas().get(nroMesa);
		mesa.setCantSillas(cantSillas);
		mesa.setEstado(estado);
	}

	/**
	 * metodo para agregar un nueva promocion de un producto al menu. <br>
	 * Pre: dia debe ser distinto de null. <br>
	 * Pre: producto debe ser distinto de null. <br>
	 * Pre: dia debe ser distinto de null. <br>
	 * Pre: dtoPorCantidad_CantMinima debe ser mayor a 0. <br>
	 * Pre: dtoPorCantidad_PrecioUnitario debe ser mayor a 0 y menor que 1 (es un
	 * porcentaje). <br>
	 * Post: se agrega un nuevo producto con promocion al sistema. <br>
	 * 
	 * @param activa                        indica si la promocion esta activa o no.
	 *                                      <br>
	 * @param dia                           de la semana en el que estara disponible
	 *                                      la promo. <br>
	 * @param producto                      al que se le aplica la promocion
	 * @param aplica2x1                     tipo de descuento. <br>
	 * @param aplicaDtoPorCantidad          tipo de descuento. <br>
	 * @param dtoPorCantidad_CantMinima     cantidad minima de producto para la cual
	 *                                      se aplica el descuento. <br>
	 * @param dtoPorCantidad_PrecioUnitario porcentaje de descuento que se le hace
	 *                                      producto. <br>
	 */
	public void agregaPromocionProd(boolean activa, Enumerados.diasDePromo dia, Producto producto, boolean aplica2x1,
			boolean aplicaDtoPorCant, int dtoPorCant_CantMinima, double dtoPorCant_PrecioUnitario)
	{
	PromocionProd promprod = new PromocionProd(dia,producto,aplica2x1,aplicaDtoPorCant,dtoPorCant_CantMinima,dtoPorCant_PrecioUnitario);
	Sistema.getInstance().getPromocionProds().put(promprod.getIdProm(), promprod);
	}

	/**
	 * metodo para modificar el estado de un producto en promocion.<br>
	 * Pre: idProm debe ser key del hashmap promocionProds del sistema <br>
	 * Post: se cambia el atributo activa del objeto promocionProd. <br>
	 * 
	 * @param idProm id del producto con promocion a modificar. <br>
	 * @param activa nuevo estado de la promocion. <br>
	 */

	public void modificaPromocionProd(int idProm, boolean activa)
	{
		/* solo activa o desactiva la promo */}

	/**
	 * metodo que elimina un producto en promocion. <br>
	 * Pre: idProm debe ser key del hashmap promocionProds del sistema <br>
	 * Post: se elimina el producto en promocion del sistema. <br>
	 * 
	 * @param idProms id del producto con promocion a eliminar. <br>
	 */

	public void eliminaPromocionProd(int idProm)
	{
	}

	/**
	 * metodo que agrega una promocion temporal al sistema. <br>
	 * Pre: diasDePromo debe ser distinto de null. <br>
	 * Pre: nombre debe ser distinto de null y vacio. <br>
	 * Pre: formaDePago debe ser distinto de null. <br>
	 * Pre: porcentajeDescuento debe ser mayor a 0 y menor que 1 (es un porcentaje).
	 * <br>
	 * 
	 * @param activa              indica si la promocion esta activa. <br>
	 * @param diasDePromo         indica que dia de la semana se aplica la
	 *                            promocion. <br>
	 * @param nombre              es el nombre de la promocion. <br>
	 * @param formaDePago         es la forma de pago para la cual se aplica la
	 *                            promocion. <br>
	 * @param porcentajeDescuento es el porcentaje de descuento de la aplicacion.
	 *                            <br>
	 * @param esAcumulable        indica si se puede acumular con otras promociones.
	 *                            <br>
	 */
	public void agregaPromocionTemporal(boolean activa, Enumerados.diasDePromo diasDePromo, String nombre,
			Enumerados.formaDePago formaDePago, int porcentajeDescuento, boolean esAcumulable)
	{
	}

	/**
	 * metodo que elimina una promocion temporal del sistema. <br>
	 * Pre: nombre debe ser key del hashmap promocionTemps del sistema <br>
	 * Post: se elimina una promocion temporal del sistema. <br>
	 * 
	 * @param nombre nombre de la promocion temporal a eliminar. <br>
	 */
	public void eliminaPromocionTemporal(String nombre)
	{
	}

	/**
	 * metodo para modificar el estado de una promocion temporal.<br>
	 * Pre: nombre debe ser key del hashmap promocionTemps del sistema <br>
	 * Post: se cambia el atributo activa del objeto promocionTemporal. <br>
	 * 
	 * @param nommbre nombre de la promocion a modificar. <br>
	 * @param activa  nuevo estado de la promocion. <br>
	 */
	public void modificaPromocionTemporal(String nombre, boolean activo)
	{
		/* solo activa o desactiva la promo */}

	/**
	 * metodo que asigna un mozo a una mesa. <br>
	 * Pre: nroMesa debe ser key del hashmap mesas del sistema <br>
	 * Pre: NyA debe ser key del hashmap mozos del sistema <br>
	 * Post: se le asigna el mozo al atributo mozo de la mesa pasada como parametro.
	 * <br>
	 * 
	 * @param nroMesa numero de la mesa a la cual se le asigna el mozo. <br>
	 * @param NyA     nombre del mozo que se le asigna a la mesa. <br>
	 */
	public void asignaMozoAMesa(int nroMesa, String NyA)
	{
	} // mesa ref a mozo

	// verifica promos, instancia MesaAtendida, y la agrega a el ArrayList del mozo
	public void cierraMesa(Comanda comanda)
	{
	}

	public void cierraComanda(Comanda comanda)
	{
	}

	// crea comanda
	public void abreComanda(Mesa mesa)
	{
	}

	public void agregaPedidos(Comanda comanda, int cant, int idProd)
	{
	}

}
