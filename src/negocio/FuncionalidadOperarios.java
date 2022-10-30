package negocio;

import excepciones.IDRepetido_Exception;
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
import modelo.PromocionTemporal;

public class FuncionalidadOperarios {

	private Operario operarioActual;

	public FuncionalidadOperarios(Operario operarioActual) {
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
	public void modificaOperario(String NyA, String userName, String password, boolean activo)
			throws UserNameRepetido_Exception {

		// llamamos al operario actual y modificamos a ese
		// se delega a la Clase Operario
		// El estado se modifica?
	}

	/**
	 * Metodo utilizado para eliminar el operario que inició sesión. <br>
	 * 
	 * Pre: el atributo operarioActual de FuncionalidadOperarios debe ser distinto
	 * de null. <br>
	 * Post: se elimina operarioActual del sistema. <br>
	 */
	public void eliminaOperario() { // consideramos que el operario puede eliminarse por s� mismo al igual que puede
									// eliminarlo el admin.
		// se llama al actual y se lo elimina
	}

	/**
	 * metodo para modificar el/los atributos del mozo que se desee. <br>
	 * 
	 * Pre: mozo debe ser distinto de null. <br>
	 * Pre: NyA debe ser distinto de null y vacio. <br>
	 * Pre: cantHijos debe ser igual a 0 o mayor. <br>
	 * Post: se modifican los atributos del objeto pasado como parametro. <br>
	 * 
	 * @param mozo      que se desea modificar.
	 * @param NyA       nombre y apellido nuevo.
	 * @param cantHijos nueva cantidad de hijos.
	 * @throws NyARepetido_Exception se lanza cuando se intenta cambiar el nombre de
	 *                               un mozo por uno que ya posee otro mozo.
	 */
	public void modificaMozo(Mozo mozo, String NyA, int cantHijos) throws NyARepetido_Exception { // el estado lo
																									// modifica el o el
																									// sistema??
		// el estado se modifica?
	}

	/**
	 * metodo para modificar el/los atributos que se deseen del producto. <br>
	 * 
	 * Pre: producto debe ser distinto de null. <br>
	 * Pre: id debe ser mayor a 0. <br>
	 * Pre: nombre debe ser distinto de null y vacio. <br>
	 * Pre: precioCosto debe ser mayor a 0 y menor que precioVenta. <br>
	 * Pre: precioVenta debe ser mayor a 0 y mayor que precioCosto. <br>
	 * Pre: stockInicial debe ser mayor a 0. <br>
	 * Post: se modifican los atributos del objeto pasado como parametro. <br>
	 * 
	 * @param producto     producto a modificar. <br>
	 * @param id           nuevo id del producto. <br>
	 * @param nombre       nuevo nombre del producto. <br>
	 * @param precioCosto  nuevo precio de costo. <br>
	 * @param precioVenta  nuevo precio de venta. <br>
	 * @param stockInicial nuevo stock inicial. <br>
	 * @throws IDRepetido_Exception se lanza cuando se intenta cambiar el id de un
	 *                              producto por uno que ya posee otro producto .
	 */
	public void modificaProducto(Producto producto, int id, String nombre, double precioCosto, double precioVenta,
			int stockInicial) throws IDRepetido_Exception {
	}

	/**
	 * metodo para modificar el/los atributos que se deseen de la mesa. <br>
	 * 
	 * Pre: mesa debe ser distinto de null. <br>
	 * Pre: nroMesa debe ser igual o mayor a 0. <br>
	 * Pre: cantSillas debe ser mayor a 0. <br>
	 * Post: se modifican los atributos del objeto pasado como parametro. <br>
	 * 
	 * 
	 * @param mesa       mesa a modificar. <br>
	 * @param nroMesa    ID de mesa. <br>
	 * @param cantSillas cantidad de personas que ocuparan la mesa. <br>
	 * @param libre      estado de la mesa. <br>
	 * @throws NroMesaRepetido_Exception Se lanza si se intenta asignar un numero de
	 *                                   mesa existente.
	 */

	public void modificaMesa(Mesa mesa, int nroMesa, int cantSillas, boolean libre) throws NroMesaRepetido_Exception {
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
			boolean aplicaDtoPorCantidad, int dtoPorCantidad_CantMinima, double dtoPorCantidad_PrecioUnitario) {
	}

	/**
	 * metodo para modificar el estado de un producto en promocion.<br>
	 * Pre: promocionProd debe ser distinto de null. <br>
	 * Post: se cambia el atributo activa del objeto promocionProd. <br>
	 * 
	 * @param promocionProd producto con promocion a modificar. <br>
	 * @param activa        nuevo estado de la promocion. <br>
	 */

	public void modificaPromocionProd(PromocionProd promocionProd, boolean activa) {
		/* solo activa o desactiva la promo */}

	/**
	 * metodo que elimina un producto en promocion. <br>
	 * Pre: promocionProd debe ser distinto de null. <br>
	 * Post: se elimina el producto en promocion del sistema. <br>
	 * 
	 * @param promocionProd producto con promocion a eliminar. <br>
	 */

	public void eliminaPromocionProd(PromocionProd promocionProd) {
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
			Enumerados.formaDePago formaDePago, int porcentajeDescuento, boolean esAcumulable) {
	}

	/**
	 * metodo que elimina una promocion temporal del sistema. <br>
	 * Pre: promocionTemporal debe ser distinto de null. <br>
	 * Post: se elimina una promocion temporal del sistema. <br>
	 * 
	 * @param promocionTemporal promocion temporal a eliminar. <br>
	 */
	public void eliminaPromocionTemporal(PromocionTemporal promocionTemporal) {
	}

	/**
	 * metodo para modificar el estado de una promocion temporal.<br>
	 * Pre: promocionTemporal debe ser distinto de null. <br>
	 * Post: se cambia el atributo activa del objeto promocionTemporal. <br>
	 * 
	 * @param promocionTemporal promocion a modificar. <br>
	 * @param activa            nuevo estado de la promocion. <br>
	 */
	public void modificaPromocionTemporal(PromocionTemporal promocionTemporal, boolean activo) {
		/* solo activa o desactiva la promo */}

	/**
	 * metodo que cambia el estado de un mozo. <br>
	 * Pre: mozo debe ser disitinto de null. <br>
	 * Pre: nuevoEstado debe ser disitinto de null. <br>
	 * Post: se cambia el atributo estado del mozo. <br>
	 * 
	 * @param mozo        a quien se lecambia el estado. <br>
	 * @param nuevoEstado nuevo estado que se le cambia el mozo. <br>
	 */
	public void cambiaEstadoMozo(Mozo mozo, Enumerados.estadoMozo nuevoEstado) {
	}

	/**
	 * metodo que asigna un mozo a una mesa Pre: mesa debe ser distinto de null.
	 * <br>
	 * Pre: mozo debe ser distinto de null. <br>
	 * Post: se le asigna el mozo al atributo mozo de la mesa pasada como parametro.
	 * <br>
	 * 
	 * @param mesa mesa a la cual se le asigna el mozo. <br>
	 * @param mozo mozo que se le asigna a la mozo. <br>
	 */
	public void asignaMozoAMesa(Mesa mesa, Mozo mozo) {
	} // mesa ref a mozo

	// verifica promos, instancia MesaAtendida, y la agrega a el ArrayList del mozo
	public void cierraMesa(Comanda comanda) {
	}

	public void cierraComanda(Comanda comanda) {
	}

	// crea comanda
	public void abreComanda(Mesa mesa) {
	}

	public void agregaPedidos(Comanda comanda, int cant, int idProd) {
	}

}
