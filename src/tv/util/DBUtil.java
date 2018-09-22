package tv.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class DBUtil {

	private static Connection connection;

	private DBUtil() {
	}

	public static Connection getConnection() throws SQLException {
		// define connection parameters
		String url = "jdbc:mysql://localhost:3306/tv_shows?autoReconnect=true&useSSL=false";
		String username = "tv_shows_user";
		String password = "sesame";

		// get and return connection
		connection = DriverManager.getConnection(url, username, password);
		return connection;
	}

	// Shouldn't need to call this if getConnection() called
	// within try-with-resources statement
	public static void closeConnection() throws SQLException {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Error closing connection!");
				throw e;
			} finally {
				connection = null;
			}
		}
	}
}