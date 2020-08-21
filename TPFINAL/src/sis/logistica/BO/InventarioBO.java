package sis.logistica.BO;

import java.util.List;

import sis.logistica.Backend.Inventario;
import sis.logistica.Exception.BOCustomExceptions;
import sis.logistica.Exception.SQLCustomExceptions;
import sis.logistica.SQLDAO.SQLInventarioDAO;

public class InventarioBO {

	private SQLInventarioDAO sqlinventariodao;

	public void setDAO(SQLInventarioDAO sqlinventariodao) {
		this.sqlinventariodao = sqlinventariodao;
	}

	
	public void validarduplicado (Inventario inventario) throws BOCustomExceptions {
		
		try {
			System.out.println(inventario.getCodigoarticulo());
			Inventario inventory = sqlinventariodao.getByCodigoArticulo (inventario.getCodigoarticulo());
			if (!inventory.getCodigoarticulo().isEmpty()) {
				throw new BOCustomExceptions("Codigo Articulo "+inventory.getCodigoarticulo()+" ya existe en el sistema");
			}
		} catch (SQLCustomExceptions e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	
	public void validarinventario (Inventario inventario) throws BOCustomExceptions{
			
		if (inventario.getCodigoarticulo().equals("")) {
			throw new BOCustomExceptions("Debe agregar Codigo Articulo");
		}else if (inventario.getStockcantidad() <= 0) {
			System.out.println(inventario.getStockcantidad());
			throw new BOCustomExceptions("El stock debe ser mayor a 0");
		}else if (inventario.getUnidadmedida().equals("")) {
			throw new BOCustomExceptions("Debe agregar una unidad de medida");
		}
	}

	public void create(Inventario inventario) throws BOCustomExceptions {
		validarinventario(inventario);
		validarduplicado(inventario);
		try {
			sqlinventariodao.insertar(inventario);
		} catch (SQLCustomExceptions e) {
			throw new BOCustomExceptions("Error en insertar articulo", e);
		} 
	}

	public void actualizarinventario(Inventario inventario) throws BOCustomExceptions {
		try {
			sqlinventariodao.actualizar(inventario);
		} catch (SQLCustomExceptions e) {
			// TODO Auto-generated catch block
			throw new BOCustomExceptions("blabla",e);
		}
	}

	
	public Inventario getByCodigoArticulo(Inventario inventario) throws BOCustomExceptions{
		try {
			return sqlinventariodao.getByCodigoArticulo (inventario.getCodigoarticulo());
		} catch (SQLCustomExceptions e) {
			// TODO Auto-generated catch block
			throw new BOCustomExceptions("Error obteniendo articulo",e);
		}
	}

	
	public void eliminarInventario(String codigo_inventario)  throws BOCustomExceptions {
		try {
			sqlinventariodao.eliminarPorCodigo(codigo_inventario);
		} catch(SQLCustomExceptions e) {
			throw new BOCustomExceptions("Error eliminando articulo",e);
		}
	}

	public static List<Inventario> mostrarTodo () {
		SQLInventarioDAO inventarioDao = new SQLInventarioDAO();
		return inventarioDao.mostrarTodo();
	}
}
