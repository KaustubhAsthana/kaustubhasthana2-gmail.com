//package aaa;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Statement;
public class aaa{
	private static final String USERNAME="kaustubh";
	private static final String PASSWORD="anjupankaj";
	private static final String CONN="jdbc:mysql://localhost/sql1";//link for the connectivity
	
	public static void main(String args[]) throws SQLException {
		Scanner s =new Scanner(System.in);
		System.out.println("Enter the ID of the patient that we want to send the mail to");
		String i=s.next();
		//Class.forName(com.mysql.jdbc.Driver);-> No need for using this statement for accessing the database
		Connection con=null;//opening the connection
		Statement stat=null;//class set 
		ResultSet rs=null;
		String mail="";
		try {
			con=DriverManager.getConnection(CONN, USERNAME, PASSWORD);
			stat=(Statement) con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stat.executeQuery("SELECT * FROM patient where PatientID='"+i+"'");
			//while(rs.next()){
		         //Retrieve by column name
		        
			if(rs.next()) {
				String id  = rs.getString(1);
		         String name = rs.getString(2);
		         mail = rs.getString(3);
		         String last = rs.getString(4);

		         //Display values
			
		         System.out.print("ID: " + id);
		         System.out.print(", Name:  " + name);
		         System.out.print(", Mail: " + mail);
		         System.out.println(", Medicine: " + last);
			}
		         //}
		         
			System.out.println("Connected");
			System.out.println(mail);
			System.out.println(rs);//printing the no. of rows in the table
		}catch(SQLException e) {
			System.out.print(e);
		}
		finally {
			if(rs != null) {
				rs.close();//closing the connection
			}if(stat != null) {
				stat.close();
			}if(con != null) {
				con.close();
			}
		}
	}
	
}