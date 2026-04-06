package qn2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDatabaseOperations {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/ddldemodb";
		String user = "root";
		String password = "root";

		Connection con = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();

			String createTableSQL = "CREATE TABLE IF NOT EXISTS Student (" + "id INT PRIMARY KEY, "
					+ "name VARCHAR(50), " + "faculty VARCHAR(50), " + "marks INT)";
			int rows = stmt.executeUpdate(createTableSQL);
			System.out.println("Table creation affected rows: " + rows);

			String alterTableSQL = "ALTER TABLE Student ADD COLUMN email VARCHAR(100)";
			rows = stmt.executeUpdate(alterTableSQL);
			System.out.println("Alter table affected rows: " + rows);

			String insertSQL1 = "INSERT INTO Student VALUES (1, 'Parakram Kharel', 'Science', 85, 'parakram@example.com')";
			String insertSQL2 = "INSERT INTO Student VALUES (2, 'Ankit', 'Math', 90, 'ankit@example.com')";
			String insertSQL3 = "INSERT INTO Student VALUES (3, 'Aayush', 'Arts', 78, 'aayush@example.com')";
			String insertSQL4 = "INSERT INTO Student VALUES (4, 'Pix', 'Engineering', 92, 'pix@example.com')";
			String insertSQL5 = "INSERT INTO Student VALUES (5, 'zek', 'Science', 88, 'zek@example.com')";

			rows = stmt.executeUpdate(insertSQL1);
			System.out.println("Insert 1 affected rows: " + rows);
			rows = stmt.executeUpdate(insertSQL2);
			System.out.println("Insert 2 affected rows: " + rows);
			rows = stmt.executeUpdate(insertSQL3);
			System.out.println("Insert 3 affected rows: " + rows);
			rows = stmt.executeUpdate(insertSQL4);
			System.out.println("Insert 4 affected rows: " + rows);
			rows = stmt.executeUpdate(insertSQL5);
			System.out.println("Insert 5 affected rows: " + rows);

			String updateSQL = "UPDATE Student SET marks = 95 WHERE id = 2";
			rows = stmt.executeUpdate(updateSQL);
			System.out.println("Update affected rows: " + rows);
			String deleteSQL = "DELETE FROM Student WHERE id = 3";
			rows = stmt.executeUpdate(deleteSQL);
			System.out.println("Delete affected rows: " + rows);

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
		
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
