package sis.logistica.DAO;
import java.util.List;

import sis.logistica.Backend.Inventario;

public interface InventarioDAO extends DAO <Inventario, Integer>{

	List<Inventario> mostrarTodo();

}
