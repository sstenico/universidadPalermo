package sis.logistica.SQLDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sis.logistica.Backend.Inventario;
import sis.logistica.DAO.InventarioDAO;
import sis.logistica.DB.*;
import sis.logistica.Exception.SQLCustomExceptions;

public class SQLInventarioDAO implements InventarioDAO{

	@Override
	public void insertar(Inventario inventario) throws SQLCustomExceptions {
	Connection conn = DBManager.connect();
	 try {
        Statement stmt = conn.createStatement();
		String query = "INSERT INTO INVENTARIO"
				+ " (codigo_articulo, "
				+ "  descripcion,"
				+ "  stock_cantidad,"
				+ "  unidad_medida,"
				+ "  precio_unitario )"
				+ "  VALUES ('"+inventario.getCodigoarticulo()+ "',"
				+ " '" + inventario.getDescripcion() + "', '" 
				+ inventario.getStockcantidad() + "', '" 
				+ inventario.getUnidadmedida()+ "', '"
				+ inventario.getPreciounitario()
				+ "')";
		stmt.executeQuery(query);
		conn.commit();
	  } catch (SQLException e) {
		  try {
				conn.rollback();
			} catch (SQLException e1) {
			}
		  throw new SQLCustomExceptions("Error al insertar registro");
		} finally {
			try {
				conn.close();
			} catch (SQLException e1) {
			}
		}
	
	}

	@Override
	public void actualizar(Inventario inventario) throws SQLCustomExceptions {
		Connection conn = DBManager.connect();
		try {
			Statement s = conn.createStatement();	
			String query = "UPDATE INVENTARIO "
					+"SET  codigo_articulo ="+"'"+inventario.getCodigoarticulo()+"'"+","
					+"descripcion          ="+"'"+inventario.getDescripcion()+"'"+","
					+"stock_cantidad	   ="+inventario.getStockcantidad()+","
					+"unidad_medida		   ="+"'"+inventario.getUnidadmedida()+"'"+","
					+"precio_unitario	   ="+inventario.getPreciounitario()
					+"WHERE codigo_articulo = "+"'"+inventario.getCodigoarticulo()+"'";
			s.executeQuery(query);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// no hago nada
			}
			  throw new SQLCustomExceptions("Error al actualizar registro");
		} finally {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e1) {
				// no hago nada
			}
		}
		
	}

	@Override
	public void eliminar(Inventario inventario) throws SQLCustomExceptions {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Inventario> mostrarTodo() {
		List<Inventario> inventory = new ArrayList<Inventario>();
		String sql = "SELECT * FROM INVENTARIO";
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				Inventario inventario = new Inventario
				(rs.getInt("inventory_item_id"), rs.getString("codigo_articulo"), rs.getString("descripcion"), rs.getInt("stock_cantidad"),
				rs.getString("unidad_medida"), rs.getInt("precio_unitario"));
				inventory.add(inventario);
			}
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				// no hago nada
			}
		} finally {
			try {
				c.close();
				return inventory;
			} catch (SQLException e1) {
				// no hago nada
			}
		}
		return inventory;
	}

	@Override
	public Inventario obtener(Integer iddatoabuscar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarPorCodigo(String codigo_articulo) throws SQLCustomExceptions{
		Connection conn = DBManager.connect();
		 try {
	        Statement stmt = conn.createStatement();
			String query = "DELETE FROM INVENTARIO "
					+"WHERE codigo_articulo ="+"'"+codigo_articulo.replaceAll("\\s","")+"'";
			stmt.executeQuery(query);
			conn.commit();
		  } catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// no hago nada
				}
			} finally {
				try {
					conn.close();
				} catch (SQLException e1) {
					// no hago nada
				}
			}
	}
		
	
	public Inventario getByCodigoArticulo(String codigo_articulo) throws SQLCustomExceptions {
		Inventario inventario = null;

		Connection c = DBManager.connect();
		try {
			System.out.println(codigo_articulo);
			String query = "select * from inventario where codigo_articulo=?";
			PreparedStatement preparedstatement = c.prepareStatement(query);
			preparedstatement.setString(1, "AR1234");
			ResultSet rs = preparedstatement.executeQuery(query);
			while (rs.next()) {
				inventario = new Inventario (rs.getInt("inventory_item_id"), rs.getString("codigo_articulo"), rs.getString("descripcion"),
											 rs.getInt("stock_cantidad"), rs.getString("unidad_medida"),rs.getInt("precio_unitario"));
				System.out.println("inv");
			}
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				throw new SQLCustomExceptions("Hubo un error en la busqueda del articulo inventario", e);
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
				throw new SQLCustomExceptions("Hubo un error en la busqueda de articulo", e1);
			}
		}
		return inventario;
	}

}
