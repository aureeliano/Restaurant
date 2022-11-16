package negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import excepciones.ComandaYaCerrada_Exception;
import excepciones.MenosDe2ProdsEnPromocion_Exception;
import excepciones.MesaNoTieneComanda_Exception;
import excepciones.MesaOcupada_Exception;
import excepciones.MozoNoActivo_Exception;
import excepciones.NoExisteEnLaColeccion_Exception;
import excepciones.NoHayProductos_Exception;
import excepciones.NroMesaRepetido_Exception;
import excepciones.NyARepetido_Exception;
import excepciones.PromocionTemporalNombreRepetido_Exception;
import excepciones.StockInsuficiente_Exception;
import excepciones.TodasMesasInhabilitadas_Exception;
import excepciones.TodosMozosInactivos_Exception;
import excepciones.UserNameRepetido_Exception;
import modelo.Comanda;
import modelo.Enumerados;
import modelo.Mesa;
import modelo.MesaAtendida;
import modelo.Mozo;
import modelo.Operario;
import modelo.Pedido;
import modelo.Producto;
import modelo.Promocion;
import modelo.PromocionProd;
import modelo.PromocionTemporal;

public class FuncionalidadOperarios
{

	private Operario operarioActual;

	public FuncionalidadOperarios(Operario operarioActual)
	{
		super();
		this.operarioActual = operarioActual;
	}

