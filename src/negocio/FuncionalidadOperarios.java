package negocio;

import excepciones.IDRepetido_Exception;
import excepciones.NyARepetido_Exception;
import excepciones.PromoIdRepetido_Exception;
import excepciones.UserNameRepetido_Exception;
import modelo.Comanda;
import modelo.Enumerados;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Producto;

public class FuncionalidadOperarios {

	private Operario operarioActual;

	public FuncionalidadOperarios(Operario operarioActual) {
		super();
		this.operarioActual = operarioActual;
	}

	/**
	 * Metodo utilizado para modificar el o los campos que se deseen del operario
	 * que inició sesión.
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
	 * Metodo utilizado para eliminar el operario que inició sesión.
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
	 * metodo para modificar el/los atributos del mozo que se desee.
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
	 * metodo para modificar el/los atributos que se deseen del producto.
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
	 * metodo para modificar el/los atributos que se deseen de la mesa.
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
	 */

	public void modificaMesa(Mesa mesa, int nroMesa, int cantSillas, boolean libre) {
	}

	/**
	 * metodo para agregar un nueva promocion de un producto al menu. <br>
	 * Pre: El producto debe ser distinto de null. <br>
	 * Pre: La promo aplica2x1 y
	 * 
	 * @param idProd                        ID del producto al que se le aplica la
	 *                                      promo. <br>
	 * @param dia                           de la semana en el que estara disponible
	 *                                      la promo. <br>
	 * @param aplica2x1                     tipo de descuento. <br>
	 * @param aplicaDtoPorCantidad          tipo de descuento. <br>
	 * @param dtoPorCantidad_CantMinima     tipo de descuento. <br>
	 * @param dtoPorCantidad_PrecioUnitario tipo de descuento. <br>
	 * @param activa                        determina si esta activa o no en el
	 *                                      momento actual. <br>
	 * @throws PromoIdRepetido_Exception Se lanza si se intenta asignar un
	 *                                   identificador de promo existente. <br>
	 */
	public void agregaPromocionProd(int idProd, Enumerados.diasDePromo dia, boolean aplica2x1,
			boolean aplicaDtoPorCantidad, int dtoPorCantidad_CantMinima, double dtoPorCantidad_PrecioUnitario,
			boolean activa) throws PromoIdRepetido_Exception {
	}

	/**
	 * metodo para modificar el estado de una promo.<br>
	 * 
	 * @param idProm identificador de la promo. <br>
	 * @param activa estado de la promo. <br>
	 */

	public void modificaPromocionProd(int idProm, boolean activa) {
		/* solo activa o desactiva la promo */}

	/**
	 * metodo que elimina la promo. <br>
	 * 
	 * @param idProm identificador de la promo. <br>
	 */

	public void eliminaPromocionProd(int idProm) {
	}

	public void agregaPromocionTemporal(String nombre, Enumerados.formaDePago formaDePago, int porcentajeDescuento,
			Enumerados.diasDePromo diasDePromo, boolean activo, boolean esAcumulable) {
	}

	public void eliminaPromocionTemporal(String nombre) {
	}

	public void modificaPromocionTemporal(String nombre, boolean activo, int porcentajeDescuento,
			boolean esAcumulable) {
	}

	public void setEstadoMozo(Mozo mozo) {
	}

	public void setMesaMozo(Mesa mesa, Mozo mozo) {
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
