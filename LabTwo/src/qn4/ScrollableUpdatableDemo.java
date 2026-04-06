package qn4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class ScrollableUpdatableDemo {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/ddldemodb?allowMultiQueries=true";
		String user = "root";
		String password = "root";

		Connection conn = null;
		Statement updatableStmt = null;
		Statement readOnlyStmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection(url, user, password);

			updatableStmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			rs = updatableStmt.executeQuery("SELECT * FROM Student");

			if (rs.first()) {
				System.out.printf("First row: %d, %s, %s, %d, %s%n", rs.getInt("id"), rs.getString("name"),
						rs.getString("faculty"), rs.getInt("marks"), rs.getString("email"));
			}

			if (rs.last()) {
				System.out.printf("Last row: %d, %s, %s, %d, %s%n", rs.getInt("id"), rs.getString("name"),
						rs.getString("faculty"), rs.getInt("marks"), rs.getString("email"));
			}

			if (rs.absolute(2)) {
				rs.updateInt("marks", rs.getInt("marks") + 5);
				rs.updateRow();
				System.out.println("Updated marks for 2nd row.");
			}

			rs.moveToInsertRow();
			rs.updateString("name", "New Student");
			rs.updateString("faculty", "Science");
			rs.updateInt("marks", 88);
			rs.updateString("email", "newstudent@example.com");
			rs.insertRow();
			System.out.println("Inserted a new row.");

			if (rs.last()) {
				rs.deleteRow();
				System.out.println("Deleted the last row.");
			}

			rs.close();

			readOnlyStmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			boolean hasMoreResults = readOnlyStmt.execute(
					"SELECT * FROM Student WHERE marks > 40; " + "SELECT COUNT(*) AS total_students FROM Student;");

			int resultSetNumber = 1;

			do {
				if (hasMoreResults) {
					rs = readOnlyStmt.getResultSet();
					System.out.println("\nResult Set #" + resultSetNumber);

					ResultSetMetaData rsmd = rs.getMetaData();
					int columnCount = rsmd.getColumnCount();

					while (rs.next()) {
						for (int i = 1; i <= columnCount; i++) {
							System.out.print(rs.getString(i) + "\t");
						}
						System.out.println();
					}
				}
				resultSetNumber++;
				hasMoreResults = readOnlyStmt.getMoreResults();
			} while (hasMoreResults);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (updatableStmt != null)
					updatableStmt.close();
				if (readOnlyStmt != null)
					readOnlyStmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println("\nProgram completed successfully.");
	}
}
