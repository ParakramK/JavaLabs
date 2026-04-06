package qn3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class StudentTransactionDemo {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/ddldemodb";
		String user = "root";
		String password = "root";

		Scanner scanner = new Scanner(System.in);

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);

			String sql = "INSERT INTO Student (name, faculty, marks, email) VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			for (int i = 1; i <= 3; i++) {
				System.out.println("Enter details for student " + i + ":");

				System.out.print("Name: ");
				String name = scanner.nextLine();

				System.out.print("Faculty: ");
				String faculty = scanner.nextLine();

				System.out.print("Marks: ");
				int marks = Integer.parseInt(scanner.nextLine());

				System.out.print("Email: ");
				String email = scanner.nextLine();

				pstmt.setString(1, name);
				pstmt.setString(2, faculty);
				pstmt.setInt(3, marks);
				pstmt.setString(4, email);

				pstmt.executeUpdate();

				if (i == 2) {
					System.out.println("Simulating failure...");
					throw new SQLException("Simulated failure!");
				}
			}

			conn.commit();
			System.out.println("Transaction committed successfully.");

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Error occurred: " + e.getMessage());

			try {
				if (conn != null) {
					conn.rollback();
					System.out.println("Transaction rolled back due to error.");
				}
			} catch (SQLException rollbackEx) {
				System.out.println("Rollback failed: " + rollbackEx.getMessage());
			}

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				scanner.close();
			} catch (SQLException e) {
				System.out.println("Error closing resources: " + e.getMessage());
			}
		}

		System.out.println("Program finished.");
	}
}
