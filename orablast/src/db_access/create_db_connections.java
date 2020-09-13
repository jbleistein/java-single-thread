package db_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import gui.main_dashboard1;


public class create_db_connections {
	
	static Connection base_db_connection = db_connect.c1; //App config database established in the earlier db connect class.
	static Connection c1;
	static Statement stmt;
	static public Connection cs;



	public create_db_connections() {

			for (int i=0; i < myArray.length();i++) {
			 
			stmt = base_db_connection.createStatement();
	
		    String sql = "select * from dbs where dbname = '" +myArray[i] +"'";
		
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String connect_string_from_db_table=rs.getString("connect_string");
		String full_connect_string="jdbc:oracle:thin:app/app123@"+connect_string_from_db_table;
		
		System.out.println("connect string: "+full_connect_string);
		
		
         cs = DriverManager.getConnection(full_connect_string);		
				

			}
	    }
	 }
	
	}

