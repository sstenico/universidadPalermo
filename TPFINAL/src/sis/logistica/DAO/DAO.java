package sis.logistica.DAO;
import java.util.List;

import sis.logistica.Exception.SQLCustomExceptions;

public interface DAO <Clase, TipoDato> {

	void insertar   (Clase objeto) throws SQLCustomExceptions;
	void actualizar (Clase objeto) throws SQLCustomExceptions;
	void eliminar   (Clase objeto) throws SQLCustomExceptions;
	
	void eliminarPorCodigo   (String codigo) throws SQLCustomExceptions;
	
	List<Clase> mostrarTodo();
	
	Clase obtener (TipoDato iddatoabuscar);
	

}
