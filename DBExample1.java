import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

 public class DBExample1 {
	public static Connection conn;
	
	// This methods wraps up all the code for making a database connection
	public static void getDBConnection(){
		try{
			//Register the Driver
			Class.forName("com.mysql.jdbc.Driver");
			//Get a connection to the database
			conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/employee","root", "root");
			print("");
			print("Database connection successful.");
		}
		catch(Exception e){
			// something went wrong with DB connection
			print("Database Connection Error!");
			e.printStackTrace();
		}
	}

	// This method wraps up all the code for closing a database connection
	public static void closeDBConnection(){
		if (conn != null){
			try{
				conn.close();
				print("");
				print("Database closed successfully.");
			}
			catch(Exception e){
				print("Error closing the DB");
			}
		}
	}
	
	// Execute a select statement to return all the employee information
	public static void listEmployees(){
		try{
			// Create a statement object
			Statement stmt = conn.createStatement();
			//Execute a Query
			ResultSet rs = stmt.executeQuery("select * from emp");
			//Process the results set
			print("");
			print("*** Employees ***");
			while (rs.next()){
				String sout = rs.getInt("empno") + "," + rs.getString("empname") + "," + 
			                  rs.getString("empdob") + "," +  rs.getFloat("empsalary"); 
				print(sout);
			}
			rs.close();
		    stmt.close();
		}
		catch(Exception e){
			print("Error executing SELECT statemennt");
		}
	}
	
	public static void updateEmpSalary(int empno, float amount){
		try{
			// Create a statement object
			Statement stmt = conn.createStatement();
			//Execute a Query
			String query = "UPDATE emp SET empsalary=" + amount + " WHERE empno=" + empno + ";";
			stmt.executeUpdate(query);
			stmt.close();
			print("");
			print("Employee successfully updated.");
		}
		catch(Exception e){
			print("Error executing UPDATE statemennt");
		}
	}
	
	public static void print(String s){System.out.println(s);}
	
	public static void main(String[] args) {
		
		// Make a MySql database connection
		getDBConnection();
		
		// List all employees from DB
		listEmployees();
		
		
		print("");
		print("Give Employee No. 1 a new salary");
		// Update the salary of employee no. 1
		updateEmpSalary(1, 200000.65f);
		
		// List all employees from DB
		listEmployees();
				
		// Tidy up
		closeDBConnection();
		
	}
	
}
