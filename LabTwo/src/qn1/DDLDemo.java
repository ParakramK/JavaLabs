package qn1;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DDLDemo {
	  public static void create()  {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			
		        String url = "jdbc:mysql://localhost:3306/ddldemodb?createDatabaseIfNotExist=true";
		        Connection con = DriverManager.getConnection(url, "root", "root");
	             Statement stm = con.createStatement();
	            System.out.println("Connected Successfully!");
	            DatabaseMetaData meta = con.getMetaData();
	            System.out.println("--- Database Metadata ---");
	            System.out.printf("Database Product Name : %s%n", meta.getDatabaseProductName());
	            System.out.printf("Driver Name           : %s%n", meta.getDriverName());
	            System.out.printf("Driver Version        : %s%n", meta.getDriverVersion());
	            System.out.printf("Logged-in User        : %s%n", meta.getUserName());

	            String sql = "CREATE TABLE IF NOT EXISTS student_tbl ("
	                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
	                    + "name VARCHAR(120), "
	                    + "age INT DEFAULT 10, "
	                    + "college VARCHAR(90))";
	            stm.execute(sql);
	            System.out.println("\nTable created successfully!");
	        } catch (SQLException e) {
	            System.out.println("SQL Error: " + e.getMessage());
	        } catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	    }
	  public static void main(String[] args) {
		 create();
	}
}