	public Operario getOperarioActual()
	{
		return operarioActual;
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
	 * @throws UserNameRepetido_Exception Se lanza si el nuevo nombre de usuario ya
	 *                                    existe en el sistema.
	 */
	public void modificaOperario(String NyA, String username, String password) throws UserNameRepetido_Exception
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
	 * Pre: username debe ser distinto de null o vacio. <br>
	 * Pre: nuevoNyA debe ser distinto de null y vacio. <br>
	 * Pre: cantHijos debe ser igual a 0 o mayor. <br>
	 * Post: se modifican los atributos del objeto pasado como parametro. <br>
	 * 
	 * @param NyA       nombre y apellido del mozo a modificar.
	 * @param nuevoNyA  nombre y apellido nuevo.
	 * @param cantHijos nueva cantidad de hijos.
	 * @throws NyARepetido_Exception           se lanza cuando se intenta cambiar el
	 *                                         nombre de un mozo por uno que ya
	 *                                         posee otro mozo.
	 * @throws NoExisteEnLaColeccion_Exception si NyA no es key del hashmap mozos de
	 *                                         Sistema. <br>
	 */
	public void modificaMozo(String NyA, String nuevoNyA, int cantHijos)
			throws NyARepetido_Exception, NoExisteEnLaColeccion_Exception
	{
		if (!Sistema.getInstance().getMozos().containsKey(NyA))
			throw new NoExisteEnLaColeccion_Exception(NyA);
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
	 * Pre: NyA debe ser distinto de null o vacio. <br>
	 * Pre: nuevoEstado debe ser disitinto de null. <br>
	 * Post: se cambia el atributo estado del mozo. <br>
	 * 
	 * @param NyA         nombre y apellido del mozo al que se le cambia el estado.
	 *                    <br>
	 * @param nuevoEstado nuevo estado que se le cambia el mozo. <br>
	 * @throws NoExisteEnLaColeccion_Exception si NyA no es key del hashmap mozos de
	 *                                         Sistema. <br>
	 */
	public void cambiaEstadoMozo(String NyA, Enumerados.estadoMozo nuevoEstado) throws NoExisteEnLaColeccion_Exception

	{
		if (!Sistema.getInstance().getMozos().containsKey(NyA))
			throw new NoExisteEnLaColeccion_Exception(NyA);
		Mozo mozo = Sistema.getInstance().getMozos().get(NyA);
		mozo.setEstado(nuevoEstado);
	}

	/**
	 * metodo para modificar el/los atributos que se deseen del producto. <br>
	 * 
	 * Pre: id no debe ser negativo. <br>
	 * Pre: nombre debe ser distinto de null y vacio. <br>
	 * Pre: precioCosto debe ser mayor a 0 y menor que precioVenta. <br>
	 * Pre: precioVenta debe ser mayor a 0 y mayor que precioCosto. <br>
	 * Post: se modifican los atributos del objeto pasado como parametro. <br>
	 * 
	 * @param id          id del producto a modificar. <br>
	 * @param nombre      nuevo nombre del producto. <br>
	 * @param precioCosto nuevo precio de costo. <br>
	 * @param precioVenta nuevo precio de venta. <br>
	 * @throws NoExisteEnLaColeccion_Exception si id no es key del hashmap productos
	 *                                         de Sistema. <br>
	 */
	public void modificaProducto(int id, String nombre, double precioCosto, double precioVenta)
			throws NoExisteEnLaColeccion_Exception

	{
		if (!Sistema.getInstance().getProductos().containsKey(id))
			throw new NoExisteEnLaColeccion_Exception(Integer.toString(id));
		Producto prod = Sistema.getInstance().getProductos().get(id);
		prod.setNombre(nombre);
		prod.setPrecioCosto(precioCosto);
		prod.setPrecioVenta(precioVenta);
	}

	/**
	 * metodo para modificar el/los atributos que se deseen de la mesa. <br>
	 * 
	 * Pre: nroMesa no debe ser negativo <br>
	 * Pre: cantSillas debe ser mayor a 0. <br>
	 * Post: se modifican los atributos del objeto pasado como parametro. <br>
	 * 
	 * 
	 * @param nroMesa    numero de la mesa a modificar. <br>
	 * @param cantSillas cantidad de personas que ocuparan la mesa. <br>
	 * @param libre      estado de la mesa. <br>
	 * @throws NroMesaRepetido_Exception       Se lanza si se intenta asignar un
	 *                                         numero de mesa existente.
	 * @throws NoExisteEnLaColeccion_Exception si nroMesa no es key del hashmap
	 *                                         mesas de Sistema. <br>
	 */

	public void modificaMesa(int nroMesa, int cantSillas, Enumerados.estadoMesa estado)
			throws NroMesaRepetido_Exception, NoExisteEnLaColeccion_Exception

	{
		if (!Sistema.getInstance().getMesas().containsKey(nroMesa))
			throw new NoExisteEnLaColeccion_Exception(Integer.toString(nroMesa));
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
		PromocionProd promprod = new PromocionProd(dia, producto, aplica2x1, aplicaDtoPorCant, dtoPorCant_CantMinima,
				dtoPorCant_PrecioUnitario);
		Sistema.getInstance().getPromocionProds().put(producto.getIdProd(), promprod); // se usa el id del producto como
																						// key de la promocion (facilita
																						// las cosas)
	}

	/**
	 * metodo para modificar el estado de un producto en promocion.<br>
	 * Pre: idProm no debe ser negativo. <br>
	 * Post: se cambia el atributo activa del objeto promocionProd. <br>
	 * 
	 * @param idProm id del producto con promocion a modificar. <br>
	 * @param activa nuevo estado de la promocion. <br>
	 * @throws NoExisteEnLaColeccion_Exception si idProm no es key del hashmap
	 *                                         promocionProds de Sistema. <br>
	 */

	public void modificaPromocionProd(int idProm, boolean activa) throws NoExisteEnLaColeccion_Exception

	{
		if (!Sistema.getInstance().getPromocionProds().containsKey(idProm))
			throw new NoExisteEnLaColeccion_Exception(Integer.toString(idProm));
		Sistema.getInstance().getPromocionProds().get(idProm).setActiva(activa);
		;
	}

	/**
	 * metodo que elimina un producto en promocion. <br>
	 * Pre: idProm no debe ser negativo <br>
	 * Post: se elimina el producto en promocion del sistema. <br>
	 * 
	 * @param idProms id del producto con promocion a eliminar. <br>
	 * @throws NoExisteEnLaColeccion_Exception si idProm no es key del hashmap
	 *                                         promocionProds de Sistema. <br>
	 */

	public void eliminaPromocionProd(int idProm) throws NoExisteEnLaColeccion_Exception

	{
		if (!Sistema.getInstance().getPromocionProds().containsKey(idProm))
			throw new NoExisteEnLaColeccion_Exception(Integer.toString(idProm));
		Sistema.getInstance().getPromocionProds().remove(idProm);
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
	 * @throws PromocionTemporalNombreRepetido_Exception se lanza si una promocion
	 *                                                   ya habia sido nombrada con
	 *                                                   el mismo nombre que se pasa
	 *                                                   como parametro. <br>
	 * 
	 */
	public void agregaPromocionTemporal(Enumerados.diasDePromo diasDePromo, String nombre,
			Enumerados.formaDePago formaDePago, int porcentajeDescuento, boolean esAcumulable)
			throws PromocionTemporalNombreRepetido_Exception
	{
		ArrayList<PromocionTemporal> promoTemps = Sistema.getInstance().getPromocionTemps();

		for (int i = 0; i < promoTemps.size(); i++)
			if (promoTemps.get(i).getNombre().equals(nombre))
				throw new PromocionTemporalNombreRepetido_Exception(nombre);

		promoTemps.add(new PromocionTemporal(diasDePromo, nombre, formaDePago, porcentajeDescuento, esAcumulable));
	}

	/**
	 * metodo que elimina una promocion temporal del sistema. <br>
	 * Pre: nombre debe ser distinto de null o vacio. <br>
	 * Post: se elimina una promocion temporal del sistema. <br>
	 * 
	 * @param nombre nombre de la promocion temporal a eliminar. <br>
	 * @throws NoExisteEnLaColeccion_Exception si la promocion con el nombre pasado
	 *                                         como parametro no existe en el
	 *                                         arraylist promocionTemps de Sistema.
	 *                                         <br>
	 */
	public void eliminaPromocionTemporal(String nombre) throws NoExisteEnLaColeccion_Exception

	{
		ArrayList<PromocionTemporal> promoTemps = Sistema.getInstance().getPromocionTemps();
		int i = 0;
		while (i < promoTemps.size() && !promoTemps.get(i).getNombre().equals(nombre))
			i++;
		if (i == promoTemps.size())
			throw new NoExisteEnLaColeccion_Exception(nombre);
		promoTemps.remove(i);
	}

	/**
	 * metodo para modificar el estado de una promocion temporal.<br>
	 * Pre: nombre debe ser distinto de null o vacio. <br>
	 * Post: se cambia el atributo activa del objeto promocionTemporal. <br>
	 * 
	 * @param nommbre nombre de la promocion a modificar. <br>
	 * @param activa  nuevo estado de la promocion. <br>
	 * @throws NoExisteEnLaColeccion_Exception si la promocion con el nombre pasado
	 *                                         como parametro no existe en el
	 *                                         arraylist promocionTemps de Sistema.
	 *                                         <br>
	 */
	public void modificaPromocionTemporal(String nombre, boolean activo) throws NoExisteEnLaColeccion_Exception
	{
		ArrayList<PromocionTemporal> promoTemps = Sistema.getInstance().getPromocionTemps();
		int i = 0;
		while (i < promoTemps.size() && !promoTemps.get(i).getNombre().equals(nombre))
			i++;
		if (i == promoTemps.size())
			throw new NoExisteEnLaColeccion_Exception(nombre);
		promoTemps.get(i).setActiva(activo);
	}

	/**
	 * metodo que asigna un mozo a una mesa. <br>
	 * Pre: NroMesa no debe ser negativo. <br>
	 * Pre: NyA debe ser distinto de null o vacio. <br>
	 * Post: se le asigna el mozo al atributo mozo de la mesa pasada como parametro.
	 * <br>
	 * 
	 * @param nroMesa numero de la mesa a la cual se le asigna el mozo. <br>
	 * @param NyA     nombre del mozo que se le asigna a la mesa. <br>
	 * @throws MozoNoActivo_Exception          si el mozo que se le quiere asignar a
	 *                                         la mesa no esta activo
	 * @throws NoExisteEnLaColeccion_Exception si nroMesa no es key del hashmap
	 *                                         mesas de Sistema o si NyA no es key
	 *                                         del hashmap mozos de Sistema.. <br>
	 */
	public void asignaMozoAMesa(int nroMesa, String NyA) throws MozoNoActivo_Exception, NoExisteEnLaColeccion_Exception

	{
		if (!Sistema.getInstance().getMesas().containsKey(nroMesa))
			throw new NoExisteEnLaColeccion_Exception(Integer.toString(nroMesa));
		if (!Sistema.getInstance().getMozos().containsKey(NyA))
			throw new NoExisteEnLaColeccion_Exception(NyA);
		Mozo mozo = Sistema.getInstance().getMozos().get(NyA);
		if (mozo.getEstado().equals(Enumerados.estadoMozo.ACTIVO))
			Sistema.getInstance().getMesas().get(nroMesa).setMozo(mozo);
		else
			throw new MozoNoActivo_Exception(mozo.getNyA());
	} // mesa ref a mozo

	/**
	 * Metodo que abre una nueva comanda. <br>
	 * Pre: nroMesa no debe ser negativo. <br>
	 * Post: asocia la mesa con el numero de mesa pasado como parametro a una nueva
	 * comanda. <br>
	 * Post: La mesa pasa a estar ocupada. <br>
	 * 
	 * @param nroMesa numero de la mesa que se qujiere asociar a una nuerva comanda.
	 *                <br>
	 * @throws TodasMesasInhabilitadas_Exception  si el sistema no tiene ninguna
	 *                                            mesa libre. <br>
	 * @throws TodosMozosInactivos_Exception      si el sistema no tiene ningun mozo
	 *                                            activo. <br>
	 * @throws MenosDe2ProdsEnPromocion_Exception si el sistema no tiene minimo 2
	 *                                            productos en promocion. <br>
	 * @throws MozoNoActivo_Exception             si el mozo asociado a la mesa con
	 *                                            el numero de mesa pasado como
	 *                                            parametro no esta activo. <br>
	 * @throws NoHayProductos_Exception           si el sistema no tiene productos.
	 *                                            <br>
	 * @throws MesaOcupada_Exception              si la mesa con el numero de mesa
	 *                                            pasado como parametro esta
	 *                                            ocupada. <br>
	 * @throws NoExisteEnLaColeccion_Exception    si nroMesa no es key del hashmap
	 *                                            mesas de Sistema. <br>
	 */
	public void abreComanda(int nroMesa)
			throws TodasMesasInhabilitadas_Exception, TodosMozosInactivos_Exception, MenosDe2ProdsEnPromocion_Exception,
			MozoNoActivo_Exception, NoHayProductos_Exception, MesaOcupada_Exception, NoExisteEnLaColeccion_Exception
	{
		if (!Sistema.getInstance().getMesas().containsKey(nroMesa))
			throw new NoExisteEnLaColeccion_Exception(Integer.toString(nroMesa));
		this.TodasMesasInhabilitadas();
		this.TodosMozosInactivos();
		this.MenosDe2ProdsEnPromocion();
		Mesa mesa = Sistema.getInstance().getMesas().get(nroMesa);
		if (!mesa.getMozo().getEstado().equals(Enumerados.estadoMozo.ACTIVO))
			throw new MozoNoActivo_Exception(mesa.getMozo().getNyA());
		if (Sistema.getInstance().getProductos().size() == 0)
			throw new NoHayProductos_Exception();
		if (!mesa.getEstado().equals(Enumerados.estadoMesa.LIBRE))
			throw new MesaOcupada_Exception(nroMesa);

		Sistema.getInstance().getComandas().put(nroMesa, new Comanda(mesa));
		mesa.setEstado(Enumerados.estadoMesa.OCUPADA);
	}

	private void TodasMesasInhabilitadas() throws TodasMesasInhabilitadas_Exception
	{
		Iterator<Entry<Integer, Mesa>> it1 = Sistema.getInstance().getMesas().entrySet().iterator();
		Entry<Integer, Mesa> entry1 = it1.next();
		while (it1.hasNext() || !entry1.getValue().getEstado().equals(Enumerados.estadoMesa.LIBRE))
			entry1 = it1.next();
		if (!it1.hasNext())
			throw new TodasMesasInhabilitadas_Exception();
	}

	private void TodosMozosInactivos() throws TodosMozosInactivos_Exception
	{
		Iterator<Entry<String, Mozo>> it2 = Sistema.getInstance().getMozos().entrySet().iterator();
		Entry<String, Mozo> entry2 = it2.next();
		while (it2.hasNext() || !entry2.getValue().getEstado().equals(Enumerados.estadoMozo.ACTIVO))
			entry2 = it2.next();
		if (!it2.hasNext())
			throw new TodosMozosInactivos_Exception();
	}

	private void MenosDe2ProdsEnPromocion() throws MenosDe2ProdsEnPromocion_Exception
	{
		Iterator<Entry<Integer, PromocionProd>> it3 = Sistema.getInstance().getPromocionProds().entrySet().iterator();
		int cant = 0;
		while (it3.hasNext() || cant < 2)
		{
			Entry<Integer, PromocionProd> entry3 = it3.next();
			if (entry3.getValue().isActiva())
				cant++;
		}
		if (!it3.hasNext())
			throw new MenosDe2ProdsEnPromocion_Exception();
	}

	/**
	 * Metodo que agrega un pedido a la comanda. <br>
	 * Pre: nroMesa no debe ser negativo. <br>
	 * Pre: idProd no debe ser negativo. <br>
	 * Pre: cant debe ser mayor a 0. <br>
	 * 
	 * @param nroMesa numero de la mesa que esta asociada a la comqanda a la cual se
	 *                le quiere agregar un pedido. <br>
	 * @param idProd  id del producto que se quiere agregar a la comanda
	 * @param cant    cantidad de producto que se quiere agregar a la comanda. <br>
	 * @throws MesaNoTieneComanda_Exception    si la mesa con el numero de mesa
	 *                                         pasado como parametro no esta
	 *                                         asociada a ninguna comanda. <br>
	 * @throws StockInsuficiente_Exception     si se quiere agregar una cantidad
	 *                                         mayor al stock del producto. <br>
	 * @throws NoExisteEnLaColeccion_Exception si nroMesa no es key del hashmap
	 *                                         mesas de Sistema o si idProd no es key del hashmap
	 *                                         productos de Sistema. <br>
	 */
	public void AgregaPedidoAComanda(int nroMesa, int idProd, int cant)
			throws MesaNoTieneComanda_Exception, StockInsuficiente_Exception, NoExisteEnLaColeccion_Exception
	{
		if (!Sistema.getInstance().getMesas().containsKey(nroMesa))
			throw new NoExisteEnLaColeccion_Exception(Integer.toString(nroMesa));
		if (!Sistema.getInstance().getProductos().containsKey(idProd))
			throw new NoExisteEnLaColeccion_Exception(Integer.toString(idProd));
		if (!Sistema.getInstance().getComandas().containsKey(nroMesa))
			throw new MesaNoTieneComanda_Exception(nroMesa);
		Producto prod = Sistema.getInstance().getProductos().get(idProd);
		if (prod.getStockActual() - cant < 0)
			throw new StockInsuficiente_Exception();
		Comanda comanda = Sistema.getInstance().getComandas().get(nroMesa);
		comanda.getPedidos().add(new Pedido(prod, cant));
		prod.setStockActual(prod.getStockActual() - cant);
	}

	

	/**
	 * Metodo que cierra una comanda. <br>
	 * Pre: nroMesa no debe ser negativo. <br>
	 * Pre: formaDePago debe ser distinto de null. <br>
	 * Post: asocia la ganancia al mozo que atendio la mesa con el numero de mesa
	 * pasado como parametro (teniendo en cuenta la promociones). <br>
	 * Post: cierra la comanda. <br>
	 * Post: libera la mesa con el numero de mesa pasaddo como parametro. <br>
	 * 
	 * @param nroMesa     numero de la mesa asociada a la comanda que se quiere
	 *                    cerrar. <br>
	 * @param formaDePago forma de pago. <br>
	 * @throws MesaNoTieneComanda_Exception si la mesa con el numero de mesa pasado
	 *                                      como parametro no esat asociada a
	 *                                      ninguna comanda. <br>
	 * @throws ComandaYaCerrada_Exception   si la comanda que se quiere cerrar ya
	 *                                      fue cerrada previamente. <br>
	 * @throws NoExisteEnLaColeccion_Exception si nroMesa no es key del hashmap mesas de Sistema. <br>
	 */
	public void cierraComanda(int nroMesa, Enumerados.formaDePago formaDePago)
			throws MesaNoTieneComanda_Exception, ComandaYaCerrada_Exception, NoExisteEnLaColeccion_Exception
			{
				if(!Sistema.getInstance().getMesas().containsKey(nroMesa))
					throw new NoExisteEnLaColeccion_Exception(Integer.toString(nroMesa));
				double total = 0;
		boolean aplicoPromProd = false;

		if (!Sistema.getInstance().getComandas().containsKey(nroMesa))
			throw new MesaNoTieneComanda_Exception(nroMesa);
		if (Sistema.getInstance().getComandas().get(nroMesa).getEstado().equals(Enumerados.estadoComanda.CERRADO))
			throw new ComandaYaCerrada_Exception(nroMesa);

		Comanda comanda = Sistema.getInstance().getComandas().get(nroMesa);
		ArrayList<Promocion> promos = new ArrayList<Promocion>();
		total += this.procesaPromProds(comanda, aplicoPromProd, promos);
		total *= this.procesaPromTemps(aplicoPromProd, formaDePago, promos);

		comanda.getMesa().getMozo().getMesasAtendidas()
				.add(new MesaAtendida(comanda.getMesa(), comanda.getPedidos(), total, formaDePago, promos));
		comanda.setEstado(Enumerados.estadoComanda.CERRADO);
		comanda.getMesa().setEstado(Enumerados.estadoMesa.LIBRE);

	}
	
	

	private double procesaPromProds(Comanda comanda, boolean aplicoPromProd, ArrayList<Promocion> promos)
	{
		double total = 0;
		for (int i = 0; i < comanda.getPedidos().size(); i++)
		{
			Pedido pedido = comanda.getPedidos().get(i);
			double precio = pedido.getProducto().getPrecioVenta();
			if (Sistema.getInstance().getPromocionProds().containsKey(pedido.getProducto().getIdProd()))
			{
				PromocionProd promProd = Sistema.getInstance().getPromocionProds()
						.get(pedido.getProducto().getIdProd());
				if (promProd.getDiasDePromo().toString().equals(LocalDate.now().getDayOfWeek().toString())
						&& promProd.isActiva())
				{
					if (promProd.isAplicaDtoPorCant() && (pedido.getCant() >= promProd.getDtoPorCant_CantMinima()))
						precio *= (1 - promProd.getDtoPorCant_PrecioUnitario());
					if (promProd.isAplica2x1())
						precio /= 2;
					aplicoPromProd = true;
					promos.add(promProd);
				}
			}
			total += precio * pedido.getCant();
		}
		return total;
	}

	private double procesaPromTemps(boolean aplicoPromProd, Enumerados.formaDePago formaDePago,
			ArrayList<Promocion> promos)
	{
		double total = 1;
		for (int i = 0; i < Sistema.getInstance().getPromocionTemps().size(); i++)
		{
			PromocionTemporal promTemp = Sistema.getInstance().getPromocionTemps().get(i);
			if (promTemp.getDiasDePromo().toString().equals(LocalDate.now().getDayOfWeek().toString())
					&& promTemp.getFormaDePago().equals(formaDePago) && promTemp.isActiva())
			{
				if (promTemp.isEsAcumulable() || aplicoPromProd == false)
				{
					total *= (1 - promTemp.getPorcentajeDesc());
					promos.add(promTemp);
				}
			}
		}
		return total;
	}

	/**
	 * metodo que calcula el promedio de venta de un mozo. <br>
	 * Pre: NyA debe ser distinto de null o vacio. <br>
	 * Post: devuleve el promedio de venta de un mozo. <br>
	 * 
	 * @param nombre nombre del mozo al que se le quiere saber el promedio. <br>
	 * @return promedio de venta de un mozo. <br>
	 * @throws NoExisteEnLaColeccion_Exception si NyA no es key del hashmap mozos de Sistema. <br>
	 */
	public double estadisticasEmpleado(String NyA) throws NoExisteEnLaColeccion_Exception

	{
		if(!Sistema.getInstance().getMozos().containsKey(NyA))
			throw new NoExisteEnLaColeccion_Exception(NyA);
		Mozo mozo = Sistema.getInstance().getMozos().get(NyA);
		double promedioVenta = 0;
		for (int i = 0; i < mozo.getMesasAtendidas().size(); i++)
			promedioVenta += mozo.getMesasAtendidas().get(i).getTotal();
		promedioVenta /= mozo.getMesasAtendidas().size();

		return promedioVenta;
	}

	/**
	 * metodo que calcula el mozo que mas ganancia dio al establecimiento. <br>
	 * 
	 * @return mozo que mas ganancia dio al establecimiento. <br>
	 */
	public Mozo empleadoConMayorVolumenDeVenta()
	{
		Mozo empleadoMax = null;
		Mozo mozo = null;
		double maxTotal = 0;

		Iterator<Entry<String, Mozo>> it = Sistema.getInstance().getMozos().entrySet().iterator();
		Entry<String, Mozo> entry = null;
		while (it.hasNext())
		{
			entry = it.next();
			mozo = entry.getValue();
			double total = 0;
			for (int j = 0; j < mozo.getMesasAtendidas().size(); j++)
				total += mozo.getMesasAtendidas().get(j).getTotal();
			if (total > maxTotal)
			{
				maxTotal = total;
				empleadoMax = mozo;
			}
		}

		return empleadoMax;
	}

	/**
	 * metodo que calcula el mozo que menos ganancia dio al establecimiento. <br>
	 * 
	 * @return mozo que menos ganancia dio al establecimiento. <br>
	 */
	public Mozo empleadoConMenorVolumenDeVenta()
	{
		Mozo empleadoMin = null;
		Mozo mozo = null;
		double minTotal = 0;
		boolean primero = false;

		Iterator<Entry<String, Mozo>> it = Sistema.getInstance().getMozos().entrySet().iterator();
		Entry<String, Mozo> entry = null;
		while (it.hasNext())
		{
			entry = it.next();
			mozo = entry.getValue();
			double total = 0;
			for (int j = 0; j < mozo.getMesasAtendidas().size(); j++)
				total += mozo.getMesasAtendidas().get(j).getTotal();

			if (!primero)
			{
				minTotal = total;
				empleadoMin = mozo;
				primero = true;
			} else if (total < minTotal)
			{
				minTotal = total;
				empleadoMin = mozo;
			}
		}

		return empleadoMin;
	}

	/**
	 * Metodo que calcula el promedio de ganancia que genero una mesa. <br>
	 * Pre: nroMesa no debe ser negativo. <br>
	 * 
	 * @param nroMesa numero de la mesa que se quiere analizar. <br>
	 * @return el promedio de ganancia que genero la mesa con el numero de mesa
	 *         pasado como parametro. <br>
	 * @throws NoExisteEnLaColeccion_Exception si nroMesa no es key del hashmap mesas de Sistema. <br>
	 */
	public double consumoPromedioPorMesa(int nroMesa) throws NoExisteEnLaColeccion_Exception
	{
		if(!Sistema.getInstance().getMesas().containsKey(nroMesa))
			throw new NoExisteEnLaColeccion_Exception(Integer.toString(nroMesa));
		Iterator<Entry<String, Mozo>> it = Sistema.getInstance().getMozos().entrySet().iterator();
		Entry<String, Mozo> entry = null;
		double total = 0;
		int cant = 0;
		while (it.hasNext())
		{
			entry = it.next();
			ArrayList<MesaAtendida> mesas = entry.getValue().getMesasAtendidas();
			for (int i = 0; i < mesas.size(); i++)
				if (mesas.get(i).getMesa().getNroMesa() == nroMesa)
				{
					total += mesas.get(i).getTotal();
					cant++;
				}
		}
		return total / cant;
	}


}
