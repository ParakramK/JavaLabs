package qn5;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class CachedRowSetDemo {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/ddldemodb";
		String user = "root";
		String password = "root";

		CachedRowSet crs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			crs = RowSetProvider.newFactory().createCachedRowSet();
			crs.setUrl(url);
			crs.setUsername(user);
			crs.setPassword(password);

			crs.setCommand("SELECT * FROM Student LIMIT 10");
			crs.execute();

			System.out.println("Offline data display:");
			while (crs.next()) {
				System.out.printf("%d, %s, %s, %d, %s%n", crs.getInt("id"), crs.getString("name"),
						crs.getString("faculty"), crs.getInt("marks"), crs.getString("email"));
			}

			crs.beforeFirst();
			if (crs.next()) {
				crs.updateInt("marks", crs.getInt("marks") + 10);
				crs.updateRow();
				System.out.println("Updated first student's marks offline.");
			}

			crs.moveToInsertRow();
			crs.updateString("name", "Offline Student");
			crs.updateString("faculty", "Science");
			crs.updateInt("marks", 85);
			crs.updateString("email", "offline@student.com");
			crs.insertRow();
			crs.moveToCurrentRow();
			System.out.println("Inserted a new student offline.");

			// intentionally adding null to not null column
			crs.moveToInsertRow();
			crs.updateString("name", null);
			crs.updateString("faculty", "Arts");
			crs.updateInt("marks", 70);
			crs.updateString("email", "error@student.com");
			crs.insertRow();
			crs.moveToCurrentRow();

			crs.acceptChanges();

		} catch (SQLException sqle) {
			System.out.println("\nSQL Exception occurred!");
			System.out.println("Message: " + sqle.getMessage());
			System.out.println("SQLState: " + sqle.getSQLState());
			System.out.println("Vendor Error Code: " + sqle.getErrorCode());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("JDBC Driver not found: " + cnfe.getMessage());
		} finally {
			try {
				if (crs != null)
					crs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println("\nDisconnected processing demo completed.");
	}
}
