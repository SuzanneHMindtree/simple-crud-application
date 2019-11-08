package project_java;

import java.sql.*;
import com.mysql.jdbc.Driver; 


public class Koneksi {
	Connection con;
	Statement stmt;
	
	public void koneksi(){

		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/icecream", "root", "");
			stmt = con.createStatement();
		}catch(Exception e){
			
		}
		
	}
}