package sis.logistica.general;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import sis.logistica.BO.InventarioBO;
import sis.logistica.Backend.Inventario;
import sis.logistica.Exception.BOCustomExceptions;
import sis.logistica.SQLDAO.SQLInventarioDAO;
import sis.logistica.UX.InventarioABMUX;

public class Handler {

	private InventarioBO inventarioBO;
	private JFrame 		containerFrame;

	public Handler(){
		this.inventarioBO = new InventarioBO();
		this.inventarioBO.setDAO(new SQLInventarioDAO());
	}
	
	public void init() {
		containerFrame = new InventarioABMUX("Sistema Logistica - UP Final Programacion", this);
		containerFrame.setVisible(true);
	}
	 
	
	public void crearinventario (Inventario inventario) {
		try {
			inventarioBO.create(inventario);
		} catch (BOCustomExceptions e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error en agregar inventario", JOptionPane.WARNING_MESSAGE);
		}
	}
	

	public void actualizarinventario (Inventario inventario) {
		try {
			inventarioBO.actualizarinventario(inventario);
		} catch (BOCustomExceptions e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error en actualizar inventario", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public Inventario getInventario (Inventario inventario) {

		Inventario inventoryFound = null;
		try {
			inventoryFound = inventarioBO.getByCodigoArticulo(inventario);
		} catch (BOCustomExceptions e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error en encontrar el articulo en el maestro de inventario", JOptionPane.WARNING_MESSAGE);
		}

		return inventoryFound;
	}
	
	public List<Inventario> GetInventario() {
		return InventarioBO.mostrarTodo();
	}
	
	public void eliminarregistroinventario (String codigo_inventario) {
		try {
			inventarioBO.eliminarInventario(codigo_inventario);
			JOptionPane.showMessageDialog(null, "Articulo "+codigo_inventario+" eliminado", "Eliminacion de articulo",JOptionPane.INFORMATION_MESSAGE);
		} catch (BOCustomExceptions e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error en eliminar articulo del inventario", JOptionPane.ERROR_MESSAGE);
		}
	}
}
