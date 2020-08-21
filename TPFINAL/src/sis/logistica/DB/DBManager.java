package sis.logistica.DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
	private static final String DB_DRIVER = "org.hsqldb.jdbcDriver";
	private static final String DB_URL = "jdbc:hsqldb:file:sql/testdb;shutdown=true;hsqldb.default_table_type=cached";
	private static final String DB_USERNAME = "sa";
	private static final String DB_PASSWORD = "";
    
	private static Connection connection = null;

	public static Connection connect() {
		try {
			if (connection == null || connection.isClosed()) {
				try {
					Class.forName(DB_DRIVER);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
					connection.setAutoCommit(false);
				} catch (SQLException e) {
					e.printStackTrace();
					System.exit(0);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public static void shutdown() throws Exception {
		Connection connection = connect();
		Statement s = connection.createStatement();
		s.execute("SHUTDOWN");
		connection.close();
	}

}
