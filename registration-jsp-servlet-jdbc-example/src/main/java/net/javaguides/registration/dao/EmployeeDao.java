package net.javaguides.registration.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.javbaguides.registration.model.Employee;

public class EmployeeDao {

	public static int registerEmployee(Employee employee) throws SQLException {
		int result=0;
	 String query="insert into employee values(?,?,?,?,?,?,?)";
	     
	 
	   Connection con = null;
	   PreparedStatement pstmt = null;
	try{
		Class.forName("com.mysql.jdbc.Driver");
		 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employees","root","Siddu@123");
		 pstmt= con.prepareStatement(query);
		pstmt.setInt(1,employee.getId());
		pstmt.setString(2, employee.getFirstName());
		pstmt.setString(3,employee.getLastName());
		pstmt.setString(4,employee.getUsertName());
		pstmt.setString(5, employee.getPassword());
		pstmt.setString(6, employee.getAddress());
		pstmt.setString(7, employee.getContact_details());
		
		 result=pstmt.executeUpdate();
		
		if(result!=0) {
			System.out.println("registered sucessfully");
		}
	
		else {
			System.out.println("failed to register");
		}
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}finally {
		pstmt.close();
		con.close();
	}
		return result;

	}

}
