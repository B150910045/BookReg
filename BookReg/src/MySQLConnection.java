import java.sql.*;

public class MySQLConnection {
	Connection conn = null;
	ResultSet rs = null;

	String username;// = "root";
	String password;// = "cody";
	String connUrl = "jdbc:mysql://localhost:3306/";
	String databaseName;

	// байгуулагч функц
	public MySQLConnection(String dbname, String uname, String pass) {
		databaseName = dbname;
		username = uname;
		password = pass;
	}

	public boolean dbConnect() {
		try {
			// connUrl = "jdbc:mysql://localhost:3306/product_db?userUnicode=true&characterEncoding=utf8"
			connUrl += databaseName + "?userUnicode=true&characterEncoding=utf8";
			conn = DriverManager.getConnection(connUrl, username, password);

			if (conn != null)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public Connection getConnection() {
		return conn;
	}
	
	public void execute(String query) {
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet getResult(String query) {
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(query);
			return rs;
		}

		catch (Exception e) {
			return rs = null;
		}
	}

	public void dbClose() {
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}