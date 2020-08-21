package sis.logistica.DB;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TableManager {

	private static String ddlInventarioTable = "CREATE TABLE INVENTARIO "
			+ "(inventory_item_id 	INTEGER IDENTITY PRIMARY KEY, "
			+ "codigo_articulo		VARCHAR(20), "
			+ "descripcion 			VARCHAR(300), "
			+ "stock_cantidad		NUMERIC, "
			+ "unidad_medida		VARCHAR(10),"
			+ "precio_unitario		NUMERIC)";
	
	
	private static String ddlClienteTable = "CREATE TABLE CLIENTE "
			+ "(customer_id 	    INTEGER IDENTITY PRIMARY KEY, "
			+ "razon_social 		VARCHAR(250), "
			+ "cuit     			VARCHAR(15), "
			+ "categ_impuesto		VARCHAR(50), "
			+ "domicilio			VARCHAR(400) ";
	
	public void createDB() throws SQLException {
	    ArrayList<ArrayList<String>> tablename = new ArrayList<ArrayList<String>>();
                	
	    tablename.get(0).add("INVENTARIO");	    
	    tablename.get(0).add("CLIENTE");	    
	    
	    tablename.get(1).add(ddlInventarioTable);
	    tablename.get(1).add(ddlClienteTable);
	    
	    for (int i = 0; i <= tablename.get(0).size() - 1; i++) {
            createTable(tablename.get(0).get(i), tablename.get(1).get(i) );	 
	    }
	}

	private static void createTable(String tablename, String ddltable) throws SQLException {
		Connection connection = DBManager.connect();
		DatabaseMetaData dbm = connection.getMetaData();
	try {
		// valido que exista la tabla o no
		ResultSet tables = dbm.getTables(null, null, tablename, null);
		
		if (!tables.next()) {
		String sql = ddltable;
		try {
			Statement s = connection.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				connection.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		}	
	 }finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 	}
	}

	public static void borrarTabla(String tablename) {
		Connection connection = DBManager.connect();

		String sql = "TRUNCATE TABLE " + tablename;
		try {
			Statement s = connection.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				connection.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static void eliminarTabla(String tablename) {
		Connection connection = DBManager.connect();

		String sql = "DROP TABLE "+tablename;
		try {
			Statement s = connection.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				connection.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
