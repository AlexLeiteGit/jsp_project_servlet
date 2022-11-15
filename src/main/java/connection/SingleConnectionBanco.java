package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	private static String banco = "jdbc:postgresql://localhost:5433/jsp_project_servlet?autoReconnect=true";
	private static String user = "postgres";
	private static String password = "admin";
	private static Connection connection = null;
	
	static {
		conectar();
	}
	
	public SingleConnectionBanco() {
		conectar();
	}
	
	private static void conectar() {
		
		try {
			
			if(connection == null) {
				
				Class.forName("org.postgresql.Driver");/*carrega o drive de conexão do vbanco de dados*/
				connection = DriverManager.getConnection(banco, user, password);
				connection.setAutoCommit(false);
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

	public static Connection getConnection() {
		return connection;
	}

}
