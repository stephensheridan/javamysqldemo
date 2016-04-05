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
			print("Database connection successful.");
			printNewline();
		}
		catch(Exception e){
			// something went wrong with DB connection
			print("Database Connection Error!");
			printNewline();
			e.printStackTrace();
		}
	}

	// This method wraps up all the code for closing a database connection
	public static void closeDBConnection(){
		if (conn != null){
			try{
				conn.close();
				print("Database closed successfully.");
				printNewline();
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
			String query = "SELECT * FROM emp";
			print("[SQL QUERY] " + query);
			ResultSet rs = stmt.executeQuery(query);
			//Process the results set
			print("*** Employees ***");
			while (rs.next()){
				String sout = rs.getInt("empno") + "," + rs.getString("empname") + "," + 
			                  rs.getString("empdob") + "," +  rs.getFloat("empsalary"); 
				print(sout);
			}
			printNewline();
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
			print("[SQL QUERY] " + query);
			int result = stmt.executeUpdate(query);
			stmt.close();
			print("Employee successfully updated.");
			printNewline();
		}
		catch(Exception e){
			print("Error executing UPDATE statemennt");
		}
	}
	
	public static void insertEmployee(String name, String DOB, float salary){
		try{
			// Create a statement object
			Statement stmt = conn.createStatement();
			//Execute a Query
			
			// NOTE: empno is my primary key and it is set to auto-increment in my database schema
			// so we only need to insert empname, empdob, empsalary and MYSQL will look after a new empno value for us.
			String query = "INSERT INTO emp(empname,empdob,empsalary) VALUES (";
			query = query + "'" + name + "'" + ",'" + DOB + "'," + salary + ");"; 
			print("[SQL QUERY] " + query);
			int result = stmt.executeUpdate(query);
			stmt.close();
			print("New employee successfully inserted.");
			printNewline();
		}
		catch(Exception e){
			print("Error executing INSERT statemennt");
			e.printStackTrace();
		}
	}
	
	public static void deleteEmployee(String empname){
		try{
			// Create a statement object
			Statement stmt = conn.createStatement();
			//Execute a Query
			String query = "DELETE FROM emp WHERE empname ='" + empname + "';";
			print("[SQL QUERY] " + query);
			int result = stmt.executeUpdate(query);
			stmt.close();
			print("New employee successfully deleted.");
			printNewline();
		}
		catch(Exception e){
			print("Error executing INSERT statemennt");
			e.printStackTrace();
		}
	}
	
	public static void print(String s){System.out.println(s);}
	public static void printNewline(){System.out.println("");}
	
	public static void main(String[] args) {
		
		// Make a MySql database connection
		getDBConnection();
		
		// List all employees from DB
		listEmployees();
				
		print("Give Employee No. 1 a new salary");
		// Update the salary of employee no. 1
		updateEmpSalary(1, 200000.65f);
		
		// List all employees from DB
		listEmployees();
		
		// Insert a new employee
		print("Inserting a new employee called Mark Maguire...");
		insertEmployee("Mark Maguire", "1967-04-23", 45000f);
		
		// List all employees from DB
		listEmployees();
		
		// Delete an employee by empno
		print("Delete Mark Maguire...");
		deleteEmployee("Mark Maguire");
		
		// List all employees from DB
		listEmployees();
				
		// Tidy up
		closeDBConnection();
		
	}
	
}
